package com.supermax.base.common.aspect;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/*
 * @Author yinzh
 * @Date   2018/10/19 17:39
 * @Description
 */
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface PATCH {
    String value();
}
