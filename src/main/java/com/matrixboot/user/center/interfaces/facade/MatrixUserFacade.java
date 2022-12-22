package com.matrixboot.user.center.interfaces.facade;

import com.matrixboot.user.center.application.service.MatrixUserService;
import com.matrixboot.user.center.domain.repository.IMatrixUserRepository;
import com.matrixboot.user.center.infrastructure.common.Result;
import com.matrixboot.user.center.infrastructure.common.command.UserCreateCommand;
import com.matrixboot.user.center.infrastructure.common.command.UserDeleteCommand;
import com.matrixboot.user.center.infrastructure.common.command.UserUpdateCommand;
import com.matrixboot.user.center.infrastructure.common.query.UserQuery;
import com.matrixboot.user.center.infrastructure.common.result.UserResult;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
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
 * create in 2022/11/28 13:21
 *
 * @author shishaodong
 * @version 0.0.1
 */
@RestController
@RequiredArgsConstructor
public class MatrixUserFacade {

    private final IMatrixUserRepository repository;

    private final MatrixUserService userService;

    @GetMapping("/users")
    public Result<Page<UserResult>> findByCondition(@NotNull UserQuery query, Pageable pageable) {
        var page = repository.findAllByUsernameStartsWith(query.username(), pageable, UserResult.class);
        return Result.success(page);
    }

    @GetMapping("/user/id/{id}")
    public Result<UserResult> findUserByUsername(@PathVariable Long id) {
        return Result.success(userService.findUserById(id));
    }

    @GetMapping("/user/username/{username}")
    public Result<UserResult> findUserByUsername(@PathVariable String username) {
        return Result.success(userService.findUserByUsername(username));
    }

    @PostMapping("/user")
    public Result<UserResult> createUser(@RequestBody UserCreateCommand command) {
        return Result.success(userService.createUser(command));
    }

    @PutMapping("/user")
    public Result<UserResult> updateUser(@RequestBody UserUpdateCommand command) {
        return Result.success(userService.updateUser(command));
    }

    @DeleteMapping("/user/id")
    public Result<UserResult> deleteUserById(@RequestBody UserDeleteCommand command) {
        return Result.success(userService.deleteUserById(command));
    }
}
