package com.matrixboot.user.center.application.service;

import com.matrixboot.user.center.domain.repository.IMatrixUserRepository;
import com.matrixboot.user.center.infrastructure.common.query.UserQuery;
import com.matrixboot.user.center.infrastructure.common.result.UserResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * create in 2023/3/11 01:29
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Validated
public class UserManageService {

    private final IMatrixUserRepository repository;

    public Page<UserResult> findByConditions(@NotNull UserQuery query, Pageable pageable) {
        return repository.findAllByUsernameStartsWith(query.getUsername() + "", pageable, UserResult.class);
    }

}
