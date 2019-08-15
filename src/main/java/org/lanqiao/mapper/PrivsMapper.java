package org.lanqiao.mapper;

import org.apache.ibatis.annotations.Param;
import org.lanqiao.pojo.Privs;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrivsMapper {
    /*
        查询所有权限
     */
    List<Privs> queryAll();
    /*
        查询按钮类型
     */
    List<Privs> queryMenu();

    /*
        增加权限
     */
    void addPrivs(Privs privs);
    /*
        删除权限
     */
    void deletePrivs(Integer id);
    /*
        动态菜单
     */
    List<Privs> menusPrivs(Integer id);

    /*
        修改权限
     */
    void updatePrivs(Privs privs);

    /*
        根据角色Id查询权限信息
     */
    List<Privs> queryByRoleId(Integer roleId);

    /*
        给角色授权
     */
    void addRolePrivs(@Param("roleId") Integer roleId, @Param("privsId") Integer privsId);

    /*
        取消角色的权限
     */
    void delRolePrivs(@Param("roleId")Integer roleId,@Param("privsId")Integer privsId);

    /*
        根据权限id查询权限信息
     */
    Privs queryPrivsById(Integer privsId);

    /*
        批量删除
     */
    void someDel(int [] id);

    /*
        多条件查询
     */
    List<Privs> conditionSearch(@Param("type")Integer type,@Param("name")String name);

    /*
        查询目录
     */
    List<Privs> queryOne();

    /*
        查询菜单
     */
    List<Privs> queryTwo();
}
