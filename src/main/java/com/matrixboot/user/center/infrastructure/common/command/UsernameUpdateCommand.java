package com.matrixboot.user.center.infrastructure.common.command;

import lombok.ToString;

import java.io.Serializable;

/**
 * create in 2023/3/8 16:55
 *
 * @author shishaodong
 * @version 0.0.1
 */
@ToString
public record UsernameUpdateCommand(Long id, String username) implements Serializable {
}
