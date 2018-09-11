package com.zxlab.service;

import com.zxlab.dao.UserDao;
import com.zxlab.entity.User;
import com.zxlab.vo.out.JsonResult;
import com.zxlab.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * @Author: Liu Yuefei
 * @Date: Created in 2018/9/11 11:00
 * @Description:
 */
@Service
public class UserServiceImpl extends BaseService implements UserService {


    @Autowired
    private UserDao userDao;

    @Override
    public User getById(Long id) {
        logger.info("get UserById: " + id);
        return userDao.getById(id);
    }

    @Override
    @Transactional
    public JsonResult<User> login(String name, String password) {
        User user = userDao.getByNameAndPassword(name, password);
        if (user == null) {
            return new JsonResult<>(JsonResult.INPUT_ERROR_CODE, "用户名或密码错误");
        }
        user = this.updateToken(user);
        userDao.updateTokenAndLastUpdate(user.getId(), user.getToken(), user.getLastUpdate());
        return new JsonResult<User>(JsonResult.SUCCESS_CODE, "用户登录成功", user);
    }

    private User updateToken(User user) {
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        user.setLastUpdate(new Date());
        return user;
    }
}
