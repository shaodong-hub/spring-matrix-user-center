package com.matrixboot.user.center.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.matrixboot.user.center.domain.AuditInfo;
import com.matrixboot.user.center.domain.IdCard;
import com.matrixboot.user.center.domain.UserStatus;
import com.matrixboot.user.center.infrastructure.common.event.UserCreateEvent;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.Version;
import org.springframework.data.domain.DomainEvents;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

/**
 * create in 2022/11/28 19:09
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "matrix_user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"username"}, name = "UK_USERNAME"),
                @UniqueConstraint(columnNames = {"mobile"}, name = "UK_MOBILE"),
                @UniqueConstraint(columnNames = {"email"}, name = "UK_EMAIL"),
        },
        indexes = {
                @Index(columnList = "created_date", name = "IDX_CREATED_DATE"),
                @Index(columnList = "last_login_date", name = "IDX_LAST_LOGIN_DATE")
        }
)
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class MatrixUserEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -6958352591294167495L;

    /**
     * 用户的唯一 ID，与业务无关，自增
     */
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名，也是唯一的
     */
    @Column(columnDefinition = "VARCHAR(20) DEFAULT '' COMMENT '用户名'")
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    @Column(columnDefinition = "VARCHAR(100) DEFAULT '' COMMENT '密码'")
    private String password;

    /**
     * 手机号码，也是唯一的
     */
    @Column(columnDefinition = "VARCHAR(20) DEFAULT '' COMMENT '手机号码'")
    private String mobile;

    /**
     * 联系人姓名
     */
    @Column(columnDefinition = "VARCHAR(20) DEFAULT '' COMMENT '联系人姓名'")
    private String contacts;

    /**
     * 用户的邮箱，按道理也应该是唯一的
     */
    @Column(columnDefinition = "VARCHAR(30) DEFAULT '' COMMENT '电子邮箱'")
    private String email;

    @Embedded
    private IdCard idCard;

    @Embedded
    private UserStatus userStatus;

    @Embedded
    private AuditInfo auditInfo;

    @Version
    @Column(columnDefinition = "BIGINT DEFAULT 0 COMMENT '版本号'")
    private Long version;

    /**
     * 用户的最后登录时间
     */
    @Column(name = "last_login_date", columnDefinition = "DATETIME COMMENT '最后登录时间'")
    private LocalDateTime lastLoginDate;

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

//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "user_id")
//    @MapKey(name = "roleCode")
//    @JsonManagedReference
//    private Map<String, MatrixRoleEntity> roles;

}
