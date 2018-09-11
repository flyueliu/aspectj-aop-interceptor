package com.zxlab.dao;

import com.zxlab.entity.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

/**
 * @Author: Liu Yuefei
 * @Description:
 * @Date: Created in 16:16 2018/2/9
 */
@Repository
public interface UserDao {

    User getById(Integer id);
}
