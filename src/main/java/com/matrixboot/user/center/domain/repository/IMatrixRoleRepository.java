package com.matrixboot.user.center.domain.repository;

import com.matrixboot.user.center.domain.entity.role.MatrixRoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * create in 2023/3/19 22:11
 *
 * @author shishaodong
 * @version 0.0.1
 */
public interface IMatrixRoleRepository extends JpaRepository<MatrixRoleEntity, Long>, IBaseRepository {

    /**
     * findAllByUsernameStartsWith
     *
     * @param pageable pageable
     * @param clz      clz
     * @param <T>      clz
     * @return Page clz
     */
    <T> Page<T> findAllBy(Pageable pageable, Class<T> clz);
}
