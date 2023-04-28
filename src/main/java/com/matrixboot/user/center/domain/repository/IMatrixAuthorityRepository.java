package com.matrixboot.user.center.domain.repository;

import com.matrixboot.user.center.domain.entity.authority.MatrixAuthorityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * create in 2023/3/19 22:11
 *
 * @author shishaodong
 * @version 0.0.1
 */
public interface IMatrixAuthorityRepository extends JpaRepository<MatrixAuthorityEntity, Long>, IBaseRepository {

    <T> Page<T> findAllBy(Pageable pageable);

    <T> List<T> findAllByIdIn(List<Long> ids);
}
