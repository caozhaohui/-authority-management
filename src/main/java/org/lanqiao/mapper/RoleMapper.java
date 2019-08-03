package org.lanqiao.mapper;

import org.lanqiao.pojo.Role;
import org.lanqiao.util.DateUtils;
import org.lanqiao.util.IntegerUtils;
import org.lanqiao.util.SqlUtils;
import org.lanqiao.vo.PageResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class RoleMapper {


    //查询所有角色带分页
    public PageResult queryAllRole(int pageNum, int pageSize) {
        String sql = "select * from sys_role limit ?,?";
        List<HashMap<String, Object>> list = SqlUtils.executeQuery(sql);
        List<Role> list2 = new ArrayList<Role>();
        Role role = null;
        for (int i = 0; i < list.size(); i++) {
            try {
                role = new Role();
                role.setId(IntegerUtils.toInt(list.get(i).get("id").toString()));
                role.setName(list.get(i).get("name").toString());
                role.setRemark(list.get(i).get("remark").toString());
                role.setCreateBy(list.get(i).get("create_by").toString());
                role.setCreateTime((java.sql.Date) DateUtils.toDate(list.get(i).get("create_time").toString()));
                role.setDelFlag(IntegerUtils.toInt(list.get(i).get("del_flag").toString()));
                list2.add(role);
            } catch (Exception e) {

            }
        }

        PageResult pageResult = new PageResult();
        pageResult.setList(list2);
        pageResult.setPageNum(pageNum);
        pageResult.setPageSize(pageSize);


        String sql2 = "select count(*) as row from sys_role";
        List<HashMap<String, Object>> countlist = SqlUtils.executeQuery(sql2);
        String count = String.valueOf(countlist.get(0).get("row"));
        int size = Integer.parseInt(count);
        pageResult.setTotal(size);
        pageResult.setPages(size % pageSize == 0 ? size / pageSize : size / pageSize + 1);
        return pageResult;

    }

    //删除用户
    public int deleteRole(String RoleId) {
        String sql = "update sys_role set del_flag=-1 where id=?";
        int i = SqlUtils.executeUpdate(sql, RoleId);
        int x = 0;
        if (i > 0) {
            x = i;
        }
        return x;
    }

    //修改角色
    public int updateRole(String name, String Remark) {
        String sql = "update sys_role set name=? , remark=? where id=?";
        int i = SqlUtils.executeUpdate(sql, name, Remark);
        int x = 0;
        if (i > 0) {
            x = i;
        }
        return x;
    }

    //新增角色
    public int insertRole(String name, String Remark) {
        String sql = "INSERT INTO sys_role (name,remark,create_by,create_time,del_flag) VALUES (?,?,'admin',NOW(),'0')";
        int i = SqlUtils.executeUpdate(sql, name, Remark);
        int x = 0;
        if (i > 0) {
            x = i;
        }
        return x;
    }

    //根据用户名字查看角色
    public Role queryRoleByName(String name) {
        String sql = "select * from sys_user a left join sys_user_role b on a.id=b.user_id left join sys_role c on b.role_id=c.id where a.name=?";
        List<HashMap<String, Object>> list = SqlUtils.executeQuery(sql, name);
        Role role = null;
        if (list.size() > 0) {
            try {
                role = new Role();
                role.setId(IntegerUtils.toInt(list.get(0).get("id").toString()));
                role.setName(list.get(0).get("name").toString());
                role.setRemark(list.get(0).get("remark").toString());
                role.setCreateBy(list.get(0).get("create_by").toString());
                role.setCreateTime((java.sql.Date) DateUtils.toDate(list.get(0).get("create_time").toString()));
                role.setDelFlag(IntegerUtils.toInt(list.get(0).get("del_flag").toString()));
            } catch (Exception e) {

            }
        }

        return role;
    }

    //删除某个用户角色
    public int deleteRoleById(String UserId) {
        String sql = "delete from sys_user_role where user_id=?";
        int i = SqlUtils.executeUpdate(sql, UserId);
        int x = 0;
        if (i > 0) {
            x = i;
        }
        return x;
    }

    //修改某个用户角色
    public int updateRoleByname(String RodeId, String UserId) {
        String sql = "update sys_user_role set role_id=? where user_id=?";
        int i = SqlUtils.executeUpdate(sql, RodeId, UserId);
        int x = 0;
        if (i > 0) {
            x = i;
        }
        return x;
    }

    //新增某个用户角色
    public int insertRoleByname(String UserId, String RoleId) {
        String sql = "insert into sys_user_role (user_id,role_id) values (?,?)";
        int i = SqlUtils.executeUpdate(sql, UserId, RoleId);
        int x = 0;
        if (i > 0) {
            x = i;
        }
        return x;
    }

}
