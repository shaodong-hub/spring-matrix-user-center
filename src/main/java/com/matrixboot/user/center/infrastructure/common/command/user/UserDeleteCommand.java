package com.matrixboot.user.center.infrastructure.common.command.user;

import jakarta.validation.constraints.NotNull;

/**
 * create in 2022/11/28 20:14
 *
 * @author shishaodong
 * @version 0.0.1
 */
public record UserDeleteCommand(@NotNull Long id) {
}
