package com.matrixboot.user.center.infrastructure.exception.handler;

import com.matrixboot.user.center.infrastructure.common.Result;
import com.matrixboot.user.center.infrastructure.exception.UserNotFountException;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.Serializable;

/**
 * create in 2022/11/28 22:04
 *
 * @author shishaodong
 * @version 0.0.1
 */
@RestControllerAdvice
public class UserNotFountExceptionHandler {

    /**
     * @param exception 业务异常
     * @return Result
     */
    @ExceptionHandler(UserNotFountException.class)
    public Result<Serializable> userGroupNotFountException(@NotNull UserNotFountException exception) {
        return Result.failure(exception.getLocalizedMessage() + exception.getId());
    }
}
