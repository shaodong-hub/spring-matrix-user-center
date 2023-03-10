package com.matrixboot.user.center.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * create in 2023/3/11 01:41
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
public class AuditInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = -7013867412565019124L;

    @CreatedDate
    @Column(name = "created_date", columnDefinition = "DATETIME COMMENT '创建时间'")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date", columnDefinition = "DATETIME COMMENT '最后更新时间'")
    private LocalDateTime lastModifiedDate;

    @CreatedBy
    @Column(name = "create_by", columnDefinition = "BIGINT DEFAULT 0 COMMENT '创建人'")
    private Long createBy;

    @LastModifiedBy
    @Column(name = "last_modified_by", columnDefinition = "BIGINT DEFAULT 0 COMMENT '最后更新人'")
    private Long lastModifiedBy;

    @Column(columnDefinition = "VARCHAR(64) DEFAULT '' COMMENT '备注信息'")
    private String note;
}
