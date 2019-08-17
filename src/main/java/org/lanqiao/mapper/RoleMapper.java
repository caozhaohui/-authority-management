package Dao;

import Pojo.Role;
import Pojo.RoleMenu;
import Pojo.UserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;


@Repository
public interface RoleMapper {

    //新增某个用户一个或多个角色
    public int insertRoleUser(List list);
    //删除某个用户一个或多个角色
    public int delectRoleUser(List<UserRole> list);
    //修改某个用户某个角色
    public int updateRoleUser(UserRole urid);
    //新增一个角色
    public int insertRoleByAll(Role role);
    //新增一个新的角色同时为它添加权限
    public int insertRoleMenu(List list);
    //查询所有角色
    public List selectRoleAll();
    //删除一个或多个角色
    public int deleteRoleById(List<String> id);
}
