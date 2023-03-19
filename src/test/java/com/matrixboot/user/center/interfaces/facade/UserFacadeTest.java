package com.matrixboot.user.center.interfaces.facade;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matrixboot.user.center.domain.entity.user.MatrixUserEntity;
import com.matrixboot.user.center.domain.repository.IMatrixUserRepository;
import io.micrometer.core.instrument.util.IOUtils;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * create in 2022/11/28 13:25
 *
 * @author shishaodong
 * @version 0.0.1
 */
@SpringBootTest
@ActiveProfiles("junit")
@AutoConfigureMockMvc
//@Sql("classpath:sql/matrix_user.sql")
class UserFacadeTest {

    @Resource
    MockMvc mvc;

    @Value("classpath:json/UserCreateCommand.json")
    org.springframework.core.io.Resource userCreateCommand;

    @Value("classpath:json/UserUpdateCommand.json")
    org.springframework.core.io.Resource userUpdateCommand;

    @Value("classpath:json/UserDeleteCommand.json")
    org.springframework.core.io.Resource userDeleteCommand;

    @Value("classpath:db/matrix_user.json")
    org.springframework.core.io.Resource data;

    @Resource
    IMatrixUserRepository repository;

    @Resource
    ObjectMapper objectMapper;

    private static final TypeReference<List<MatrixUserEntity>> TYPE_REFERENCE = new TypeReference<>() {
    };

    @BeforeEach
    void beforeEach() throws IOException {
        String string = IOUtils.toString(data.getInputStream(), StandardCharsets.UTF_8);
        List<MatrixUserEntity> list = objectMapper.readValue(string, TYPE_REFERENCE);
        repository.saveAll(list);
    }

    @AfterEach
    void afterEach() {
        repository.deleteAll();
    }

    @Test
    void findByCondition() throws Exception {
        MvcResult mvcResult = this.mvc.perform(get("/users?username=test").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.totalElements").value(2))
                .andDo(document("list-users"))
                .andReturn();
        String s = mvcResult.getResponse().getContentAsString();
        System.out.println(s);
    }

    @Test
    void findUserByUsername1() throws Exception {
        this.mvc.perform(get("/user/id/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value("000000"))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.username").value("test_username1"))
                .andDo(document("list-user"));
    }

    @Test
    void findUserByUsername2() throws Exception {
        this.mvc.perform(get("/username/test_username1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("000000"))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.username").value("test_username1"))
                .andDo(document("list-user"));
    }


    @Test
    void createUser() throws Exception {
        String data = IOUtils.toString(userCreateCommand.getInputStream(), StandardCharsets.UTF_8);
        this.mvc.perform(post("/user")
                        .content(data)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("000000"))
                .andDo(document("list-user"));

    }

    @Test
    void updateUser() throws Exception {
        String data = IOUtils.toString(userUpdateCommand.getInputStream(), StandardCharsets.UTF_8);
        this.mvc.perform(put("/user")
                        .content(data)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("000000"))
                .andDo(document("list-user"));
    }


    @Test
    void deleteUserById() throws Exception {
        String data = IOUtils.toString(userDeleteCommand.getInputStream(), StandardCharsets.UTF_8);
        this.mvc.perform(delete("/user/id")
                        .content(data)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("000000"))
                .andDo(document("list-user"));
    }
}