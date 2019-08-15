package org.lanqiao.mapper;

import org.apache.ibatis.annotations.Param;
import org.lanqiao.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
      int deleteByPrimaryKey(int[] id);
  //
      int insert(User record);
  //
      int insertSelective(User record);
  //
  //    User selectByPrimaryKey(long id);

  User selectByName(String username);

  List<User> ByName(@Param("username") String username, @Param("role") String role);

  //    int updateByPrimaryKeySelective(User record);
  //
  //    int updateByPrimaryKey(User record);
  //
  //
  User queryById(int userId);

  List<User> selectAll();
}
