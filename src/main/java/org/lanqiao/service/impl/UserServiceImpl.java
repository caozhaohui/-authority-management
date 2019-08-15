package org.lanqiao.service.impl;

import org.lanqiao.mapper.UserMapper;
import org.lanqiao.pojo.User;
import org.lanqiao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserMapper userMapper;

  @Override
  public User queryByName(String username) {
    // 通过用户名称查询用户
    User user = null;
    try {
      System.out.println("我进来了");
      user = userMapper.selectByName(username);
      System.out.println("我能出来吗"+user);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return user;
  }

  @Override
  public List<User> ByName(String username, String role) {
    // 通过用户名称查询用户
    List<User> user = null;
    try {
      user = userMapper.ByName(username, role);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return user;
  }

  //  public static void main(String[] args) {
  //    UserService userService = new UserService();
  //    User user = userService.queryByName("admin");
  //    System.out.println(user.toString());
  //  }
  // 通过用户ID查询用户
  @Override
  public User queryById(int userId) {

    User user = null;
    try {
      user = userMapper.queryById(userId);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return user;
  }
  //
  // 查询用户列表

  @Override
  public List<User> userList() {
    List<User> user = null;
    try {
      user = userMapper.selectAll();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return user;
  }
  //
  //  // 更新用户
  //  public int updateUser(User user) {
  //    SqlSession sqlSession = MybatisUtil.openSession();
  //    int i = sqlSession.selectOne("org.lanqiao.mapper.UserMapper.updateByPrimaryKey", user);
  //    sqlSession.close();
  //    return i;
  //  }
  // 添加用户
  @Transactional
  @Override
  public Long addUser(User user) {
    Long id = null;
    try {
      userMapper.insert(user);
      id = user.getId();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    System.out.println("得到的id" + id);
    // 需要给角色
    // TODO
    return id;
  }
  // 删除用户

  @Override
  public int deleteUser(int[] id) {
    int i = userMapper.deleteByPrimaryKey(id);
    System.out.println("返回的删除的个数" + i);
    return i;
  }
}
