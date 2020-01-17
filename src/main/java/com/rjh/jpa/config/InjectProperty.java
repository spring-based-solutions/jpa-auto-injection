package com.rjh.jpa.config;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 *
 * @author Null
 * @date 2020-01-16
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { FIELD, METHOD, ANNOTATION_TYPE })
public @interface InjectProperty {
    /**
     * 属性名
     * @return
     */
    String value();
    /**
     * 只在插入时赋值
     * @return
     */
    boolean insert() default false;

    /**
     * 如果有值时，是否强制赋值
     * @return
     */
    boolean force() default true;
}
