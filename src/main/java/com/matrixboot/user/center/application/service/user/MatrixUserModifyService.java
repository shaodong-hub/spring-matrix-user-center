package com.matrixboot.user.center.application.service.user;

import com.matrixboot.user.center.domain.repository.IMatrixUserRepository;
import com.matrixboot.user.center.infrastructure.bus.IEventBus;
import com.matrixboot.user.center.infrastructure.common.command.user.UpdateContactCommand;
import com.matrixboot.user.center.infrastructure.common.command.user.UpdateEmailCommand;
import com.matrixboot.user.center.infrastructure.common.command.user.UpdateMobileCommand;
import com.matrixboot.user.center.infrastructure.common.command.user.UpdatePasswordCommand;
import com.matrixboot.user.center.infrastructure.common.command.user.UpdateUsernameCommand;
import com.matrixboot.user.center.infrastructure.common.result.UserResult;
import com.matrixboot.user.center.infrastructure.exception.UserIdNotFountException;
import com.matrixboot.user.center.infrastructure.mapper.IMatrixUserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * create in 2023/4/28 15:52
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Validated
@CacheConfig(cacheNames = "common:user")
public class MatrixUserModifyService {

    private final IEventBus eventBus;

    private final IMatrixUserRepository repository;

    @Caching(put = {
            @CachePut(key = "'id:' + #result.id()", unless = "null == #result.id()"),
            @CachePut(key = "'username:' + #result.username()", unless = "null == #result.username()"),
            @CachePut(key = "'mobile:' + #result.mobile()", unless = "null == #result.mobile()"),
            @CachePut(key = "'email:' + #result.email()", unless = "null == #result.email()"),
    })
    public UserResult updateUsername(@NotNull @Valid UpdateUsernameCommand command) {
        var optional = repository.findById(command.id());
        var entity = optional.orElseThrow(() -> new UserIdNotFountException(command.id()));
        entity.updateUsername(command);
        var save = repository.save(entity);
        eventBus.publishEvent(command);
        return IMatrixUserMapper.INSTANCE.from(save);
    }

    @Caching(put = {
            @CachePut(key = "'id:' + #result.id()", unless = "null == #result.id()"),
            @CachePut(key = "'username:' + #result.username()", unless = "null == #result.username()"),
            @CachePut(key = "'mobile:' + #result.mobile()", unless = "null == #result.mobile()"),
            @CachePut(key = "'email:' + #result.email()", unless = "null == #result.email()"),
    })
    public UserResult updateMobile(@NotNull @Valid UpdateMobileCommand command) {
        var optional = repository.findById(command.id());
        var entity = optional.orElseThrow(() -> new UserIdNotFountException(command.id()));
        entity.updateMobile(command);
        var save = repository.save(entity);
        eventBus.publishEvent(command);
        return IMatrixUserMapper.INSTANCE.from(save);
    }

    @Caching(put = {
            @CachePut(key = "'id:' + #result.id()", unless = "null == #result.id()"),
            @CachePut(key = "'username:' + #result.username()", unless = "null == #result.username()"),
            @CachePut(key = "'mobile:' + #result.mobile()", unless = "null == #result.mobile()"),
            @CachePut(key = "'email:' + #result.email()", unless = "null == #result.email()"),
    })
    public UserResult updatePassword(@NotNull @Valid UpdatePasswordCommand command) {
        var optional = repository.findById(command.id());
        var entity = optional.orElseThrow(() -> new UserIdNotFountException(command.id()));
        entity.updatePassword(command);
        var save = repository.save(entity);
        eventBus.publishEvent(command);
        return IMatrixUserMapper.INSTANCE.from(save);
    }


    @Caching(put = {
            @CachePut(key = "'id:' + #result.id()", unless = "null == #result.id()"),
            @CachePut(key = "'username:' + #result.username()", unless = "null == #result.username()"),
            @CachePut(key = "'mobile:' + #result.mobile()", unless = "null == #result.mobile()"),
            @CachePut(key = "'email:' + #result.email()", unless = "null == #result.email()"),
    })
    public UserResult updateContact(@NotNull @Valid UpdateContactCommand command) {
        var optional = repository.findById(command.id());
        var entity = optional.orElseThrow(() -> new UserIdNotFountException(command.id()));
        entity.updateContact(command);
        var save = repository.save(entity);
        eventBus.publishEvent(command);
        return IMatrixUserMapper.INSTANCE.from(save);
    }

    @Caching(put = {
            @CachePut(key = "'id:' + #result.id()", unless = "null == #result.id()"),
            @CachePut(key = "'username:' + #result.username()", unless = "null == #result.username()"),
            @CachePut(key = "'mobile:' + #result.mobile()", unless = "null == #result.mobile()"),
            @CachePut(key = "'email:' + #result.email()", unless = "null == #result.email()"),
    })
    public UserResult updateEmail(@NotNull @Valid UpdateEmailCommand command) {
        var optional = repository.findById(command.id());
        var entity = optional.orElseThrow(() -> new UserIdNotFountException(command.id()));
        entity.updateEmail(command);
        var save = repository.save(entity);
        eventBus.publishEvent(command);
        return IMatrixUserMapper.INSTANCE.from(save);
    }
}
