package org.lanqiao.controller;

import org.lanqiao.pojo.Privs;
import org.lanqiao.service.PrivsService;
import org.lanqiao.util.DateUtils;
import org.lanqiao.util.IntegerUtils;
import org.lanqiao.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;


@CrossOrigin
@RestController
public class PrivsController {

    @Autowired
    PrivsService privsService;

    /**
     * 添加权限
     * @return
     */
    @RequestMapping("/api/privs/add")
    public JsonResult addPrivs(String name,int parentId,String url,
                               String perms,int type,String createBy,
                               @RequestParam(value = "delFlag",defaultValue = "0")String delFlag){

        Privs privs = new Privs();
        privs.setName(name);
        privs.setParentId(parentId);
        privs.setUrl(url);
        privs.setPerms(perms);
        privs.setType(type);
        privs.setCreateBy(createBy);
        try{
            privs.setCreateTime(DateUtils.getNow());
        }catch (ParseException e){
            e.printStackTrace();
        }
        privs.setDelFlag(IntegerUtils.toInt(delFlag));
        JsonResult jsonResult = null;
        System.out.println(privs.toString());
        try{
            privsService.addPrivs(privs);
            jsonResult = new JsonResult("200","添加成功",null);
        }catch (Exception e){
            jsonResult = new JsonResult("500","添加异常",null);
        }
        return jsonResult;
    }


    /**
     * 查询所有权限信息
     */
    @RequestMapping("/api/privs/queryAll")
    public JsonResult queryAll(){
        JsonResult jsonResult = null;
        try{
//            PageHelper.startPage(1,2);
            List<Privs> list = privsService.queryAll();
//            PageInfo pageInfo =new PageInfo(list);
            jsonResult = new JsonResult("200","查询成功",list);
        }catch (Exception e){
            jsonResult = new JsonResult("500","查询异常",null);
        }
        return jsonResult;
    }

    /**
     * 动态菜单
     */
    @RequestMapping("/api/menus")
    public JsonResult menusPrivs(Integer id){
        JsonResult jsonResult = null;
        try{
            List<Privs> list = privsService.menusPrivs(id);
            jsonResult = new JsonResult("200","查询成功",list);
        }catch (Exception e){
            jsonResult = new JsonResult("500","查询异常",null);
        }
        return jsonResult;
    }

    /**
     * 删除权限
     */
    @RequestMapping("/api/privs/del")
    public JsonResult delPrivs(Integer id){
        JsonResult jsonResult = null;
        try{
            privsService.deletePrivs(id);
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
    public JsonResult updatePrivs(Integer id,String name,String perms,String lastUpdateBy
                                    ,String url){
        JsonResult jsonResult = null;
        Privs privs = new Privs();
        privs.setId(id);
        privs.setName(name);
        privs.setUrl(url);
        privs.setLastUpdateBy(lastUpdateBy);
        privs.setPerms(perms);
        try{
            privsService.updatePrivs(privs);
            jsonResult = new JsonResult("200","修改成功",null);
        }catch (Exception e){
            jsonResult = new JsonResult("500","修改异常",null);
        }
        return jsonResult;
    }

    /**
     * 根据角色id查询权限信息
     */
    @RequestMapping("/api/privsRole/selectById")
    public JsonResult queryByRoleId(Integer roleId){
        JsonResult jsonResult = null;
        try{
            List<Privs> list = privsService.queryByRoleId(roleId);
            jsonResult = new JsonResult("200","查询成功",list);
        }catch (Exception e){
            jsonResult = new JsonResult("500","查询异常",null);
        }
        return jsonResult;
    }

    /**
     * 给角色授权
     */
    @RequestMapping("/api/privs/empower")
    public JsonResult addRolePrivs(Integer roleId,Integer privsId){
        JsonResult jsonResult = null;
        try{
            privsService.addRolePrivs(roleId,privsId);
            jsonResult = new JsonResult("200","为角色添加权限成功",null);
        }catch (Exception e){
            jsonResult = new JsonResult("500","为角色添加权限异常",null);
        }
        return jsonResult;
    }

    /**
     * 删除角色的权限
     */
    @RequestMapping("/api/privs/noempower")
    public JsonResult delRolePrivs(Integer roleId,Integer privsId){
        JsonResult jsonResult = null;
        try{
            privsService.delRolePrivs(roleId,privsId);
            jsonResult = new JsonResult("200","删除角色权限成功",null);
        }catch (Exception e){
            jsonResult = new JsonResult("500","删除角色权限异常",null);
        }
        return jsonResult;
    }

    /**
     * 根据权限id查询权限详细信息
     */
    @RequestMapping("/api/privs/queryById")
    public JsonResult queryPrivsById(Integer privsId){
        JsonResult jsonResult = null;
        try{
            Privs privs = privsService.queryPrivsById(privsId);
            jsonResult = new JsonResult("200","查询成功",privs);
        }catch (Exception e){
            jsonResult = new JsonResult("500","查询异常",null);
        }
        return jsonResult;
    }


    /**
     * 批量删除
     */
    @RequestMapping("/api/privs/someDel")
    public JsonResult someDel(@RequestParam("id") int [] id){
        JsonResult jsonResult = null;
        try{
            privsService.someDel(id);
            jsonResult = new JsonResult("200","删除成功",null);
        }catch (Exception e){
            jsonResult = new JsonResult("500","删除异常",null);
        }
        return jsonResult;
    }

    /**
     * 多条件查询
     */
    @RequestMapping("/api/privs/condition")
    public JsonResult conditionSearch(Integer type,String name){
        JsonResult jsonResult = null;
        try{
            List<Privs> privses = privsService.conditionSearch(type,name);
            if(privses.size() > 0){
                jsonResult = new JsonResult("200","查询成功",privses);
            }else{
                jsonResult = new JsonResult("404","无数据",privses);
            }
        }catch (Exception e){
            jsonResult = new JsonResult("500","查询异常",null);
        }
        return jsonResult;
    }


    /**
     * 查询上次权限
     */
    @RequestMapping("/api/privs/last")
    public JsonResult queryLastPrivs(Integer type){
        JsonResult jsonResult = null;
        try{
            List<Privs> privses = privsService.queryLastPrivs(type);
            if(privses.size() > 0){
                jsonResult = new JsonResult("200","查询成功",privses);
            }else{
                jsonResult = new JsonResult("404","无数据",privses);
            }
        }catch (Exception e){
            jsonResult = new JsonResult("500","查询异常",null);
        }
        return jsonResult;
    }
}
