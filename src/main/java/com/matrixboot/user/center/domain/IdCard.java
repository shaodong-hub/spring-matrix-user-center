package com.matrixboot.user.center.domain;

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


    private String idNumber;

    private String frontPicture;

    private String backPicture;

}
