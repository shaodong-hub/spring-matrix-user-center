package com.matrixboot.user.center.interfaces.facade;

import com.matrixboot.user.center.application.service.UserService;
import com.matrixboot.user.center.domain.repository.IUserRepository;
import com.matrixboot.user.center.infrastructure.common.Result;
import com.matrixboot.user.center.infrastructure.common.command.UserCreateCommand;
import com.matrixboot.user.center.infrastructure.common.command.UserDeleteCommand;
import com.matrixboot.user.center.infrastructure.common.query.UserIdQuery;
import com.matrixboot.user.center.infrastructure.common.query.UserQuery;
import com.matrixboot.user.center.infrastructure.common.query.UserUsernameQuery;
import com.matrixboot.user.center.infrastructure.common.result.UserResultVO;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
public class UserFacade {

    private final IUserRepository repository;

    private final UserService userService;


    @GetMapping("/users")
    public Result<Page<UserResultVO>> findByCondition(@NotNull UserQuery query, Pageable pageable) {
        var page = repository.findAllByUsernameStartsWith(query.username(), pageable, UserResultVO.class);
        return Result.success(page);
    }

    @GetMapping("/user/id")
    public Result<UserResultVO> findUserByUsername(UserIdQuery query) {
        return Result.success(userService.findUserById(query));
    }

    @GetMapping("/user/username")
    public Result<UserResultVO> findUserByUsername(UserUsernameQuery query) {
        return Result.success(userService.findUserByUsername(query));
    }

    @PostMapping("/user")
    public Result<UserResultVO> createUser(UserCreateCommand command) {
        return Result.success(userService.createUser(command));
    }

    @DeleteMapping("/user/id")
    public Result<UserResultVO> deleteUserById(@RequestBody UserDeleteCommand command) {
        return Result.success(userService.deleteUserById(command));
    }
}
