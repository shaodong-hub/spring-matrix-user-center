package com.matrixboot.user.center.infrastructure.common.command.user;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * create in 2023/3/8 16:55
 *
 * @author shishaodong
 * @version 0.0.1
 */
public record UpdatePasswordCommand(@NotNull Long id,
                                    @Length(min = 6, max = 16) String password) {
}
