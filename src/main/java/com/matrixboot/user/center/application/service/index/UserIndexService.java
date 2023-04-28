package com.matrixboot.user.center.application.service.index;

import com.matrixboot.user.center.application.service.user.MatrixUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * create in 2023/3/19 20:53
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@RequiredArgsConstructor
@Service
@PreAuthorize("permitAll()")
public class UserIndexService {

    public final MatrixUserService service;


}
