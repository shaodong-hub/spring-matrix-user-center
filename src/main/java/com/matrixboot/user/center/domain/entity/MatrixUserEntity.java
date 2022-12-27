package com.matrixboot.user.center.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.matrixboot.user.center.infrastructure.common.event.UserCreateEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.domain.DomainEvents;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

/**
 * create in 2022/11/28 19:09
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Getter
@Setter
@ToString
@Document(collection = "matrix_user")
@CompoundIndex(unique = true, name = "UK_USERNAME", def = "{'username':1}")
@CompoundIndex(unique = true, name = "UK_MOBILE", def = "{'mobile':1}")
@CompoundIndex(unique = true, name = "UK_EMAIL", def = "{'email':1}")
@CompoundIndex(name = "idx_region_age", def = "{'region':1,'age':1}")
public class MatrixUserEntity {

    /**
     * 用户的唯一 ID，与业务无关，自增
     */
    @Id
    private String id;

    /**
     * 用户名，也是唯一的
     */
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 手机号码，也是唯一的
     */
    private String mobile;

    /**
     * 联系人姓名
     */
    private String contacts;

    /**
     * 用户的邮箱，按道理也应该是唯一的
     */
    private String email;

    @CreatedDate
    @Field("created_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Field("last_modified_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModifiedDate;

    /**
     * 用户的最后登录时间
     */
    @Field("last_login_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginDate;

    /**
     * 账户过期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Field("account_expired_date")
    private LocalDateTime accountExpiredDate;

    /**
     * 账户锁定到的时间
     */
    @JsonIgnore
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Field("account_non_locked_date")
    private LocalDateTime accountNonLockedDate;

    /**
     * 密码过期时间
     */
    @JsonIgnore
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Field("credentials_non_expired_date")
    private LocalDateTime credentialsNonExpiredDate;

    /**
     * 账户是否启用
     */
    @JsonIgnore
    private Boolean enabled;

    @Version
    private Long version;

    /**
     * domainEvent
     *
     * @return Collection
     */
    @SuppressWarnings("unused")
    @DomainEvents
    public Collection<UserCreateEvent> domainEvent() {
        return Collections.singletonList(new UserCreateEvent(this.getId(), this.getUsername()));
    }
}
