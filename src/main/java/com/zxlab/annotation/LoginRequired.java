package com.zxlab.annotation;

import java.lang.annotation.*;

/**
 * @Author: Liu Yuefei
 * @Date: Created in 2018/9/11 21:30
 * @Description:
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginRequired {


}
