package com.matrixboot.user.center.infrastructure.bus;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * create in 2023/4/25 21:23
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Component
@RequiredArgsConstructor
public class SpringEventBus implements IEventBus {

    private final ApplicationContext context;

    @Override
    public void publishEvent(Object event) {
        context.publishEvent(event);
    }
}
