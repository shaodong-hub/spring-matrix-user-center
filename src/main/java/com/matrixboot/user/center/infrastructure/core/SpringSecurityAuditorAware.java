package com.matrixboot.user.center.infrastructure.core;

import com.matrixboot.user.center.domain.entity.user.MatrixUserEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * create in 2023/4/25 21:56
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<MatrixUserEntity> {

    @Override
    public @NotNull Optional<MatrixUserEntity> getCurrentAuditor() {
        return Optional.empty();
    }
}
