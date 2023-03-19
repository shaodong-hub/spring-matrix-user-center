package com.matrixboot.user.center.domain.repository;

import com.matrixboot.user.center.domain.entity.authority.MatrixAuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * create in 2023/3/19 22:11
 *
 * @author shishaodong
 * @version 0.0.1
 */
public interface IMatrixAuthorityRepository extends JpaRepository<MatrixAuthorityEntity, Long>, IBaseRepository {
}
