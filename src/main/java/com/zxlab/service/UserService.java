package com.zxlab.service;

import com.zxlab.entity.User;
import com.zxlab.vo.out.JsonResult;

/**
 * @Author: Liu Yuefei
 * @Date: Created in 2018/9/11 11:00
 * @Description:
 */
public interface UserService {

    User getById(Long id);

    JsonResult<User> login(String name, String password);
}
