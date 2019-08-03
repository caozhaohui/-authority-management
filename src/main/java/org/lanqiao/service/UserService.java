package org.lanqiao.service;

import org.lanqiao.mapper.UserMapper;
import org.lanqiao.pojo.User;
import org.lanqiao.vo.Menu;

import java.util.List;

public class UserService {
  // 通过用户名称查询用户
  public User queryByName(String username) {
    UserMapper map = new UserMapper();
    User user = map.queryByName(username);
    //    String perms = map.queryPerms(user.getId().toString());
    //    user.setPerms(perms);
    return user;
  }
  // 查询菜单类表
  public List<Menu> menuList(String userId) {
    UserMapper map = new UserMapper();
    List<Menu> son = map.querySonMenu(userId); // 获得登陆用户的权限
    List<Menu> father = map.queryFatherMenu(userId);

    // 这是动态菜单的核心
    for (Menu father1 : father) {
      for (Menu son1 : son) {
        if (son1.getParent_id() == (father1.getId())) {
          father1.getSonMenus().add(son1);
        }
      }
    }
    return father;
  }
  // 查询用户列表
  public List<User> userList() {
    UserMapper map = new UserMapper();
    List<User> user = map.selectAll();
    return user;
  }

  // 更新用户
  public  int updateUser(User user) {
    UserMapper map = new UserMapper();
    return map.updateUser(user);
  }
  // 添加用户
  public  int addUser(User user) {
    UserMapper map = new UserMapper();
    return map.addUser(user);
  }
   //删除用户
  public int deleteUser(String  id){
    UserMapper mapper=new UserMapper();
   return mapper.deleteUser(id);
  }
}
