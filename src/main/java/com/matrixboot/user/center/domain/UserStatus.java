package com.matrixboot.user.center.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * create in 2023/3/11 02:20
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@Setter
@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class UserStatus implements Serializable {

    @Serial
    private static final long serialVersionUID = 8024908880563315371L;


    /**
     * 账户过期时间
     */
    @Column(name = "account_expired_date", columnDefinition = "DATETIME COMMENT '账号没有过期'")
    private LocalDateTime accountExpiredDate;

    /**
     * 账户锁定到的时间
     */
    @JsonIgnore
    @Column(name = "account_non_locked_date", columnDefinition = "DATETIME COMMENT '账号没有被锁定'")
    private LocalDateTime accountNonLockedDate;

    /**
     * 密码过期时间
     */
    @JsonIgnore
    @Column(name = "credentials_non_expired_date", columnDefinition = "DATETIME COMMENT '凭证没有过期'")
    private LocalDateTime credentialsNonExpiredDate;

    /**
     * 账户是否启用
     */
    @JsonIgnore
    @Column(name = "enabled", columnDefinition = "TINYINT DEFAULT 1 COMMENT '账号启用'")
    private Boolean enabled;
}
