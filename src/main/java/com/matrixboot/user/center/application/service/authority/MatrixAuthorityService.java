package com.matrixboot.user.center.application.service.authority;

import com.matrixboot.user.center.domain.repository.IMatrixAuthorityRepository;
import com.matrixboot.user.center.infrastructure.common.command.authority.AuthorityCreateCommand;
import com.matrixboot.user.center.infrastructure.common.view.AuthorityView;
import com.matrixboot.user.center.infrastructure.common.view.IAuthorityView;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

/**
 * create in 2023/3/19 22:45
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Validated
@CacheConfig(cacheNames = "common:authority")
public class MatrixAuthorityService {

    private final IMatrixAuthorityRepository repository;

    public Page<IAuthorityView> findAll(Pageable pageable) {
        return repository.findAllBy(pageable);
    }

    /**
     * findAllByIds
     *
     * @param list List
     * @return List
     */
    public List<AuthorityView> findAllByIds(List<Long> list) {
        return repository.findAllByIdIn(list);
    }

    public Optional<AuthorityView> findById(long id) {
        return repository.findById(id, AuthorityView.class);
    }

    public AuthorityView createAuthority(@Valid AuthorityCreateCommand command) {
        return null;
    }

}
