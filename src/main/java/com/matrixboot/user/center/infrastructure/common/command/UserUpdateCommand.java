package com.matrixboot.user.center.infrastructure.common.command;

import jakarta.validation.constraints.NotNull;
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
public record UserUpdateCommand(@NotNull Long id,
                                @Length(max = 16) String username,
                                @Length(max = 16) String password,
                                @Length(max = 16) String mobile,
                                @Length(max = 16) String contacts,
                                @Length(max = 16) String email) implements Serializable {
}
