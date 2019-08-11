package org.lanqiao.controller;

import org.lanqiao.pojo.Privs;
import org.lanqiao.service.PrivsService;
import org.lanqiao.util.DateUtils;
import org.lanqiao.vo.JsonResult;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;


@CrossOrigin
@RestController
public class PrivsController {


    /**
     * 添加权限
     * @return
     */
    @RequestMapping("/api/privs/add")
    public JsonResult addPrivs(String name, Integer parentId, String url, String perms,
                               Integer type, String icon, String createBy,
                               @RequestParam(value = "delFlag",defaultValue = "0") Integer delFlag){
        Privs privs = new Privs();
        privs.setName(name);
        privs.setParentId(parentId);
        System.out.println(parentId);
        privs.setUrl(url);
        privs.setPerms(perms);
        privs.setType(type);
        privs.setIcon(icon);
        privs.setCreateBy(createBy);
        try{
            privs.setCreateTime(DateUtils.getNow());
        }catch (ParseException e){
            e.printStackTrace();
        }
        privs.setDelFlag(delFlag);

        PrivsService privsService = new PrivsService();
        JsonResult jsonResult = null;
        try{
            privsService.addPrivs(privs);
            jsonResult = new JsonResult("200","添加成功",null);
        }catch (Exception e){
            jsonResult = new JsonResult("500","添加异常",null);
        }
        return jsonResult;
    }


    /**
     * 删除权限
     */
    @RequestMapping("/api/privs/delete")
    public JsonResult deletePrivs(Integer privsId){
        PrivsService privsService = new PrivsService();
        JsonResult jsonResult = null;
        try{
            privsService.deletePrivs(privsId);
            jsonResult = new JsonResult("200","删除成功",null);
        }catch (Exception e){
            jsonResult = new JsonResult("500","删除异常",null);
        }
        return jsonResult;
    }

    /**
     * 修改权限
     */
    @RequestMapping("/api/privs/update")
    public JsonResult updatePrivs(Integer id ,String name, Integer parentId, String perms,
                                  Integer type,String lastUpadateBy,Integer delFlag){
        Privs privs = new Privs();
        privs.setId(id);
        privs.setName(name);
        privs.setParentId(parentId);
        privs.setPerms(perms);
        privs.setType(type);
        privs.setLastUpdateBy(lastUpadateBy);
        try{
            privs.setLastUpdateTime(DateUtils.getNow());
        }catch (ParseException e){
            e.printStackTrace();
        }
        privs.setDelFlag(delFlag);

        PrivsService privsService = new PrivsService();
        JsonResult jsonResult = null;
        try{
            privsService.updatePrivs(privs);
            jsonResult = new JsonResult("200","修改成功",null);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult = new JsonResult("500","修改异常",null);
        }
        return jsonResult;
    }


    /**
     * 查询所有权限
     */

    @RequestMapping(value = "/api/privs/query",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult queryAll(){
        PrivsService privsService = new PrivsService();
        JsonResult jsonResult = null;
        try{
            List<Privs> list = privsService.queryAll();
            System.out.println(list.size());
            if(list!=null){
                jsonResult = new JsonResult("200","查询成功",list);
            }else{
                jsonResult = new JsonResult("404","查询失败",null);
            }
        }catch (Exception e){
            jsonResult = new JsonResult("500","查询异常",null);
        }
        return jsonResult;
    }

    /**
     * 根据权限名称查询权限信息
     */
    @RequestMapping("api/privs/findPrivsByName")
    public JsonResult findPrivsByName(String name){
        JsonResult jsonResult = null;
        PrivsService privsService = new PrivsService();
        Privs privs = null;
        try {
            privs = privsService.findPrivsByName(name);
            if(privs != null){
                jsonResult = new JsonResult("200","查询成功",privs);
            }else{
                jsonResult = new JsonResult("404","该权限不存在",null);
            }
        }catch (Exception e){
            e.printStackTrace();
            jsonResult = new JsonResult("500","查询异常",e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 根据权限Id查询权限信息
     */
    @RequestMapping("api/privs/findPrivsById")
    public JsonResult findPrivsById(Integer privsId){
        JsonResult jsonResult = null;
        PrivsService privsService = new PrivsService();
        Privs privs = null;
        try {
            privs = privsService.findPrivsById(privsId);
            if(privs != null){
                jsonResult = new JsonResult("200","查询成功",privs);
            }else{
                jsonResult = new JsonResult("404","该权限不存在",null);
            }
        }catch (Exception e){
            e.printStackTrace();
            jsonResult = new JsonResult("500","查询异常",e.getMessage());
        }
        return jsonResult;
    }


    /**
     * 根据角色Id查询权限
    */
    @RequestMapping("/api/privs/findByRoleId")
    public JsonResult findById(Integer roleId){
        PrivsService privsService = new PrivsService();
        JsonResult jsonResult = null;
        try{
            List<Privs> list = privsService.queryAllById(roleId);
            if(list!=null){
                jsonResult = new JsonResult("200","查询成功",list);
            }else{
                jsonResult = new JsonResult("404","查询失败",null);
            }
        }catch (Exception e){
            jsonResult = new JsonResult("500","查询异常",null);
        }
        return jsonResult;
    }

    /**
     * 给角色授权
     */
    @RequestMapping("/api/privs/empower")
    public JsonResult empower(Integer roleId, Integer privsId, String createBy,
                              String lastUpdateBy){
        PrivsService privsService = new PrivsService();
        Date createTime = null;
        Date lastUpdateTime = null;
        try{
            createTime = DateUtils.getNow();
        }catch (ParseException e){
            e.printStackTrace();
        }
        try{
            lastUpdateTime = DateUtils.getNow();
        }catch (ParseException e){
            e.printStackTrace();
        }
        JsonResult jsonResult = null;
        try{
            privsService.empower(roleId,privsId,createBy,createTime,lastUpdateBy,lastUpdateTime);
            jsonResult = new JsonResult("200","授权成功",null);
        }catch (Exception e){
            jsonResult = new JsonResult("500","授权异常",null);
        }
        return jsonResult;
    }


    /**
     * 取消权限
     */
    @RequestMapping("/api/privs/noempower")
    public JsonResult noEmpower(Integer roleId,Integer privsId){
        JsonResult jsonResult = null;
        PrivsService privsService = new PrivsService();
        try {
            privsService.deleteRolePrivs(roleId,privsId);
            jsonResult = new JsonResult("200","取消权限成功",null);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult = new JsonResult("500","取消权限异常",null);
        }
        return jsonResult;
    }

    /**
     * 通过父级权限 查询
     */
    @RequestMapping("/api/privs/findPrivsByParentId")
    public JsonResult findPrivsByParentId(Integer parentId) {
        JsonResult jsonResult = null;
        PrivsService privsService = new PrivsService();
        Privs privs = null;
        try {
            privs = privsService.findPrivsByParentId(parentId);
            if (privs != null) {
                jsonResult = new JsonResult("200", "查询成功", privs);
            } else {
                jsonResult = new JsonResult("404", "该权限不存在", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult("500", "查询异常", e.getMessage());
        }
        return jsonResult;
    }
}
