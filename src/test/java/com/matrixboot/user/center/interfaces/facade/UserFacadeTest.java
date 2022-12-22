package com.matrixboot.user.center.interfaces.facade;

import com.matrixboot.user.center.domain.repository.IMatrixUserRepository;
import io.micrometer.core.instrument.util.IOUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
@Sql("classpath:sql/matrix_user.sql")
@AutoConfigureRestDocs
class UserFacadeTest {

    @Resource
    MockMvc mvc;

    @Value("classpath:json/UserCreateCommand.json")
    org.springframework.core.io.Resource userCreateCommand;

    @Value("classpath:json/UserUpdateCommand.json")
    org.springframework.core.io.Resource userUpdateCommand;

    @Value("classpath:json/UserDeleteCommand.json")
    org.springframework.core.io.Resource userDeleteCommand;

    @Resource
    IMatrixUserRepository repository;

    @AfterEach
    void afterEach(){
        repository.deleteAll();
    }

    @Test
    void findByCondition() throws Exception {
        this.mvc.perform(get("/users").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("list-users"))
        ;
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
        this.mvc.perform(get("/user/username/test_username1").accept(MediaType.APPLICATION_JSON))
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