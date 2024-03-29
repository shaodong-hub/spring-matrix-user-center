package com.matrixboot.user.center.infrastructure.common.command.role;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * create in 2023/3/19 22:26
 *
 * @author shishaodong
 * @version 0.0.1
 */
public record RoleUpdateCommand(@NotNull Long id,
                                @Length(min = 1, max = 16) String roleName,
                                @Length(min = 1, max = 16) String roleCode) {
}
