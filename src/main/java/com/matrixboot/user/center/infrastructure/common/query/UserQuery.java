package com.matrixboot.user.center.infrastructure.common.query;

import org.apache.commons.lang3.StringUtils;

/**
 * create in 2022/11/28 13:24
 *
 * @author shishaodong
 * @version 0.0.1
 */
public record UserQuery(String username) {

    public String getUsername() {
        return StringUtils.isBlank(username()) ? "" : username();
    }

}
