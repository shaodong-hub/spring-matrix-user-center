package com.matrixboot.user.center.infrastructure.constraints.validator;

import com.matrixboot.user.center.infrastructure.constraints.annotation.WeakPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * create in 2023/3/19 23:50
 *
 * @author shishaodong
 * @version 0.0.1
 */
public class WeakPasswordValidator implements ConstraintValidator<WeakPassword, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return true;
    }
}
