package com.groupwork.order.annotation;

import com.groupwork.order.model.OperationType;

import java.lang.annotation.*;
import java.util.Date;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLog {

    long userId() default 0L;

    String detail() default "";

    OperationType operationType() default OperationType.UNKNOWN;

}
