package org.lanqiao.service;


import org.lanqiao.mapper.RoleMapper;
import org.lanqiao.pojo.Role;
import org.lanqiao.pojo.RoleMenu;
import org.lanqiao.pojo.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RoleMenu roleMenu;

    @Autowired
    UserRole userRole;


    //新增某个用户一个或多个角色
    public int insertRoleUser(UserRole uid, Long[] rid) {
        List<UserRole> list = new ArrayList<UserRole>();
        int j = 0;
        for (Long i : rid) {
            userRole.setUserId(uid.getUserId());
            userRole.setRoleId(i);
            list.add(userRole);
            j = roleMapper.insertRoleUser(list);
        }
        return j;
    }


    //删除一个用户一个或多个角色
    public int delectRoleUser(UserRole uid, Long[] rid) {
        List<UserRole> list = new ArrayList<UserRole>();
        int j = 0;
        for (Long i : rid) {
            userRole.setUserId(uid.getUserId());
            userRole.setRoleId(i);
            list.add(userRole);
            j = roleMapper.delectRoleUser(list);
        }
        return j;

    }


    //修改某个用户某个角色
    public int updateRoleUser(UserRole urid) {
        int i = roleMapper.updateRoleUser(urid);
        return i;
    }


    //添加一个新的角色同时为它添加权限
    public void insertRoleByAll(Role role, Long[] mid) {
        //先将一个角色存入到角色表中
        roleMapper.insertRoleByAll(role);
        List<RoleMenu> list = new ArrayList<RoleMenu>();
        //循环传入的权限id
        for (Long i : mid) {
            //循环设置权限id对应一个角色
            roleMenu.setMenuId(i);
            roleMenu.setRoleId(role.getId());
            //把中间表信息循环加入list
            list.add(roleMenu);
            roleMapper.insertRoleMenu(list);
        }
        //将对应的角色id和权限id 将入到中间表中
    }


    //查询所有角色
    public List selectRoleAll() {
        return roleMapper.selectRoleAll();
    }


    //删除一个或多个角色
    public int deleteRoleById(String id) {
        List<String> list = new ArrayList<String>();
        String[] stIds = id.split(",");
        for (String value : stIds) {
            list.add(value);
        }
        return roleMapper.deleteRoleById(list);
    }
}
