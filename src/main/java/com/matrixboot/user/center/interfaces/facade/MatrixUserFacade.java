package com.matrixboot.user.center.interfaces.facade;

import com.matrixboot.user.center.application.service.user.MatrixUserService;
import com.matrixboot.user.center.infrastructure.common.Result;
import com.matrixboot.user.center.infrastructure.common.command.user.UserCreateCommand;
import com.matrixboot.user.center.infrastructure.common.command.user.UserDeleteCommand;
import com.matrixboot.user.center.infrastructure.common.command.user.UserUpdateCommand;
import com.matrixboot.user.center.infrastructure.common.result.UserResult;
import lombok.RequiredArgsConstructor;
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

    private final MatrixUserService userService;

    @GetMapping("/user/id/{id}")
    public Result<UserResult> findUserById(@PathVariable Long id) {
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

    @DeleteMapping("/user")
    public Result<UserResult> deleteUserById(@RequestBody UserDeleteCommand command) {
        return Result.success(userService.deleteUserById(command));
    }
}
