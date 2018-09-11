package com.zxlab.dao;

import com.zxlab.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @Author: Liu Yuefei
 * @Description:
 * @Date: Created in 16:16 2018/2/9
 */
@Repository
public interface UserDao {

    User getById(Long id);

    @Select("select * from tbl_user where name=#{name} and password=#{password} ")
    User getByNameAndPassword(@Param("name") String name, @Param("password") String password);

    @Update("update tbl_user set token=#{token},last_update=#{lastUpdate} where id=#{id}")
    void updateTokenAndLastUpdate(@Param("id") Long id, @Param("token") String token, @Param("lastUpdate") Date updateTime);

    @Select("select * from tbl_user where token=#{token}")
    User getByToken(String token);
}
