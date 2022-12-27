package com.matrixboot.user.center.domain.repository;

import com.matrixboot.user.center.domain.entity.MatrixUserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * create in 2022/11/28 19:36
 *
 * @author shishaodong
 * @version 0.0.1
 */
public interface IMatrixUserRepository extends MongoRepository<MatrixUserEntity, String> {

    /**
     * findById
     *
     * @param id  id
     * @param clz clz
     * @param <T> clz
     * @return clz
     */
    <T> T findById(String id, Class<T> clz);

    /**
     * findByUsername
     *
     * @param username username
     * @param clz      clz
     * @param <T>      clz
     * @return clz
     */
    <T> T findByUsername(String username, Class<T> clz);

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
