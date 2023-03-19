package com.matrixboot.user.center.infrastructure.common.command;

import jakarta.validation.constraints.NotNull;
import lombok.ToString;

import java.io.Serializable;

/**
 * create in 2022/11/28 20:14
 *
 * @author shishaodong
 * @version 0.0.1
 */
@ToString
public record UserDeleteCommand(@NotNull Long id) implements Serializable {
}
