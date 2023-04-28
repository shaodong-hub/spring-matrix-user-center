package com.matrixboot.user.center.domain.service;

import com.matrixboot.user.center.domain.entity.authority.MatrixAuthorityEntity;
import com.matrixboot.user.center.domain.entity.role.MatrixRoleAuthorityEntity;
import com.matrixboot.user.center.domain.entity.role.MatrixRoleEntity;
import com.matrixboot.user.center.domain.repository.IMatrixAuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * create in 2023/4/25 21:01
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Service
@RequiredArgsConstructor
public class RoleAuthorityService {

    private final IMatrixAuthorityRepository authorityRepository;

    public void initRoleAuthority(@NotNull MatrixRoleEntity role) {
        Map<Long, MatrixRoleAuthorityEntity> map = role.getAuthorities();
        List<MatrixAuthorityEntity> list = map.values().stream()
                .map(x -> authorityRepository.findById(x.getRoleId()))
                .filter(Optional::isPresent).map(Optional::get)
                .toList();
        role.setAuthorityEntityList(list);
    }
}
