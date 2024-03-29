package com.matrixboot.user.center.application.service.user;

import com.matrixboot.user.center.domain.repository.IMatrixUserRepository;
import com.matrixboot.user.center.infrastructure.bus.IEventBus;
import com.matrixboot.user.center.infrastructure.common.command.user.UserCreateCommand;
import com.matrixboot.user.center.infrastructure.common.command.user.UserDeleteCommand;
import com.matrixboot.user.center.infrastructure.common.event.UserDeleteEvent;
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
import org.springframework.security.access.prepost.PreAuthorize;
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

    private final IEventBus eventBus;

    private final IMatrixUserRepository repository;

    /**
     * 按照条件查询
     *
     * @param query    查询条件
     * @param pageable 分页
     * @return Page
     */
    public Page<UserResult> findByConditions(@NotNull UserQuery query, Pageable pageable) {
        return repository.findAll(query.specification(), pageable)
                .map(x -> new UserResult(x.getId(), x.getUsername(), x.getMobile(), x.getEmail()));
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
    @PreAuthorize("permitAll()")
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
    @PreAuthorize("permitAll()")
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
        eventBus.publishEvent(new UserDeleteEvent(entity.getId(), entity.getUsername()));
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
            @CacheEvict(key = "'username:' + #result.username()"),
            @CacheEvict(key = "'mobile:' + #result.mobile()"),
            @CacheEvict(key = "'email:' + #result.email()"),
    })
    public UserResult deleteUserById(@NotNull @Valid UserDeleteCommand command) {
        log.info("deleteUserById {}", command);
        var optional = repository.findById(command.id());
        var user = optional.orElseThrow(() -> new UserIdNotFountException(command.id()));
        repository.delete(user);
        eventBus.publishEvent(new UserDeleteEvent(user.getId(), user.getUsername()));
        return IMatrixUserMapper.INSTANCE.from(user);
    }
}
