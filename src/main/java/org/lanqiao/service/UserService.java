package org.lanqiao.service;

import org.lanqiao.pojo.User;

import java.util.List;

public interface UserService {
  User queryByName(String username);

  List<User> ByName(String username, String role);

  User queryById(int userId);

  int deleteUser(int[] id);

  List<User> userList();

  public Long addUser(User user);
}
