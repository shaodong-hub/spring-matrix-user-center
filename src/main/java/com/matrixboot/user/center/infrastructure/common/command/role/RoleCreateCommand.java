package com.matrixboot.user.center.infrastructure.common.command.role;

import org.hibernate.validator.constraints.Length;

/**
 * create in 2023/3/19 22:07
 *
 * @author shishaodong
 * @version 0.0.1
 */
public record RoleCreateCommand(@Length(min = 1, max = 16) String roleName,
                                @Length(min = 1, max = 16) String roleCode) {
}
