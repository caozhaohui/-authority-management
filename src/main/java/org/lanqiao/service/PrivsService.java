package org.lanqiao.service;

import org.lanqiao.mapper.PrivsMapper;
import org.lanqiao.pojo.Privs;
import org.lanqiao.util.DateUtils;
import org.lanqiao.util.SqlUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface PrivsService {
    //查询所有权限
    List<Privs> queryAll();

    //添加权限
    void addPrivs(Privs privs);

    //删除权限
    void deletePrivs(Integer id);

    //动态菜单
    List<Privs> menusPrivs(Integer id);

    //修改权限
    void updatePrivs(Privs privs);

    //根据id查询角色的权限的信息
    List<Privs> queryByRoleId(Integer roleId);

    //给角色授权
    void addRolePrivs(Integer roleId,Integer privsId);

    //删除角色的权限
    void delRolePrivs(Integer roleId,Integer privsId);

    //根据权限id查询权限信息
    Privs queryPrivsById(Integer privsId);

    //批量删除
    void someDel(int [] id);

    //多条件查询
    List<Privs> conditionSearch(Integer type,String name);

    //查询上级权限
    List<Privs> queryLastPrivs(Integer type);
}
