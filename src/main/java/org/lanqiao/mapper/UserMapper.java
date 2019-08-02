package org.lanqiao.mapper;

import org.lanqiao.pojo.User;
import org.lanqiao.util.*;
import org.lanqiao.vo.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserMapper {

  public User queryByName(String username) {
    String sql = "select * from sys_user where name =?";
    ArrayList<HashMap<String, Object>> list = null;
    User user = null;
    try {
      list = SqlUtils.executeQuery(sql, username);
      if (list.size() > 0) {
        for (HashMap map : list) {
          user = new User();
          user.setId(IntegerUtils.toInt(map.get("id")));
          user.setName(StringUtils.toString(map.get("name")));
          user.setPassword(StringUtils.toString(map.get("password")));
          user.setEmail(StringUtils.toString(map.get("email")));
          user.setMobile(StringUtils.toString(map.get("moblie")));
          user.setStatus(ByteUtils.toByte(map.get("status")));
          user.setDelFlag(ByteUtils.toByte(map.get("del_flag")));
          user.setMobile(StringUtils.toString(map.get("mobile")));
          user.setCreateBy(StringUtils.toString(map.get("create_by")));
          user.setCreateTime(DateUtils.toDate(map.get("create_time").toString()));
          user.setPerms(queryPerms(StringUtils.toString(map.get("id"))));
        }
      }
    } catch (Exception ex) {
      ex.getStackTrace();
    }
    return user;
  }

  public List<User> selectAll() {
    String sql = "select * from sys_user ";
    ArrayList<HashMap<String, Object>> list = null;
    User user = null;
    List<User> list1 = new ArrayList <>();
    try {
      list = SqlUtils.executeQuery(sql);
      if (list.size() > 0) {
        for (HashMap map : list) {
          user = new User();
          user.setId(IntegerUtils.toInt(map.get("id")));
          user.setName(StringUtils.toString(map.get("name")));
          user.setPassword(StringUtils.toString(map.get("password")));
          user.setEmail(StringUtils.toString(map.get("email")));
          user.setMobile(StringUtils.toString(map.get("moblie")));
          user.setStatus(ByteUtils.toByte(map.get("status")));
          user.setDelFlag(ByteUtils.toByte(map.get("del_flag")));
          user.setMobile(StringUtils.toString(map.get("mobile")));
          user.setCreateBy(StringUtils.toString(map.get("create_by")));
          user.setCreateTime(DateUtils.toDate(map.get("create_time").toString()));
          user.setPerms(queryPerms(StringUtils.toString(map.get("id"))));
          list1.add(user);
        }
      }
    } catch (Exception ex) {
      ex.getStackTrace();
    }
    return list1;
  }

  // 查询用户权限
  public  String queryPerms(String userId) {
    String sql =
        "select e.perms  \n"
            + "from  \n"
            + "sys_user a \n"
            + "left join sys_user_role b on a.id = b.user_id \n"
            + "left join sys_role c on b.role_id = c.id \n"
            + "left join sys_role_menu d on c.id = d.role_id \n"
            + "left join  sys_menu e on d.menu_id = e.id  \n"
            + "where \n"
            + "e.del_flag = 0 and e.perms is not null and a.id =?";
    ArrayList<HashMap<String, Object>> list = null;
    StringBuffer stringBuffer = new StringBuffer();
    int i = 0;
    try {
      list = SqlUtils.executeQuery(sql, userId);
      int length = list.size();
      if (list.size() > 0) {
        for (HashMap map : list) {
          // 对权限组进行组装
          if (i < length - 1) {
            stringBuffer.append(UrlUtil.toUrl(map.get("perms") + ","));
            i++;
          } else {
            stringBuffer.append(UrlUtil.toUrl(map.get("perms")));
          }
        }
      }
    } catch (Exception ex) {
      ex.getStackTrace();
    }
    return stringBuffer.toString();
  }

  // 查询菜单子类表
  public List<Menu> querySonMenu(String userId) {
    String sql =
        "select e.id ,e.name ,e.parent_id,e.url ,e.perms,e.del_flag  \n"
            + "from  \n"
            + "sys_user a \n"
            + "left join sys_user_role b on a.id = b.user_id \n"
            + "left join sys_role c on b.role_id = c.id \n"
            + "left join sys_role_menu d on c.id = d.role_id \n"
            + "left join  sys_menu e on d.menu_id = e.id  \n"
            + "where \n"
            + "e.del_flag = 0 and e.parent_id = 1 and a.id =?";
    ArrayList<HashMap<String, Object>> list = null;
    Menu menu = null;
    List<Menu> list1 = new ArrayList<>();
    try {
      list = SqlUtils.executeQuery(sql, userId);
      if (list.size() > 0) {
        for (HashMap map : list) {
          menu = new Menu();
          menu.setId(IntegerUtils.toInt(map.get("id")));
          menu.setName(StringUtils.toString(map.get("name")));
          menu.setParent_id(IntegerUtils.toInt(map.get("parent_id")));
          menu.setUrl(StringUtils.toString(map.get("url")));
          menu.setPerms(UrlUtil.toUrl(map.get("perms")));
          menu.setFlag(ByteUtils.toByte(map.get("del_flag")));
          list1.add(menu);
        }
      }
    } catch (Exception ex) {
      ex.getStackTrace();
    }
    return list1;
  }
  // 查询菜单父类表
  public List<Menu> queryFatherMenu(String userId) {
    String sql =
        "select e.id ,e.name ,e.parent_id,e.url ,e.perms,e.del_flag  \n"
            + "from  \n"
            + "sys_user a \n"
            + "left join sys_user_role b on a.id = b.user_id \n"
            + "left join sys_role c on b.role_id = c.id \n"
            + "left join sys_role_menu d on c.id = d.role_id \n"
            + "left join  sys_menu e on d.menu_id = e.id  \n"
            + "where \n"
            + "e.del_flag = 0 and e.parent_id = 0 and a.id =?";
    ArrayList<HashMap<String, Object>> list = null;
    Menu menu = null;
    List<Menu> list1 = new ArrayList<>();
    try {
      list = SqlUtils.executeQuery(sql, userId);
      if (list.size() > 0) {
        for (HashMap map : list) {
          menu = new Menu();
          menu.setId(IntegerUtils.toInt(map.get("id")));
          menu.setName(StringUtils.toString(map.get("name")));
          menu.setParent_id(IntegerUtils.toInt(map.get("parent_id")));
          menu.setUrl(StringUtils.toString(map.get("url")));
          menu.setPerms(UrlUtil.toUrl(map.get("perms")));
          menu.setFlag(ByteUtils.toByte(map.get("del_flag")));
          list1.add(menu);
        }
      }
    } catch (Exception ex) {
      ex.getStackTrace();
    }
    return list1;
  }
}
