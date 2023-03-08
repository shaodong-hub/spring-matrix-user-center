package com.matrixboot.user.center.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matrixboot.user.center.domain.repository.IMatrixUserRepository;
import com.matrixboot.user.center.infrastructure.common.command.UserCreateCommand;
import com.matrixboot.user.center.infrastructure.common.command.UserDeleteCommand;
import com.matrixboot.user.center.infrastructure.common.command.UserUpdateCommand;
import com.matrixboot.user.center.infrastructure.common.result.UserResult;
import io.micrometer.core.instrument.util.IOUtils;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


/**
 * create in 2023/3/8 13:08
 *
 * @author shishaodong
 * @version 0.0.1
 */
@SpringBootTest
@ActiveProfiles("junit")
@Sql("classpath:sql/matrix_user.sql")
@SqlMergeMode(SqlMergeMode.MergeMode.OVERRIDE)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MatrixUserServiceTest {

    @Resource
    MatrixUserService userService;

    @Resource
    IMatrixUserRepository repository;

    @Resource
    ObjectMapper objectMapper;

    @Value("classpath:json/UserCreateCommand.json")
    org.springframework.core.io.Resource userCreateCommand;

    @Value("classpath:json/UserUpdateCommand.json")
    org.springframework.core.io.Resource userUpdateCommand;

    @AfterEach
    void afterEach(){
        repository.deleteAll();
    }

    @Test
    void findByConditions() {
    }

    @Test
    void findUserById() {
        UserResult result = userService.findUserById(1L);
        Assertions.assertEquals(1L, result.id());
        Assertions.assertEquals("test_username1", result.username());
    }

    @Test
    void findUserByUsername() {
        UserResult result = userService.findUserByUsername("test_username1");
        Assertions.assertEquals(1L, result.id());
        Assertions.assertEquals("test_username1", result.username());
    }

    @Test
    void findByMobile() {
        UserResult result = userService.findByMobile("18812345671");
        Assertions.assertEquals(1L, result.id());
        Assertions.assertEquals("test_username1", result.username());
    }

    @Test
    void findByEmail() {
        UserResult result = userService.findByEmail("spring-boot1@qq.com");
        Assertions.assertEquals(1L, result.id());
        Assertions.assertEquals("test_username1", result.username());
    }
    @Test
    void createUser() throws IOException {
        String string = IOUtils.toString(userCreateCommand.getInputStream(), StandardCharsets.UTF_8);
        UserCreateCommand command = objectMapper.readValue(string, UserCreateCommand.class);
        UserResult result = userService.createUser(command);
        Assertions.assertEquals(command.username(), result.username());
    }

    @Test
    void updateUser() throws IOException {
        String string = IOUtils.toString(userUpdateCommand.getInputStream(), StandardCharsets.UTF_8);
        UserUpdateCommand command = objectMapper.readValue(string, UserUpdateCommand.class);
        UserResult result = userService.updateUser(command);
        Assertions.assertEquals(command.id(), result.id());
        Assertions.assertEquals(command.username(), result.username());
    }

    @Test
    void deleteUserById() {
        UserResult result = userService.deleteUserById(new UserDeleteCommand(1L));
        Assertions.assertEquals(1L, result.id());
        Assertions.assertEquals("test_username1", result.username());
    }


}