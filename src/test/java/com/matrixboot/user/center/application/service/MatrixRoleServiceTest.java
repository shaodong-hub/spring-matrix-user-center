package com.matrixboot.user.center.application.service;

import com.matrixboot.user.center.domain.repository.IMatrixRoleRepository;
import com.matrixboot.user.center.infrastructure.common.command.RoleCreateCommand;
import com.matrixboot.user.center.infrastructure.common.command.RoleDeleteCommand;
import com.matrixboot.user.center.infrastructure.common.command.RoleUpdateCommand;
import com.matrixboot.user.center.infrastructure.common.query.RoleQuery;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;

/**
 * create in 2023/3/19 22:57
 *
 * @author shishaodong
 * @version 0.0.1
 */
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
@Rollback(value = false)
@SpringBootTest
@ActiveProfiles("junit")
@Sql("classpath:sql/matrix_role.sql")
@SqlMergeMode(SqlMergeMode.MergeMode.OVERRIDE)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MatrixRoleServiceTest {

    @Resource
    MatrixRoleService service;

    @Test
    @Order(1)
    void findByConditions() {
        var page = service.findByConditions(new RoleQuery(), Pageable.unpaged());
        Assertions.assertEquals(2L, page.getTotalElements());
    }

    @Test
    @Order(2)
    void findRoleById() {
        var result = service.findRoleById(1L);
        Assertions.assertEquals("role_name_1", result.roleName());
        Assertions.assertEquals("ROLE_CODE_1", result.roleCode());
    }

    @Test
    @Order(3)
    void createRole() {
        String role = "ROLE_" + RandomStringUtils.randomAlphabetic(6);
        var result = service.createRole(new RoleCreateCommand("TestRole", role));
        Assertions.assertEquals("TestRole", result.roleName());
        Assertions.assertEquals(role, result.roleCode());
    }

    @Test
    @Order(4)
    void updateRole() {
        var role = "ROLE_" + RandomStringUtils.randomAlphabetic(6);
        var result = service.updateRole(new RoleUpdateCommand(1L, "TEST_UPDATE_NAME", role));
        Assertions.assertEquals("TEST_UPDATE_NAME", result.roleName());
        Assertions.assertEquals(role, result.roleCode());

    }

    @Test
    @Order(5)
    void deleteRole() {
        var result = service.deleteRole(new RoleDeleteCommand(1L));
        Assertions.assertEquals("role_name_1", result.roleName());
        Assertions.assertEquals("ROLE_CODE_1", result.roleCode());
    }


    @Resource
    IMatrixRoleRepository repository;

    @AfterEach
    void afterEach() {
        repository.deleteAll();
    }

}