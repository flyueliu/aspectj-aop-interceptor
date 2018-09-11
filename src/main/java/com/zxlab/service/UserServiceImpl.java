package com.zxlab.service;

import com.zxlab.dao.UserDao;
import com.zxlab.entity.User;
import com.zxlab.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return null;
    }
}
