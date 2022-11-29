package com.matrixboot.user.center.application.service;

import com.matrixboot.user.center.domain.entity.UserEntity;
import com.matrixboot.user.center.domain.repository.IUserRepository;
import com.matrixboot.user.center.infrastructure.common.command.UserCreateCommand;
import com.matrixboot.user.center.infrastructure.common.command.UserDeleteCommand;
import com.matrixboot.user.center.infrastructure.common.query.UserIdQuery;
import com.matrixboot.user.center.infrastructure.common.query.UserUsernameQuery;
import com.matrixboot.user.center.infrastructure.common.result.UserResultVO;
import com.matrixboot.user.center.infrastructure.exception.UserNotFountException;
import com.matrixboot.user.center.infrastructure.mapper.IUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * create in 2022/11/28 20:10
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@RequiredArgsConstructor
@Service
@CacheConfig(cacheNames = "common:user")
public class UserService {

    private final IUserRepository repository;

    /**
     * findUserById
     *
     * @param query id
     * @return UserResultVO
     */
    @Cacheable(key = "'id:' + #query.id()", unless = "null == #query.id()")
    public UserResultVO findUserById(@NotNull UserIdQuery query) {
        log.info("findUserById {}", query.id());
        return repository.findById(query.id(), UserResultVO.class);
    }

    /**
     * findUserByUsername
     *
     * @param query username
     * @return UserResultVO
     */
    @Cacheable(key = "'username:' + #query.username()", unless = "null == #query.username()")
    public UserResultVO findUserByUsername(@NotNull UserUsernameQuery query) {
        log.info("findUserByUsername {}", query.username());
        return repository.findByUsername(query.username(), UserResultVO.class);
    }

    /**
     * createUser
     *
     * @param command UserCreateCommand
     * @return UserResultVO
     */
    @Caching(put = {
            @CachePut(key = "'id:' + #result.id()", unless = "null == #result.id()"),
            @CachePut(key = "'username:' + #result.username()", unless = "null == #result.username()")
    })
    public UserResultVO createUser(UserCreateCommand command) {
        log.info("createUser {}", command);
        var user = IUserMapper.INSTANCE.from(command);
        UserEntity entity = repository.save(user);
        return IUserMapper.INSTANCE.from(entity);
    }

    /**
     * deleteUserById
     *
     * @param command UserDeleteCommand
     * @return UserResultVO
     */
    @Caching(evict = {
            @CacheEvict(key = "'id:' + #result.id()"),
            @CacheEvict(key = "'username:' + #result.username()")
    })
    public UserResultVO deleteUserById(@NotNull UserDeleteCommand command) {
        log.info("deleteUserById {}", command);
        var optional = repository.findById(command.id());
        UserEntity user = optional.orElseThrow(() -> new UserNotFountException(command.id()));
        repository.delete(user);
        return IUserMapper.INSTANCE.from(user);
    }
}
