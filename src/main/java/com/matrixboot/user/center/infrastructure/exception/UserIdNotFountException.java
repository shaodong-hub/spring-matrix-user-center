package com.matrixboot.user.center.infrastructure.exception;

import lombok.Getter;

import java.io.Serial;

/**
 * create in 2022/11/28 20:22
 *
 * @author shishaodong
 * @version 0.0.1
 */
public class UserIdNotFountException extends UserNotFountException{

    @Serial
    private static final long serialVersionUID = -1041772808948135766L;

    @Getter
    private Long id;

    public UserIdNotFountException(Long id) {
        this.id = id;
    }
}
