package com.matrixboot.user.center.infrastructure.common.query;

import lombok.ToString;

import java.io.Serializable;

/**
 * create in 2022/11/28 13:24
 *
 * @author shishaodong
 * @version 0.0.1
 */
@ToString
public record UserUsernameQuery(String username) implements Serializable {
}
