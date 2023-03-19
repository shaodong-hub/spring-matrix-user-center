package com.matrixboot.user.center.application.service;

import com.matrixboot.user.center.domain.entity.role.MatrixRoleEntity;
import com.matrixboot.user.center.domain.repository.IMatrixRoleRepository;
import com.matrixboot.user.center.infrastructure.common.command.RoleCreateCommand;
import com.matrixboot.user.center.infrastructure.common.command.RoleDeleteCommand;
import com.matrixboot.user.center.infrastructure.common.command.RoleUpdateCommand;
import com.matrixboot.user.center.infrastructure.common.query.RoleQuery;
import com.matrixboot.user.center.infrastructure.common.result.RoleResult;
import com.matrixboot.user.center.infrastructure.exception.RoleNotFoundException;
import com.matrixboot.user.center.infrastructure.mapper.IMatrixRoleMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * create in 2023/3/19 22:07
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Validated
@CacheConfig(cacheNames = "common:role")
public class MatrixRoleService {

    private final IMatrixRoleRepository repository;

    /**
     * findByConditions
     *
     * @param query    query
     * @param pageable pageable
     * @return Page
     */
    @PreAuthorize("@check.hasPermission()")
    public Page<RoleResult> findByConditions(RoleQuery query, Pageable pageable) {
        return repository.findAllBy(pageable, RoleResult.class);
    }

    /**
     * findRoleById
     *
     * @param id id
     * @return RoleResult
     */
    @Cacheable(key = "'id:' + #id", unless = "null == #id")
    public RoleResult findRoleById(Long id) {
        log.info("findRoleById {}", id);
        var optional = repository.findById(id, RoleResult.class);
        return optional.orElseThrow(RoleNotFoundException::new);
    }

    /**
     * createRole
     *
     * @param command RoleCreateCommand
     * @return RoleResult
     */
    @CachePut(key = "'id:' + #result.id()", unless = "null == #result.id()")
    public RoleResult createRole(@NotNull @Valid RoleCreateCommand command) {
        var role = IMatrixRoleMapper.INSTANCE.from(command);
        var entity = repository.save(role);
        return IMatrixRoleMapper.INSTANCE.from(entity);
    }

    /**
     * updateRole
     *
     * @param command RoleUpdateCommand
     * @return RoleResult
     */
    @CachePut(key = "'id:' + #result.id()", unless = "null == #result.id()")
    public RoleResult updateRole(@NotNull @Valid RoleUpdateCommand command) {
        var optional = repository.findById(command.id());
        MatrixRoleEntity result = optional.orElseThrow(RoleNotFoundException::new);
        IMatrixRoleMapper.INSTANCE.update(command, result);
        MatrixRoleEntity entity = repository.save(result);
        return IMatrixRoleMapper.INSTANCE.from(entity);
    }

    /**
     * deleteRole
     *
     * @param command RoleDeleteCommand
     * @return RoleResult
     */
    @CacheEvict(key = "'id:' + #result.id()")
    public RoleResult deleteRole(@NotNull @Valid RoleDeleteCommand command) {
        var optional = repository.findById(command.id(), RoleResult.class);
        RoleResult result = optional.orElseThrow(RoleNotFoundException::new);
        repository.deleteById(command.id());
        return result;
    }
}
