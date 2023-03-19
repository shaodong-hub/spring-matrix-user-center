package com.matrixboot.user.center.domain.repository;

import com.matrixboot.user.center.domain.entity.user.MatrixUserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * create in 2022/11/28 19:36
 *
 * @author shishaodong
 * @version 0.0.1
 */
public interface IMatrixUserRepository extends JpaRepository<MatrixUserEntity, Long>, IBaseRepository {

    /**
     * findByUsername
     *
     * @param username username
     * @param clz      clz
     * @param <T>      clz
     * @return clz
     */
    <T> Optional<T> findByUsername(String username, Class<T> clz);

    /**
     * findByMobile
     *
     * @param mobile mobile
     * @param clz    clz
     * @param <T>    clz
     * @return clz
     */
    <T> Optional<T> findByMobile(String mobile, Class<T> clz);

    /**
     * findByEmail
     *
     * @param email email
     * @param clz   clz
     * @param <T>   clz
     * @return clz
     */
    <T> Optional<T> findByEmail(String email, Class<T> clz);

    /**
     * findAllByUsernameStartsWith
     *
     * @param username username
     * @param pageable pageable
     * @param clz      clz
     * @param <T>      clz
     * @return Page clz
     */
    <T> Page<T> findAllByUsernameStartsWith(String username, Pageable pageable, Class<T> clz);
}
