package com.matrixboot.user.center.infrastructure.bus;

/**
 * create in 2023/4/25 21:23
 *
 * @author shishaodong
 * @version 0.0.1
 */
public interface IEventBus {

    void publishEvent(Object event);

}
