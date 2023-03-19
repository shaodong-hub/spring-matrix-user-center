package com.matrixboot.user.center.infrastructure.common.command;

import jakarta.validation.constraints.NotBlank;
import lombok.ToString;

import java.io.Serializable;

/**
 * create in 2022/11/28 20:14
 *
 * @author shishaodong
 * @version 0.0.1
 */
@ToString
public record UserCreateCommand(@NotBlank String username,
                                @NotBlank String password,
                                @NotBlank String mobile,
                                @NotBlank String contacts,
                                @NotBlank String email) implements Serializable {
}
