package com.zxlab.dao;

import com.zxlab.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: Liu Yuefei
 * @Description:
 * @Date: Created in 16:16 2018/2/9
 */
public interface UserDao extends CrudRepository<User, Long> {
}
