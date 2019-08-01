package org.lanqiao.mapper;

import org.lanqiao.pojo.Privs;
import org.lanqiao.util.SqlUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PrivsMapper {

    /**
     * 添加权限 / 菜单 / 目录
     *      2       1   0
     */
    public int addPrivs(Privs privs){
        String sql = "insert into sys_menu (name,parent_id,url,perms,type,icon," +
                "create_by,create_time,last_update_by,last_update_time,del_flag values(?,?,?" +
                ",?,?,?,?,?,?,?,?)";
        int i = SqlUtils.executeUpdate(sql,privs.getName(),privs.getParentId(),privs.getUrl(),privs.getPerms(),
                privs.getType(),privs.getIcon(),privs.getCreateBy(),privs.getCreateTime(),privs.getLastUpdateBy(),
                privs.getLastUpdateTime(),privs.getDelFlag());
        SqlUtils.close();
        return i;

    }

    /**
     * 查询所有目录
     */
    public List<Privs> queryAll(){
        String sql = "select * from sys_menu where type = 0";
        List<HashMap<String,Object>> list =  SqlUtils.executeQuery(sql);
        List<Privs> catalogues = null;
        if(list.size() > 0){
            catalogues = new ArrayList<>();

        }
        return catalogues;
    }
}
