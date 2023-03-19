package com.matrixboot.user.center.interfaces.facade;

import com.matrixboot.user.center.application.service.MatrixRoleService;
import com.matrixboot.user.center.infrastructure.common.Result;
import com.matrixboot.user.center.infrastructure.common.command.RoleCreateCommand;
import com.matrixboot.user.center.infrastructure.common.command.RoleDeleteCommand;
import com.matrixboot.user.center.infrastructure.common.command.RoleUpdateCommand;
import com.matrixboot.user.center.infrastructure.common.query.RoleQuery;
import com.matrixboot.user.center.infrastructure.common.result.RoleResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * create in 2023/3/19 22:49
 *
 * @author shishaodong
 * @version 0.0.1
 */
@RestController
@RequiredArgsConstructor
public class MatrixRoleFacade {

    private final MatrixRoleService service;

    @GetMapping("/role")
    public Result<Page<RoleResult>> findByConditions(RoleQuery query, Pageable pageable) {
        return Result.success(service.findByConditions(query, pageable));
    }

    @GetMapping("/role/{id}")
    public Result<RoleResult> findRoleById(@PathVariable Long id) {
        return Result.success(service.findRoleById(id));
    }

    @PostMapping("/role")
    public Result<RoleResult> createRole(@RequestBody RoleCreateCommand command) {
        return Result.success(service.createRole(command));
    }

    @PutMapping("/role")
    public Result<RoleResult> updateRole(@RequestBody RoleUpdateCommand command) {
        return Result.success(service.updateRole(command));
    }

    @DeleteMapping("/role")
    public Result<RoleResult> deleteRole(@RequestBody RoleDeleteCommand command) {
        return Result.success(service.deleteRole(command));
    }


}
