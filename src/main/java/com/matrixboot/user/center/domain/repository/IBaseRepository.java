package com.matrixboot.user.center.domain.repository;

import java.util.Optional;

/**
 * create in 2023/3/19 22:13
 *
 * @author shishaodong
 * @version 0.0.1
 */
public interface IBaseRepository {

    /**
     * findById
     *
     * @param id  id
     * @param clz clz
     * @param <T> clz
     * @return clz
     */
    <T> Optional<T> findById(Long id, Class<T> clz);
}
