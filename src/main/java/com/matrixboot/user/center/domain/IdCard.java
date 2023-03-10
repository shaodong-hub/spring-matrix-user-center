package com.matrixboot.user.center.domain;

import com.matrixboot.user.center.infrastructure.converter.IdNumberConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;

/**
 * create in 2023/3/9 11:09
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
public class IdCard implements Serializable {

    @Serial
    private static final long serialVersionUID = 8024908880563315371L;

    @Convert(attributeName = "idNumber", converter = IdNumberConverter.class)
    @Column(name = "id_number", columnDefinition = "CHAR(20) DEFAULT '' COMMENT 'idNumber'")
    private String idNumber;

    @Column(columnDefinition = "CHAR(20) DEFAULT '' COMMENT 'frontPicture'")
    private String frontPicture;

    @Column(columnDefinition = "CHAR(20) DEFAULT '' COMMENT 'backPicture'")
    private String backPicture;

}
