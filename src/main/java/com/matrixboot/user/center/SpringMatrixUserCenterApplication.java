package com.matrixboot.user.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author shishaodong
 */
@EnableAsync
@EnableCaching
@EnableScheduling
@EnableMongoRepositories
@EnableMongoAuditing
@SpringBootApplication
public class SpringMatrixUserCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMatrixUserCenterApplication.class, args);
    }

}
