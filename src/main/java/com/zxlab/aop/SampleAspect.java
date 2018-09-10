package com.zxlab.aop;

import com.zxlab.interceptor.Interceptor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

/**
 * @author Liu Yuefei
 * @created 2018-09-10 8:56
 */
@Aspect
@Component
public class SampleAspect implements ApplicationContextAware {


    private static Logger logger = LoggerFactory.getLogger(SampleAspect.class);
    private static ApplicationContext applicationContext;

    /**
     * 切入点：SampleService继承树中所有方法。
     */
    @Pointcut("execution(* com.zxlab..*service..*(..))")
    public void methodPointCut() {

    }

    @Before("methodPointCut()")
    public void before() {
        logger.info("before execute.......");
    }

    @AfterThrowing(value = "methodPointCut()", throwing = "ex")
    public void exceptionMethod(Exception ex) {
        logger.info("execute exception ...");
        logger.error(ex.getMessage(), ex);
    }

    @AfterReturning("methodPointCut()")
    public void finallyExecute() {
        logger.info("finallyExecute finish....");
    }

    @Around("methodPointCut()")
    public Object aroundExecuteMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("applicationContext:" + applicationContext);
        logger.info("pointcut before:" + joinPoint.getStaticPart());
        logger.info("target class:" + joinPoint.getTarget().getClass().getName());
        logger.info("proxy class:" + joinPoint.getThis().getClass().getName());
        logger.info("target class equal proxy class:" + joinPoint.getThis().getClass().equals(joinPoint.getTarget().getClass()));
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        Object target = joinPoint.getTarget();
        String methodName = signature.getName();

        Class[] paramTypes = this.getMethodParamTypes(target, methodName, args);
        Method method = target.getClass().getMethod(methodName, paramTypes);
        int paramCount = 0;
        if (args != null && args.length > 0) {
            paramCount = args.length;
        }
        MethodParameter[] parameters = this.getMethodParameters(method, paramCount);
        boolean result = true;
        Map<String, Interceptor> beansOfType = applicationContext.getBeansOfType(Interceptor.class);
        Collection<Interceptor> interceptors = beansOfType.values();
        for (Interceptor interceptor : interceptors) {
            if (!interceptor.preHandle(target, method, parameters, args)) {
                result = false;
                break;
            }
        }
        if (result) {
            return joinPoint.proceed(args);
        } else {
            logger.info("method has been intercepted");
            return null;
        }
    }


    @After("methodPointCut()")
    public void afterExecute(JoinPoint joinPoint) throws Throwable {
        logger.info("pointcut after:" + joinPoint.getStaticPart());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SampleAspect.applicationContext = applicationContext;
    }

    private MethodParameter[] getMethodParameters(Method method, int count) {
        if (count < 1) {
            return null;
        }
        MethodParameter[] result = new MethodParameter[count];
        for (int i = 0; i < count; i++) {
            result[i] = new MethodParameter(method, i);
        }
        return result;
    }

    private Class[] getMethodParamTypes(Object target, String methodName, Object[] args) {
        if (args == null || args.length == 0) {
            return null;
        }
        Class<?>[] paramTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            paramTypes[i] = args[i].getClass();
        }
        return paramTypes;
    }
}
