package com.matrixboot.user.center.application.service;

import com.matrixboot.user.center.domain.repository.IMatrixAuthorityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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



}
