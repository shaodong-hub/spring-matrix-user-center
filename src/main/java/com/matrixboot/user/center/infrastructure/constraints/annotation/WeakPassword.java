package com.matrixboot.user.center.infrastructure.constraints.annotation;

import com.matrixboot.user.center.infrastructure.constraints.validator.WeakPasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * create in 2023/3/19 23:48
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
//指定注解的处理类
@Constraint(validatedBy = {WeakPasswordValidator.class})
public @interface WeakPassword {

    String value() default "";

    String message() default "弱密码";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
