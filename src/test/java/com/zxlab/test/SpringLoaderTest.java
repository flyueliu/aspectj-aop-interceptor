package com.zxlab.test;

import com.zxlab.dao.UserDao;
import com.zxlab.entity.User;
import com.zxlab.service.SampleService;
import com.zxlab.service.UserService;
import com.zxlab.vo.out.JsonResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

/**
 * @author Liu Yuefei
 * @created 2018-09-10 9:13
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext*.xml"})
public class SpringLoaderTest {

    @Autowired
    private SampleService sampleService;

    @Autowired
    private DataSource dataSource;

    @Test
    public void testContext() {
        System.out.println("start");
    }

    @Test
    public void testService() {
        sampleService.getName("userName");
    }

    @Test
    public void testDataSource() {
        System.out.println(dataSource);
    }

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Test
    public void testRepository() {
        User user = userDao.getByNameAndPassword("lyf", "123");

        System.out.println(user);
    }

    @Test
    public void testTransational() {
        JsonResult<User> jsonResult = userService.login("lyf", "123");
        System.out.println(jsonResult);
    }
}
