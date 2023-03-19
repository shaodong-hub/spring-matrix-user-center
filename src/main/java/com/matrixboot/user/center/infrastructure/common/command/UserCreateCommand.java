package com.matrixboot.user.center.infrastructure.common.command;

import com.matrixboot.user.center.infrastructure.constraints.annotation.WeakPassword;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * create in 2022/11/28 20:14
 *
 * @author shishaodong
 * @version 0.0.1
 */
@ToString
public record UserCreateCommand(@Length(min = 1, max = 16) String username,
                                @WeakPassword String password,
                                @Length(min = 1, max = 16) String mobile,
                                @Length(min = 1, max = 16) String contacts,
                                @Length(min = 1, max = 16) String email) implements Serializable {
}
