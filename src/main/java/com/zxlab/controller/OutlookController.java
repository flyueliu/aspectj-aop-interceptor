package com.zxlab.controller;

import com.zxlab.annotation.LoginRequired;
import com.zxlab.entity.User;
import com.zxlab.vo.in.BaseInModel;
import com.zxlab.vo.out.JsonResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Liu Yuefei
 * @Date: Created in 2018/9/11 21:28
 * @Description:
 */
@RestController
public class OutlookController {


    @LoginRequired()
    @RequestMapping("/look/{version}")
    public JsonResult<User> outLookResouce(@PathVariable("version") String version,
                                           BaseInModel inModel) {
        return new JsonResult<User>(JsonResult.SUCCESS_CODE, "获取信息成功");
    }
}
