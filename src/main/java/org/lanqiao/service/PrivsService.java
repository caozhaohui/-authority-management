package org.lanqiao.service;

import org.lanqiao.mapper.PrivsMapper;
import org.lanqiao.pojo.Privs;

public class PrivsService {

    /**
     * 添加目录菜单权限的内容
     */
    public void addPrivs(Privs privs){
        PrivsMapper privsMapper = new PrivsMapper();
        privsMapper.addPrivs(privs);
    }

    /**
     * 删除目录菜单权限（更改状态）
     */
    public void deletePrivs(Integer privsId){
        PrivsMapper privsMapper = new PrivsMapper();
        privsMapper.delete(privsId);
        System.out.println("刘粤是瓜皮");
    }
}
