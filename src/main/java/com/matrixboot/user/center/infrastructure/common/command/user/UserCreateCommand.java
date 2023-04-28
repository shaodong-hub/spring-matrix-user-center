package com.matrixboot.user.center.infrastructure.common.command.user;

import com.matrixboot.user.center.infrastructure.constraints.annotation.WeakPassword;
import org.hibernate.validator.constraints.Length;

/**
 * create in 2022/11/28 20:14
 *
 * @author shishaodong
 * @version 0.0.1
 */
public record UserCreateCommand(@Length(min = 1, max = 16) String username,
                                @WeakPassword String password,
                                @Length(min = 1, max = 16) String mobile,
                                @Length(min = 1, max = 16) String contacts,
                                @Length(min = 1, max = 16) String email) {
}
