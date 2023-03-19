package com.matrixboot.user.center.infrastructure.common.command;

import lombok.ToString;
import org.jetbrains.annotations.NotNull;

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
