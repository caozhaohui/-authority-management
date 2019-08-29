package org.lanqiao.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.lanqiao.pojo.Role;
import org.lanqiao.pojo.RoleMenu;
import org.lanqiao.pojo.UserRole;
import org.lanqiao.service.RoleService;
import org.lanqiao.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/api")
@Controller
public class RoleController {

    @Autowired
    RoleService roleService;



    @Resource
    UserRole userRole;

    @Resource
    Role role;

    JsonResult json = null;

    //用户添加一个或多个角色
    @RequestMapping("/sys/insertRoleUser")
    public JsonResult insertRoleUser(Long uid, Long[] rid) {
        try {
            userRole.setUserId(uid);
            int i = roleService.insertRoleUser(userRole, rid);
            if (i > 0) {
                json = new JsonResult("200", "success", "添加成功");
            } else {
                json = new JsonResult("404", "error", null);
            }
        } catch (Exception e) {
            json = new JsonResult("500", "error", e.getMessage());
        }
        return json;
    }

    //删除一个用户的一个或多个角色
    @RequestMapping("/sys/delectRoleUser")
    public JsonResult delectRoleUser(Long uid, Long[] rid) {
        try {
            userRole.setUserId(uid);
            int i = roleService.delectRoleUser(userRole, rid);
            if (i > 0) {
                json = new JsonResult("200", "success", "删除成功");
            } else {
                json = new JsonResult("404", "error", null);
            }
        } catch (Exception e) {
            json = new JsonResult("500", "error", e.getMessage());
        }
        return json;
    }

    //修改某个用户一个或多个角色
    @RequestMapping("/sys/updateRoleUser")
    public JsonResult updateRoleUser(Long userId, Long roleId, Long setroleId) {
        try {
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRole.setSetroleId(setroleId);
            int i = roleService.updateRoleUser(userRole);
            if (i > 0) {
                json = new JsonResult("200", "success", "修改成功");
            } else {
                json = new JsonResult("404", "error", null);
            }
        } catch (Exception e) {
            json = new JsonResult("500", "error", e.getMessage());
        }
        return json;
    }

    //删除一个或多个角色
    @RequestMapping("/sys/deleteRoleById")
    public JsonResult deleteRoleById(String id) {
        try {
            int i = roleService.deleteRoleById(id);
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

    //新增角色
    @RequestMapping("/sys/insertRoleByAll")
    public JsonResult insertRoleByAll( String name, String remark, String create_by, Long [] mid) {
        try {
            role.setName(name);
            role.setRemark(remark);
            role.setCreateBy(create_by);

            roleService.insertRoleByAll(role, mid);

            json = new JsonResult("200", "success", "新增成功");

        } catch (Exception e) {
            System.out.println(e);
            json = new JsonResult("500", "error", e.getMessage());
        }
        return json;
    }

    //查询所有角色
    @RequestMapping("/sys/selectRoleAll")
    public JsonResult selectRoleAll(@RequestParam(value = "pageNum") int pageNum,
                                    @RequestParam(value = "pageSize") int pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            List list = roleService.selectRoleAll();

            PageInfo pageInfo=new PageInfo(list);

            if (list.size() > 0) {
                json = new JsonResult("200", "success", pageInfo);
            } else {
                json = new JsonResult("404", "error", null);
            }
        } catch (Exception e) {
            json = new JsonResult("500", "error", e.getMessage());
        }
        return json;
    }
}
