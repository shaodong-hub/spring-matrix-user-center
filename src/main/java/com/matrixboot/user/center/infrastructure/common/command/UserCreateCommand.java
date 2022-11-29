package com.matrixboot.user.center.infrastructure.common.command;

/**
 * create in 2022/11/28 20:14
 *
 * @author shishaodong
 * @version 0.0.1
 */
public record UserCreateCommand(String username,
                                String password,
                                String mobile,
                                String contact,
                                String email) {
}
