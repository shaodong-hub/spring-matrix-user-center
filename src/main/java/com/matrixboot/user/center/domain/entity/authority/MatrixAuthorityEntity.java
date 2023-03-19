package com.matrixboot.user.center.domain.entity.authority;

import com.matrixboot.user.center.domain.AuditInfo;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.io.Serializable;

/**
 * create in 2023/3/19 21:10
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "matrix_authority", uniqueConstraints = {@UniqueConstraint(columnNames = {"authority"}, name = "UK_AUTHORITY")})
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class MatrixAuthorityEntity implements GrantedAuthority, Serializable {

    @Serial
    private static final long serialVersionUID = -6958352591294167495L;

    /**
     * 用户的唯一 ID，与业务无关，自增
     */
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "authority_name", columnDefinition = "VARCHAR(20) DEFAULT '' COMMENT '名称'")
    private String authorityName;

    @Column(columnDefinition = "VARCHAR(20) DEFAULT '' COMMENT '权限'")
    private String authority;

    @Embedded
    private AuditInfo auditInfo;

    @Version
    @Column(columnDefinition = "BIGINT DEFAULT 0 COMMENT '版本号'")
    private Long version;
}
