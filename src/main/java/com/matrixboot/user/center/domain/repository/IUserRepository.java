package com.matrixboot.user.center.domain.repository;

import com.matrixboot.user.center.domain.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * create in 2022/11/28 19:36
 *
 * @author shishaodong
 * @version 0.0.1
 */
public interface IUserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

    /**
     * findById
     *
     * @param id  id
     * @param clz clz
     * @param <T> clz
     * @return clz
     */
    <T> T findById(long id, Class<T> clz);

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

    /**
     * updateLastLoginDate
     *
     * @param id id
     */
    @Query("UPDATE UserEntity AS user SET user.lastLoginDate = current_timestamp WHERE user.id = #{id}")
    void updateLastLoginDateById(long id);
}
