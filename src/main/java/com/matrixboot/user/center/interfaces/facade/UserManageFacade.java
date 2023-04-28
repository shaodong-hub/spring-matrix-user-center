package com.matrixboot.user.center.interfaces.facade;

import com.matrixboot.user.center.application.service.user.MatrixUserService;
import com.matrixboot.user.center.infrastructure.common.Result;
import com.matrixboot.user.center.infrastructure.common.query.UserQuery;
import com.matrixboot.user.center.infrastructure.common.result.UserResult;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * create in 2023/3/11 01:28
 *
 * @author shishaodong
 * @version 0.0.1
 */
@RestController
@RequiredArgsConstructor
public class UserManageFacade {

    private final MatrixUserService userService;

    @GetMapping("/users")
    public Result<Page<UserResult>> findByCondition(@NotNull UserQuery query, Pageable pageable) {
        return Result.success(userService.findByConditions(query, pageable));
    }

}
