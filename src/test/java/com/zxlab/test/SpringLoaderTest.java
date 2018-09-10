package com.zxlab.test;

import com.zxlab.service.SampleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Liu Yuefei
 * @created 2018-09-10 9:13
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SpringLoaderTest {

    @Autowired
    private SampleService sampleService;

    @Test
    public void testContext() {
        System.out.println("start");
    }

    @Test
    public void testService() {
        sampleService.getName("userName");
    }
}