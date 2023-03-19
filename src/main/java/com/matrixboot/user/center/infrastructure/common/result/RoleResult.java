package com.matrixboot.user.center.infrastructure.common.result;

import java.io.Serializable;

/**
 * create in 2023/3/19 22:08
 *
 * @author shishaodong
 * @version 0.0.1
 */
public record RoleResult(Long id, String roleName, String roleCode) implements Serializable {
}
