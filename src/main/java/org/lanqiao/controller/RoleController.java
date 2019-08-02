package org.lanqiao.controller;


import org.lanqiao.pojo.Role;
import org.lanqiao.service.RoleService;
import org.lanqiao.util.IntegerUtils;
import org.lanqiao.vo.JsonResult;
import org.lanqiao.vo.PageResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RoleController {

    RoleService service = new RoleService();
    JsonResult json = null;


    @RequestMapping("/sys/role/viewall")
    public JsonResult queryAllRoleCon(@RequestParam("pageNum") String pageNum,
                                      @RequestParam("pageNum") String pageSize) {

        int Num = IntegerUtils.toInt(pageNum);
        int Size = IntegerUtils.toInt(pageSize);

        try {
            PageResult pageResult = service.queryAllRoleM(Num, Size);
            if (pageResult != null) {
                json = new JsonResult("200", "success", pageResult);
            } else {
                json = new JsonResult("404", "error", null);
            }
        } catch (Exception e) {
            json = new JsonResult("500", "error", e.getMessage());
        }
        return json;


    }

    //删除用户
    @RequestMapping("/sys/role/deleteall")
    public JsonResult deleteRoleCon(String RoleId) {
        try {
            int i = service.deleteRoleM(RoleId);
            if (i> 0) {
                json = new JsonResult("200", "success", i);
            } else {
                json = new JsonResult("404", "error", null);
            }
        } catch (Exception e) {
            json = new JsonResult("500", "error", e.getMessage());
        }
        return json;

    }

    //修改角色
    @RequestMapping("/sys/role/updateall")
    public JsonResult updateRoleCon(String name, String Remark) {
        try {
            int i = service.updateRoleM(name, Remark);
            if (i>0) {
                json = new JsonResult("200", "success", i);
            } else {
                json = new JsonResult("404", "error", null);
            }
        } catch (Exception e) {
            json = new JsonResult("500", "error", e.getMessage());
        }
        return json;
    }

    //新增角色
    public JsonResult insertRoleCon(String name, String Remark) {
        try {
            int i = service.insertRoleM(name, Remark);
            if (i>0) {
                json = new JsonResult("200", "success", i);
            } else {
                json = new JsonResult("404", "error", null);
            }
        } catch (Exception e) {
            json = new JsonResult("500", "error", e.getMessage());
        }
        return json;
    }

    //根据用户名字查看角色
    public JsonResult queryRoleByNameCon(String name) {

        try {
            Role role = service.queryRoleByNameM(name);
            if (role != null) {
                json = new JsonResult("200", "success", role);
            } else {
                json = new JsonResult("404", "error", null);
            }
        } catch (Exception e) {
            json = new JsonResult("500", "error", e.getMessage());
        }
        return json;
    }

    //删除某个用户角色
    public JsonResult deleteRoleByIdCon(String UserId) {
        try {
            int i = service.deleteRoleByIdM(UserId);
            if (i >0) {
                json = new JsonResult("200", "success", i);
            } else {
                json = new JsonResult("404", "error", null);
            }
        } catch (Exception e) {
            json = new JsonResult("500", "error", e.getMessage());
        }
        return json;
    }

    //修改某个用户角色
    public JsonResult updateRoleBynameCon(String RodeId, String UserId) {
        try {
            int i = service.updateRoleBynameM(RodeId, UserId);
            if (i > 0) {
                json = new JsonResult("200", "success", i);
            } else {
                json = new JsonResult("404", "error", null);
            }
        } catch (Exception e) {
            json = new JsonResult("500", "error", e.getMessage());
        }
        return json;
    }

    //新增某个用户角色
    public JsonResult insertRoleBynameCon(String UserId, String RoleId) {
        try {
            int i = service.insertRoleBynameM(UserId, RoleId);
            if (i >0) {
                json = new JsonResult("200", "success", i);
            } else {
                json = new JsonResult("404", "error", null);
            }
        } catch (Exception e) {
            json = new JsonResult("500", "error", e.getMessage());
        }
        return json;
    }


}
