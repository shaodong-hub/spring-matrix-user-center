package com.matrixboot.user.center.domain.entity.role;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.matrixboot.user.center.domain.AuditInfo;
import com.matrixboot.user.center.domain.entity.authority.MatrixAuthorityEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKey;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * create in 2023/3/11 02:04
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "matrix_role", uniqueConstraints = {@UniqueConstraint(columnNames = {"role_code"}, name = "UK_ROLE_CODE")})
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class MatrixRoleEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -6958352591294167495L;

    /**
     * 用户的唯一 ID，与业务无关，自增
     */
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name", columnDefinition = "VARCHAR(30) DEFAULT '' COMMENT 'roleName'")
    private String roleName;

    @Column(name = "role_code", columnDefinition = "VARCHAR(30) DEFAULT '' COMMENT 'roleCode'")
    private String roleCode;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "role_id")
    @MapKey(name = "id")
    @JsonManagedReference
    private Map<Long, MatrixRoleAuthorityEntity> authorities;

    @Embedded
    private AuditInfo auditInfo;

    @Version
    @Column(columnDefinition = "BIGINT DEFAULT 0 COMMENT '版本号'")
    private Long version;

    @Transient
    private List<MatrixAuthorityEntity> authorityEntityList;
}
