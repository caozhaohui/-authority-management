package org.lanqiao.controller;

import org.lanqiao.vo.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrivsController {

    /**
     * 添加权限 目录 菜单 的具体内容
     */
    @RequestMapping("/privs/add")
    public JsonResult addPrivs(){
        JsonResult jsonResult = null;
        return jsonResult;
    }
}
