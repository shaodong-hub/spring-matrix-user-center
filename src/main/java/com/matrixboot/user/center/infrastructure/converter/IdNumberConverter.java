package com.matrixboot.user.center.infrastructure.converter;

import jakarta.persistence.AttributeConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * create in 2023/3/9 11:13
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
public class IdNumberConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        String upperCase = StringUtils.upperCase(StringUtils.isBlank(attribute) ? "" : attribute);
        log.info("convertToDatabaseColumn {} {}", attribute, upperCase);
        return upperCase;
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        log.info("convertToEntityAttribute {}", dbData);
        return dbData;
    }
}
