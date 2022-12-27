package com.matrixboot.user.center.infrastructure.common.event;

/**
 * create in 2022/11/28 23:13
 *
 * @author shishaodong
 * @version 0.0.1
 */
public record UserCreateEvent(String id, String username) implements IMatrixUserEvent{
}
