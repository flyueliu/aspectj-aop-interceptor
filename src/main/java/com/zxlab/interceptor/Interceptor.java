package com.zxlab.interceptor;

import org.springframework.core.MethodParameter;

import java.lang.reflect.Method;

/**
 * @author Liu Yuefei
 * @created 2018-09-10 13:06
 */
public interface Interceptor {

    boolean preHandle(Object target, Method method, MethodParameter[] parameters, Object[] args);


    void afterReturingHandle(Object handler, Object[] args, String methodName, Object result);

    void afterException(Object handler, Object[] args, String methodName, Exception ex);
}
