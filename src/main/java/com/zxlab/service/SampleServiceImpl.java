package com.zxlab.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Liu Yuefei
 * @created 2018-09-10 8:54
 */
@Service
public class SampleServiceImpl implements SampleService {

    private Logger logger = LoggerFactory.getLogger(SampleServiceImpl.class);

    @Override
    public String getName(String name) {
        logger.info("1.start execute getName method!");
        logger.info("2.method params:" + name);
        logger.info("3.finish execute getName method!");
        return name;
    }
}
