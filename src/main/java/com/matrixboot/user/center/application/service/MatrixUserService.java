package com.matrixboot.user.center.application.service;

import com.matrixboot.user.center.domain.repository.IMatrixUserRepository;
import com.matrixboot.user.center.infrastructure.common.command.UserCreateCommand;
import com.matrixboot.user.center.infrastructure.common.command.UserDeleteCommand;
import com.matrixboot.user.center.infrastructure.common.command.UserUpdateCommand;
import com.matrixboot.user.center.infrastructure.common.query.UserQuery;
import com.matrixboot.user.center.infrastructure.common.result.UserResult;
import com.matrixboot.user.center.infrastructure.exception.UserNotFountException;
import com.matrixboot.user.center.infrastructure.mapper.IMatrixUserMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * create in 2022/11/28 20:10
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Validated
@CacheConfig(cacheNames = "common:user")
public class MatrixUserService {

    private final IMatrixUserRepository repository;

    public Page<UserResult> findByConditions(@NotNull UserQuery query, Pageable pageable) {
        return repository.findAllByUsernameStartsWith(query.getUsername() + "", pageable, UserResult.class);
    }


    /**
     * findUserById
     *
     * @param id id
     * @return UserResult
     */
    @Cacheable(key = "'id:' + #id", unless = "null == #id")
    public UserResult findUserById(String id) {
        log.info("findUserById {}", id);
        return repository.findById(id, UserResult.class);
    }

    /**
     * findUserByUsername
     *
     * @param username username
     * @return UserResult
     */
    @Cacheable(key = "'username:' + #username", unless = "null == #username")
    public UserResult findUserByUsername(@NotNull @NotBlank String username) {
        log.info("findUserByUsername {}", username);
        return repository.findByUsername(username, UserResult.class);
    }

    /**
     * createUser
     *
     * @param command UserCreateCommand
     * @return UserResult
     */
    @Caching(put = {
            @CachePut(key = "'id:' + #result.id()", unless = "null == #result.id()"),
            @CachePut(key = "'username:' + #result.username()", unless = "null == #result.username()")

    })
    public UserResult createUser(@Valid UserCreateCommand command) {
        log.info("createUser {}", command);
        var user = IMatrixUserMapper.INSTANCE.from(command);
        var entity = repository.save(user);
        return IMatrixUserMapper.INSTANCE.from(entity);
    }

    /**
     * updateUser
     *
     * @param command UserUpdateCommand
     * @return UserResult
     */
    @Caching(put = {
            @CachePut(key = "'id:' + #result.id()", unless = "null == #result.id()"),
            @CachePut(key = "'username:' + #result.username()", unless = "null == #result.username()")
    })
    @Transactional(rollbackFor = Exception.class)
    public UserResult updateUser(@NotNull @Valid UserUpdateCommand command) {
        var optional = repository.findById(command.id());
        var entity = optional.orElseThrow(() -> new UserNotFountException(command.id()));
        IMatrixUserMapper.INSTANCE.update(command, entity);
        return IMatrixUserMapper.INSTANCE.from(entity);
    }

    /**
     * deleteUserById
     *
     * @param command UserDeleteCommand
     * @return UserResult
     */
    @Caching(evict = {
            @CacheEvict(key = "'id:' + #result.id()"),
            @CacheEvict(key = "'username:' + #result.username()")
    })
    public UserResult deleteUserById(@NotNull @Valid UserDeleteCommand command) {
        log.info("deleteUserById {}", command);
        var optional = repository.findById(command.id());
        var user = optional.orElseThrow(() -> new UserNotFountException(command.id()));
        repository.delete(user);
        return IMatrixUserMapper.INSTANCE.from(user);
    }
}
