package com.zxlab.vo.in;

import com.zxlab.entity.User;

/**
 * @Author: Liu Yuefei
 * @Date: Created in 2018/9/11 21:26
 * @Description:
 */
public class BaseInModel {

    private String token;

    private User user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
