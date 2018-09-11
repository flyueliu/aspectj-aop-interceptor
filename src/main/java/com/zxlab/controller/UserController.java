package com.zxlab.controller;

import com.zxlab.entity.User;
import com.zxlab.vo.out.JsonResult;
import com.zxlab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Liu Yuefei
 * @created 2018-09-10 17:24
 */
@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping("/user/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @RequestMapping("/user/{name}/{password}")
    public JsonResult<User> login(@PathVariable("name") String name,
                                  @PathVariable("password") String password) {
        return userService.login(name, password);
    }
}
