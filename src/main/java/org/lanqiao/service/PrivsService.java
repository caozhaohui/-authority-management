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
}
