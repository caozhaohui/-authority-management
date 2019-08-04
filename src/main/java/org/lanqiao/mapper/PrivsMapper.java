package org.lanqiao.mapper;

import org.lanqiao.pojo.Privs;
import org.lanqiao.util.DateUtils;
import org.lanqiao.util.IntegerUtils;
import org.lanqiao.util.SqlUtils;
import org.lanqiao.util.StringUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PrivsMapper {

    /**
     * 添加权限 / 菜单 / 目录
     *      2       1   0
     */
    public int addPrivs(Privs privs){
        String sql = "insert into sys_menu (name,parent_id,url,perms,type,icon," +
                "create_by,create_time,del_flag) values(?,?,?" +
                ",?,?,?,?,?,?)";
        int i = SqlUtils.executeUpdate(sql,privs.getName(),privs.getParentId(),privs.getUrl(),privs.getPerms(),
                privs.getType(),privs.getIcon(),privs.getCreateBy(),privs.getCreateTime(),
                privs.getDelFlag());
        SqlUtils.close();
        return i;

    }

    /**
     * 查询所有目录
     */
    public List<Privs> queryList(){
        String sql = "select * from sys_menu where type = 0";
        List<HashMap<String,Object>> list =  SqlUtils.executeQuery(sql);
        List<Privs> catalogues = null;
        if(list.size() > 0){
            catalogues = new ArrayList<>();
            for(HashMap<String,Object> map : list){
                Privs privs = new Privs();
                privs.setId(IntegerUtils.toInt(map.get("id")));
                privs.setName(StringUtils.toString(map.get("name")));
                privs.setParentId(IntegerUtils.toInt(map.get("parent_id")));
                privs.setUrl(StringUtils.toString(map.get("url")));
                privs.setPerms(StringUtils.toString(map.get("perms")));
                privs.setType(IntegerUtils.toInt(map.get("type")));
                privs.setIcon(StringUtils.toString(map.get("icon")));
                privs.setCreateBy(StringUtils.toString(map.get("create_by")));
                try {
                    privs.setCreateTime(DateUtils.toDate(StringUtils.toString(map.get("create_time"))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                privs.setLastUpdateBy(StringUtils.toString(map.get("lastUpdateBy")));
                try {
                    privs.setLastUpdateTime(DateUtils.toDate(StringUtils.toString(map.get("last_update_time"))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                privs.setDelFlag(IntegerUtils.toInt(map.get("del_flag")));
                catalogues.add(privs);
            }
        }
        return catalogues;
    }

    /**
     * 查询1级菜单
     */
    public List<Privs> queryMenu(){
        String sql = "select * from sys_menu where type = 1";
        List<HashMap<String,Object>> list =  SqlUtils.executeQuery(sql);
        List<Privs> menus = null;
        if(list.size() > 0){
            menus = new ArrayList<>();
            for(HashMap<String,Object> map : list){
                Privs privs = new Privs();
                privs.setId(IntegerUtils.toInt(map.get("id")));
                privs.setName(StringUtils.toString(map.get("name")));
                privs.setParentId(IntegerUtils.toInt(map.get("parent_id")));
                privs.setUrl(StringUtils.toString(map.get("url")));
                privs.setPerms(StringUtils.toString(map.get("perms")));
                privs.setType(IntegerUtils.toInt(map.get("type")));
                privs.setIcon(StringUtils.toString(map.get("icon")));
                privs.setCreateBy(StringUtils.toString(map.get("create_by")));
                try {
                    privs.setCreateTime(DateUtils.toDate(StringUtils.toString(map.get("create_time"))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                privs.setLastUpdateBy(StringUtils.toString(map.get("lastUpdateBy")));
                try {
                    privs.setLastUpdateTime(DateUtils.toDate(StringUtils.toString(map.get("last_update_time"))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                privs.setDelFlag(IntegerUtils.toInt(map.get("del_flag")));
                menus.add(privs);
            }
        }
        SqlUtils.close();
        return menus;
    }

    /**
     * 查询2级权限
     */
    public List<Privs> queryPrivs(){
        String sql = "select * from sys_menu where type = 2";
        List<HashMap<String,Object>> list =  SqlUtils.executeQuery(sql);
        List<Privs> thridPrivs = null;
        if(list.size() > 0){
            thridPrivs = new ArrayList<>();
            for(HashMap<String,Object> map : list){
                Privs privs = new Privs();
                privs.setId(IntegerUtils.toInt(map.get("id")));
                privs.setName(StringUtils.toString(map.get("name")));
                privs.setParentId(IntegerUtils.toInt(map.get("parent_id")));
                privs.setUrl(StringUtils.toString(map.get("url")));
                privs.setPerms(StringUtils.toString(map.get("perms")));
                privs.setType(IntegerUtils.toInt(map.get("type")));
                privs.setIcon(StringUtils.toString(map.get("icon")));
                privs.setCreateBy(StringUtils.toString(map.get("create_by")));
                try {
                    privs.setCreateTime(DateUtils.toDate(StringUtils.toString(map.get("create_time"))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                privs.setLastUpdateBy(StringUtils.toString(map.get("lastUpdateBy")));
                try {
                    privs.setLastUpdateTime(DateUtils.toDate(StringUtils.toString(map.get("last_update_time"))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                privs.setDelFlag(IntegerUtils.toInt(map.get("del_flag")));
                thridPrivs.add(privs);
            }
        }
        SqlUtils.close();
        return thridPrivs;
    }

    /**
     * 修改目录/菜单/权限
     */
    public int update(Privs privs){
        String sql = "update sys_menu set name=?,parent_id=?,url=?,perms=?,type=?,icon=?," +
                "create_by=?,create_time=?,last_update_by=?,last_update_time=?,del_flag=? " +
                "where id = ?";
        int i = SqlUtils.executeUpdate(sql,privs.getName(),privs.getParentId(),privs.getUrl(),privs.getPerms(),
                privs.getType(),privs.getIcon(),privs.getCreateBy(),privs.getCreateTime(),privs.getLastUpdateBy(),
                privs.getLastUpdateTime(),privs.getDelFlag(),privs.getId());
        SqlUtils.close();
        return i;
    }


    /**
     *  删除目录/菜单/权限（逻辑删除）或者叫做禁用  状态修改
     */
    public int delete(Integer privsId){
        String sql = "update sys_menu set del_flag = -1 where id = ?";
        int i = SqlUtils.executeUpdate(sql,privsId);
        SqlUtils.close();
        return i;
    }


    /**
     * 根据权限名称查权限信息
     */
    public Privs findPrivsByName(String name){
        String sql = "select * from sys_menu where name = ?";
        List<HashMap<String,Object>> list =  SqlUtils.executeQuery(sql,name);
        Privs privs = null;
        if(list.size() > 0) {
            privs = new Privs();
            privs.setId(IntegerUtils.toInt(list.get(0).get("id")));
            privs.setName(StringUtils.toString(list.get(0).get("name")));
            privs.setParentId(IntegerUtils.toInt(list.get(0).get("parent_id")));
            privs.setUrl(StringUtils.toString(list.get(0).get("url")));
            privs.setPerms(StringUtils.toString(list.get(0).get("perms")));
            privs.setType(IntegerUtils.toInt(list.get(0).get("type")));
            privs.setIcon(StringUtils.toString(list.get(0).get("icon")));
            privs.setCreateBy(StringUtils.toString(list.get(0).get("create_by")));
            try {
                privs.setCreateTime(DateUtils.toDate(StringUtils.toString(list.get(0).get("create_time"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            privs.setLastUpdateBy(StringUtils.toString(list.get(0).get("lastUpdateBy")));
            try {
                privs.setLastUpdateTime(DateUtils.toDate(StringUtils.toString(list.get(0).get("last_update_time"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            privs.setDelFlag(IntegerUtils.toInt(list.get(0).get("del_flag")));
        }
        return privs;
    }

    /**
     * 根据权限ID 查询权限信息
     */
    public Privs findPrivsById(Integer privsId){
        String sql = "select * from sys_menu where id = ?";
        List<HashMap<String,Object>> list =  SqlUtils.executeQuery(sql,privsId);
        Privs privs = null;
        if(list.size() > 0) {
            privs = new Privs();
            privs.setId(IntegerUtils.toInt(list.get(0).get("id")));
            privs.setName(StringUtils.toString(list.get(0).get("name")));
            privs.setParentId(IntegerUtils.toInt(list.get(0).get("parent_id")));
            privs.setUrl(StringUtils.toString(list.get(0).get("url")));
            privs.setPerms(StringUtils.toString(list.get(0).get("perms")));
            privs.setType(IntegerUtils.toInt(list.get(0).get("type")));
            privs.setIcon(StringUtils.toString(list.get(0).get("icon")));
            privs.setCreateBy(StringUtils.toString(list.get(0).get("create_by")));
            try {
                privs.setCreateTime(DateUtils.toDate(StringUtils.toString(list.get(0).get("create_time"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            privs.setLastUpdateBy(StringUtils.toString(list.get(0).get("last_update_by")));
            try {
                privs.setLastUpdateTime(DateUtils.toDate(StringUtils.toString(list.get(0).get("last_update_time"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            privs.setDelFlag(IntegerUtils.toInt(list.get(0).get("del_flag")));
        }
        return privs;
    }



    /**
     * 根据角色Id查询目录
     */
    public List<Privs> queryListById(Integer roleId){
        String sql = "select * from sys_menu sm left join sys_role_menu srm on sm.id = srm.menu_id" +
                " left join sys_role sr on srm.role_id = sr.id where sr.id = ? and sm.type = 0";
        List<HashMap<String,Object>> list = SqlUtils.executeQuery(sql,roleId);
        List<Privs> allPrivs = null;
        if(list.size() > 0){
            allPrivs = new ArrayList<>();
            for(HashMap<String,Object> map : list){
                Privs privs = new Privs();
                privs.setId(IntegerUtils.toInt(map.get("id")));
                privs.setName(StringUtils.toString(map.get("name")));
                privs.setParentId(IntegerUtils.toInt(map.get("parent_id")));
                privs.setUrl(StringUtils.toString(map.get("url")));
                privs.setPerms(StringUtils.toString(map.get("perms")));
                privs.setType(IntegerUtils.toInt(map.get("type")));
                privs.setIcon(StringUtils.toString(map.get("icon")));
                privs.setCreateBy(StringUtils.toString(map.get("create_by")));
                try {
                    privs.setCreateTime(DateUtils.toDate(StringUtils.toString(map.get("create_time"))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                privs.setLastUpdateBy(StringUtils.toString(map.get("lastUpdateBy")));
                try {
                    privs.setLastUpdateTime(DateUtils.toDate(StringUtils.toString(map.get("last_update_time"))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                privs.setDelFlag(IntegerUtils.toInt(map.get("del_flag")));
                allPrivs.add(privs);
            }
        }
        SqlUtils.close();
        return allPrivs;
    }



    /**
     * 根据用户Id查询菜单
     */
    public List<Privs> queryMenuById(Integer roleId){
        String sql = "select * from sys_menu sm left join sys_role_menu srm on sm.id = srm.menu_id" +
                " left join sys_role sr on srm.role_id = sr.id where sr.id = ? and sm.type = 1";
        List<HashMap<String,Object>> list = SqlUtils.executeQuery(sql,roleId);
        List<Privs> allPrivs = null;
        if(list.size() > 0){
            allPrivs = new ArrayList<>();
            for(HashMap<String,Object> map : list){
                Privs privs = new Privs();
                privs.setId(IntegerUtils.toInt(map.get("id")));
                privs.setName(StringUtils.toString(map.get("name")));
                privs.setParentId(IntegerUtils.toInt(map.get("parent_id")));
                privs.setUrl(StringUtils.toString(map.get("url")));
                privs.setPerms(StringUtils.toString(map.get("perms")));
                privs.setType(IntegerUtils.toInt(map.get("type")));
                privs.setIcon(StringUtils.toString(map.get("icon")));
                privs.setCreateBy(StringUtils.toString(map.get("create_by")));
                try {
                    privs.setCreateTime(DateUtils.toDate(StringUtils.toString(map.get("create_time"))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                privs.setLastUpdateBy(StringUtils.toString(map.get("lastUpdateBy")));
                try {
                    privs.setLastUpdateTime(DateUtils.toDate(StringUtils.toString(map.get("last_update_time"))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                privs.setDelFlag(IntegerUtils.toInt(map.get("del_flag")));
                allPrivs.add(privs);
            }
        }
        SqlUtils.close();
        return allPrivs;
    }

    /**
     * 根据角色Id查询权限
     */
    public List<Privs> queryPrivsById(Integer roleId){
        String sql = "select * from sys_menu sm left join sys_role_menu srm on sm.id = srm.menu_id" +
                " left join sys_role sr on srm.role_id = sr.id where sr.id = ? and sm.type = 2";
        List<HashMap<String,Object>> list = SqlUtils.executeQuery(sql,roleId);
        List<Privs> allPrivs = null;
        if(list.size() > 0){
            allPrivs = new ArrayList<>();
            for(HashMap<String,Object> map : list){
                Privs privs = new Privs();
                privs.setId(IntegerUtils.toInt(map.get("id")));
                privs.setName(StringUtils.toString(map.get("name")));
                privs.setParentId(IntegerUtils.toInt(map.get("parent_id")));
                privs.setUrl(StringUtils.toString(map.get("url")));
                privs.setPerms(StringUtils.toString(map.get("perms")));
                privs.setType(IntegerUtils.toInt(map.get("type")));
                privs.setIcon(StringUtils.toString(map.get("icon")));
                privs.setCreateBy(StringUtils.toString(map.get("create_by")));
                try {
                    privs.setCreateTime(DateUtils.toDate(StringUtils.toString(map.get("create_time"))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                privs.setLastUpdateBy(StringUtils.toString(map.get("lastUpdateBy")));
                try {
                    privs.setLastUpdateTime(DateUtils.toDate(StringUtils.toString(map.get("last_update_time"))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                privs.setDelFlag(IntegerUtils.toInt(map.get("del_flag")));
                allPrivs.add(privs);
            }
        }
        SqlUtils.close();
        return allPrivs;
    }

    /**
     * 授权
     */
    public int empower(Integer roleId, Integer privsId, String createBy, Date createTime,
                       String lastUpdateBy,Date lastUpdateTime){
        String sql = "insert into sys_role_menu (role_id,menu_id,create_by," +
                "create_time,last_update_by,last_update_time) values (?,?,?,?,?,?)";
        int i = SqlUtils.executeUpdate(sql,roleId,privsId,createBy,createTime,lastUpdateBy,lastUpdateTime);
        SqlUtils.close();
        return i;
    }


    /**
     * 根据角色Id删除权限或菜单或目录
     */
    public int deleteRolePrivs(Integer roleId,Integer privsId){
        String sql = "delete from sys_role_menu where role_id = ? and menu_id = ?";
        int i = SqlUtils.executeUpdate(sql,roleId,privsId);
        SqlUtils.close();
        return i;

    }

    /**
     * 自查 根据父级权限id查名字
     */
    public Privs findPrivsByParentId(Integer parentId){
        String sql = "select * from sys_menu where parent_id = ? and ";
        List<HashMap<String,Object>> list =  SqlUtils.executeQuery(sql,parentId);
        Privs privs = null;
        if(list.size() > 0) {
            privs = new Privs();
            privs.setId(IntegerUtils.toInt(list.get(0).get("id")));
            privs.setName(StringUtils.toString(list.get(0).get("name")));
            privs.setParentId(IntegerUtils.toInt(list.get(0).get("parent_id")));
            privs.setUrl(StringUtils.toString(list.get(0).get("url")));
            privs.setPerms(StringUtils.toString(list.get(0).get("perms")));
            privs.setType(IntegerUtils.toInt(list.get(0).get("type")));
            privs.setIcon(StringUtils.toString(list.get(0).get("icon")));
            privs.setCreateBy(StringUtils.toString(list.get(0).get("create_by")));
            try {
                privs.setCreateTime(DateUtils.toDate(StringUtils.toString(list.get(0).get("create_time"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            privs.setLastUpdateBy(StringUtils.toString(list.get(0).get("lastUpdateBy")));
            try {
                privs.setLastUpdateTime(DateUtils.toDate(StringUtils.toString(list.get(0).get("last_update_time"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            privs.setDelFlag(IntegerUtils.toInt(list.get(0).get("del_flag")));
        }
        return privs;
    }
}
