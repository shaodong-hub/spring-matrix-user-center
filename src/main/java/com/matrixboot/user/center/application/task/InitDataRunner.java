package com.matrixboot.user.center.application.task;

import com.matrixboot.user.center.domain.entity.UserEntity;
import com.matrixboot.user.center.domain.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * create in 2022/11/28 19:56
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Component
@AllArgsConstructor
public class InitDataRunner implements CommandLineRunner {

    private static final int COUNT = 100;

    private final IUserRepository repository;

    @Override
    public void run(String... args) {
        if (repository.count() != 0) {
            return;
        }
        for (int i = 0; i < COUNT; i++) {
            var user = new UserEntity();
            user.setUsername("uname-" + i);
            user.setPassword("passwd-" + i);
            user.setMobile("mobile-" + i);
            user.setContact("contact-" + i);
            user.setEmail("email-" + i);
            repository.save(user);
        }
    }
}
