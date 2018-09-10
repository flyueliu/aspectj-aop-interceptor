package com.zxlab.test;

import com.zxlab.service.SampleService;
import com.zxlab.service.SampleServiceImpl;
import org.junit.Test;

/**
 * @author Liu Yuefei
 * @created 2018-09-10 8:58
 */
public class SampleServiceTest {

    @Test
    public void testSample() {
        SampleService sampleService = new SampleServiceImpl();
        String userName = sampleService.getName("userName");
    }
}
