package com.matrixboot.user.center.infrastructure.common.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * create in 2022/11/28 20:14
 *
 * @author shishaodong
 * @version 0.0.1
 */
public record UserUpdateCommand(@NotNull Long id,
                                @NotBlank String username,
                                @NotBlank String password,
                                @NotBlank String mobile,
                                @NotBlank String contacts,
                                @NotBlank String email) {
}
