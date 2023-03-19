package com.matrixboot.user.center.infrastructure.common.command;

import jakarta.validation.constraints.NotNull;
import lombok.ToString;

import java.io.Serializable;

/**
 * create in 2023/3/19 22:34
 *
 * @author shishaodong
 * @version 0.0.1
 */
@ToString
public record RoleDeleteCommand(@NotNull Long id) implements Serializable {
}
