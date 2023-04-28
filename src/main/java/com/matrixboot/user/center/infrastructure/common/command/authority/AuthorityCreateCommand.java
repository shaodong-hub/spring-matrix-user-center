package com.matrixboot.user.center.infrastructure.common.command.authority;

import org.hibernate.validator.constraints.Length;

/**
 * create in 2023/3/19 22:08
 *
 * @author shishaodong
 * @version 0.0.1
 */
public record AuthorityCreateCommand(@Length(min = 1, max = 16) String authorityName,
                                     @Length(min = 1, max = 16) String authority) {
}
