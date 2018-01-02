package com.junlong0716.module.common.rxbus;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: 巴黎没有摩天轮Li
 * @description:
 * @date: Created in 下午1:15 2018/1/1
 * @modified by:
 */

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Subscribe {
    int code() default -1;

    ThreadMode threadMode() default ThreadMode.CURRENT_THREAD;
}
