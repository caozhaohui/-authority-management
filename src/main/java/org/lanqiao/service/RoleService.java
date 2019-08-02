package org.lanqiao.service;

import org.lanqiao.mapper.RoleMapper;
import org.lanqiao.pojo.Role;
import org.lanqiao.vo.PageResult;

public class RoleService {
    RoleMapper mapper = new RoleMapper();

    //查询所有角色带分页
    public PageResult queryAllRoleM(int pageNum, int pageSize) {
        return mapper.queryAllRole(pageNum, pageSize);
    }

    //删除用户
    public int deleteRoleM(String RoleId) {
        return mapper.deleteRole(RoleId);
    }

    //修改角色
    public int updateRoleM(String name, String Remark) {
        return mapper.updateRole(name, Remark);
    }

    //新增角色
    public int insertRoleM(String name, String Remark) {
        return mapper.insertRole(name, Remark);
    }

    //根据用户名字查看角色
    public Role queryRoleByNameM(String name) {

        return mapper.queryRoleByName(name);
    }

    //删除某个用户角色
    public int deleteRoleByIdM(String UserId) {
        return mapper.deleteRoleById(UserId);
    }

    //修改某个用户角色
    public int updateRoleBynameM(String RodeId, String UserId) {
        return mapper.updateRoleByname(RodeId, UserId);
    }

    //新增某个用户角色
    public int insertRoleBynameM(String UserId, String RoleId) {
        return mapper.insertRoleByname(UserId, RoleId);
    }


}
