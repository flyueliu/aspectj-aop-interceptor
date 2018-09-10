package com.zxlab.aop;

import com.zxlab.interceptor.Interceptor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

/**
 * @author Liu Yuefei
 * @created 2018-09-10 16:27
 */
public class AopExecute {

    private static final Logger logger = LoggerFactory.getLogger(AopExecute.class);

    public static Object execute(ProceedingJoinPoint joinPoint, ApplicationContext applicationContext) throws Throwable {
        logger.info("applicationContext:" + applicationContext);
        logger.info("pointcut before:" + joinPoint.getStaticPart());
        logger.info("target class:" + joinPoint.getTarget().getClass().getName());
        logger.info("proxy class:" + joinPoint.getThis().getClass().getName());
        logger.info("target class equal proxy class:" + joinPoint.getThis().getClass().equals(joinPoint.getTarget().getClass()));
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        Object target = joinPoint.getTarget();
        String methodName = signature.getName();

        Class[] paramTypes = AopExecute.getMethodParamTypes(target, methodName, args);
        Method method = target.getClass().getMethod(methodName, paramTypes);
        int paramCount = 0;
        if (args != null && args.length > 0) {
            paramCount = args.length;
        }
        MethodParameter[] parameters = getMethodParameters(method, paramCount);
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

    private static MethodParameter[] getMethodParameters(Method method, int count) {
        if (count < 1) {
            return null;
        }
        MethodParameter[] result = new MethodParameter[count];
        for (int i = 0; i < count; i++) {
            result[i] = new MethodParameter(method, i);
        }
        return result;
    }

    private static Class[] getMethodParamTypes(Object target, String methodName, Object[] args) {
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
