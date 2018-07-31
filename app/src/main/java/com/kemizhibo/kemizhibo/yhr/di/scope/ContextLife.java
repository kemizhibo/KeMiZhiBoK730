package com.kemizhibo.kemizhibo.yhr.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
/**
 * Author: yhr
 * Date: 2018/5/3
 * Describe:  限定符
 * 限定Context生命周期
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ContextLife {
    String value() default "";
}
