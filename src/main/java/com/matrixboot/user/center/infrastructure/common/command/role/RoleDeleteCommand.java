package com.matrixboot.user.center.infrastructure.common.command.role;

import jakarta.validation.constraints.NotNull;

/**
 * create in 2023/3/19 22:34
 *
 * @author shishaodong
 * @version 0.0.1
 */
public record RoleDeleteCommand(@NotNull Long id) {
}
