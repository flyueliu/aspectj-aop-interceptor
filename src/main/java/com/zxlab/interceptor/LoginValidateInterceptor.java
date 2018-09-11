package com.zxlab.interceptor;

import com.zxlab.annotation.LoginRequired;
import com.zxlab.dao.UserDao;
import com.zxlab.entity.User;
import com.zxlab.vo.in.BaseInModel;
import com.zxlab.vo.out.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Method;

/**
 * @Author: Liu Yuefei
 * @Date: Created in 2018/9/11 21:51
 * @Description:
 */
@Controller
public class LoginValidateInterceptor implements Interceptor {

    @Autowired
    private UserDao userDao;

    private static final Logger logger = LoggerFactory.getLogger(LoginValidateInterceptor.class);

    @Override
    public boolean preHandle(Object target, Method method, MethodParameter[] parameters, Object[] args) {
        logger.info("check @LoginRequired annotation. ");
        LoginRequired loginRequired = method.getAnnotation(LoginRequired.class);
        if (loginRequired != null) {
            logger.info("has @LoginRequired,need login");
            // 需要登录
            try {
                int index = this.getInModelIndexOfParams(parameters);
                this.validateToken((BaseInModel) args[index]);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                return false;
            }
        }
        return true;
    }

    private void validateToken(BaseInModel inModel) {
        String token = inModel.getToken();
        if (token == null) {
            throw new RuntimeException("没有传递token值");
        }
        User user = userDao.getByToken(token);
        if (user == null) {
            throw new RuntimeException("没有该会话信息");
        }
        inModel.setUser(user);
    }

    private int getInModelIndexOfParams(MethodParameter[] parameters) {
        if (parameters == null || parameters.length < 1) {
            throw new RuntimeException("需要传递token值");
        }
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].getParameterType().equals(BaseInModel.class)) {
                logger.info("params index is :" + i);
                return i;
            }
        }
        throw new RuntimeException("没有接受token值的参数");
    }

    @Override
    public void afterReturingHandle(Object handler, Object[] args, String methodName, Object result) {

    }

    @Override
    public void afterException(Object handler, Object[] args, String methodName, Exception ex) {

    }
}
