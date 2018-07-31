package com.kemizhibo.kemizhibo.yhr.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Author: yhr
 * Date: 2018/5/3
 * Describe: 自定义Scope限定作用域
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
