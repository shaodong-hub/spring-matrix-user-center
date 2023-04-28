package com.matrixboot.user.center.domain.entity.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.matrixboot.user.center.domain.AuditInfo;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.Version;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;

/**
 * create in 2023/3/19 21:23
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "matrix_user_role")
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class MatrixUserRoleEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 7519729838081369971L;

    /**
     * 用户的唯一 ID，与业务无关，自增
     */
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    @ManyToOne(targetEntity = MatrixUserEntity.class)
    @JsonBackReference
    private MatrixUserEntity user;

    @Embedded
    private AuditInfo auditInfo;

    @Version
    @Column(columnDefinition = "BIGINT DEFAULT 0 COMMENT '版本号'")
    private Long version;
}
