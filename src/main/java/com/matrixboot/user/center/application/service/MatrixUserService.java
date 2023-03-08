package com.matrixboot.user.center.application.service;

import com.matrixboot.user.center.domain.repository.IMatrixUserRepository;
import com.matrixboot.user.center.infrastructure.common.command.UserCreateCommand;
import com.matrixboot.user.center.infrastructure.common.command.UserDeleteCommand;
import com.matrixboot.user.center.infrastructure.common.command.UserUpdateCommand;
import com.matrixboot.user.center.infrastructure.common.query.UserQuery;
import com.matrixboot.user.center.infrastructure.common.result.UserResult;
import com.matrixboot.user.center.infrastructure.exception.UserEmailNotFountException;
import com.matrixboot.user.center.infrastructure.exception.UserIdNotFountException;
import com.matrixboot.user.center.infrastructure.exception.UserMobileNotFountException;
import com.matrixboot.user.center.infrastructure.exception.UsernameNotFountException;
import com.matrixboot.user.center.infrastructure.mapper.IMatrixUserMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

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
    @Caching(
            cacheable = {
                    @Cacheable(key = "'id:' + #id", unless = "null == #id")
            },
            put = {
                    @CachePut(key = "'username:' + #result.username()", unless = "null == #result.username()"),
                    @CachePut(key = "'mobile:' + #result.mobile()", unless = "null == #result.mobile()"),
                    @CachePut(key = "'email:' + #result.email()", unless = "null == #result.email()"),
            }
    )
    @SneakyThrows(Throwable.class)
    public UserResult findUserById(Long id) {
        log.info("findUserById {}", id);
        Optional<UserResult> optional = repository.findById(id, UserResult.class);
        return optional.orElseThrow(() -> new UserIdNotFountException(id));
    }


    /**
     * findByMobile
     *
     * @param mobile mobile
     * @return UserResult
     */
    @Caching(
            cacheable = {
                    @Cacheable(key = "'mobile:' + #mobile", unless = "null == #mobile")
            },
            put = {
                    @CachePut(key = "'id:' + #result.id()", unless = "null == #result.id()"),
                    @CachePut(key = "'username:' + #result.username()", unless = "null == #result.username()"),
                    @CachePut(key = "'email:' + #result.email()", unless = "null == #result.email()"),
            }
    )
    @SneakyThrows(Throwable.class)
    public UserResult findByMobile(@NotNull @NotBlank String mobile) {
        log.info("findByMobile {}", mobile);
        Optional<UserResult> optional = repository.findByMobile(mobile, UserResult.class);
        return optional.orElseThrow(() -> new UserMobileNotFountException(mobile));
    }

    /**
     * findByEmail
     *
     * @param email email
     * @return UserResult
     */
    @Caching(
            cacheable = {
                    @Cacheable(key = "'email:' + #email", unless = "null == #email")
            },
            put = {
                    @CachePut(key = "'id:' + #result.id()", unless = "null == #result.id()"),
                    @CachePut(key = "'username:' + #result.username()", unless = "null == #result.username()"),
                    @CachePut(key = "'mobile:' + #result.mobile()", unless = "null == #result.mobile()"),
            }
    )
    @SneakyThrows(Throwable.class)
    public UserResult findByEmail(@NotNull @NotBlank String email) {
        log.info("findByEmail {}", email);
        Optional<UserResult> optional = repository.findByEmail(email, UserResult.class);
        return optional.orElseThrow(() -> new UserEmailNotFountException(email));
    }

    /**
     * findUserByUsername
     *
     * @param username username
     * @return UserResult
     */
    @Caching(
            cacheable = {
                    @Cacheable(key = "'username:' + #username", unless = "null == #username")
            },
            put = {
                    @CachePut(key = "'id:' + #result.id()", unless = "null == #result.id()"),
                    @CachePut(key = "'mobile:' + #result.mobile()", unless = "null == #result.mobile()"),
                    @CachePut(key = "'email:' + #result.email()", unless = "null == #result.email()"),
            }
    )
    public UserResult findUserByUsername(@NotNull @NotBlank String username) {
        log.info("findUserByUsername {}", username);
        Optional<UserResult> optional = repository.findByUsername(username, UserResult.class);
        return optional.orElseThrow(() -> new UsernameNotFountException(username));
    }

    /**
     * createUser
     *
     * @param command UserCreateCommand
     * @return UserResult
     */
    @Caching(put = {
            @CachePut(key = "'id:' + #result.id()", unless = "null == #result.id()"),
            @CachePut(key = "'username:' + #result.username()", unless = "null == #result.username()"),
            @CachePut(key = "'mobile:' + #result.mobile()", unless = "null == #result.mobile()"),
            @CachePut(key = "'email:' + #result.email()", unless = "null == #result.email()"),
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
    @Caching(
            evict = {
                    @CacheEvict(key = "'id:' + #result.id()"),
                    @CacheEvict(key = "'username:' + #command.username()"),
                    @CacheEvict(key = "'mobile:' + #command.mobile()"),
                    @CacheEvict(key = "'email:' + #command.email()"),
            },
            put = {
                    @CachePut(key = "'id:' + #result.id()", unless = "null == #result.id()"),
                    @CachePut(key = "'username:' + #result.username()", unless = "null == #result.username()"),
                    @CachePut(key = "'mobile:' + #result.mobile()", unless = "null == #result.mobile()"),
                    @CachePut(key = "'email:' + #result.email()", unless = "null == #result.email()"),
            }
    )
    public UserResult updateUser(@NotNull @Valid UserUpdateCommand command) {
        var optional = repository.findById(command.id());
        var entity = optional.orElseThrow(() -> new UserIdNotFountException(command.id()));
        IMatrixUserMapper.INSTANCE.update(command, entity);
        var save = repository.save(entity);
        return IMatrixUserMapper.INSTANCE.from(save);
    }

    /**
     * deleteUserById
     *
     * @param command UserDeleteCommand
     * @return UserResult
     */
    @Caching(evict = {
            @CacheEvict(key = "'id:' + #result.id()"),
            @CacheEvict(key = "'username:' + #result.username()"),
            @CacheEvict(key = "'mobile:' + #result.mobile()"),
            @CacheEvict(key = "'email:' + #result.email()"),
    })
    public UserResult deleteUserById(@NotNull @Valid UserDeleteCommand command) {
        log.info("deleteUserById {}", command);
        var optional = repository.findById(command.id());
        var user = optional.orElseThrow(() -> new UserIdNotFountException(command.id()));
        repository.delete(user);
        return IMatrixUserMapper.INSTANCE.from(user);
    }
}
