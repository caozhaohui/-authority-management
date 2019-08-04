package org.lanqiao.service;

import org.lanqiao.mapper.PrivsMapper;
import org.lanqiao.pojo.Privs;
import org.lanqiao.util.DateUtils;
import org.lanqiao.util.SqlUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    }

    /**
     *  修改目录菜单权限
     */
    public void updatePrivs(Privs privs){
        PrivsMapper privsMapper = new PrivsMapper();
        privsMapper.update(privs);
    }

    /**
     * 查询所有权限
     */
    public List<Privs> queryAll(){
        PrivsMapper privsMapper = new PrivsMapper();
        List<Privs> lists = privsMapper.queryList();
        List<Privs> menus = privsMapper.queryMenu();
        List<Privs> buttons = privsMapper.queryPrivs();
        for(Privs privsGrandFather : lists){
            List<Privs> newMenus = new ArrayList<>();
            for(Privs privsFather : menus){
                List<Privs> newButtons = new ArrayList<>();
                if(privsFather.getParentId()==privsGrandFather.getId()){
                    for(Privs privsSon : buttons){
                        if(privsSon.getParentId()==privsFather.getId()){
                            newButtons.add(privsSon);
                        }
                    }
                    newMenus.add(privsFather);
                }
                privsFather.setList(newButtons);
            }
            privsGrandFather.setList(newMenus);
        }
        return lists;
    }

    /**
     * 根据权限名称查询权限信息
     */
    public Privs findPrivsByName(String name){
        PrivsMapper privsMapper = new PrivsMapper();
        return privsMapper.findPrivsByName(name);
    }

    /**
     * 根据权限Id查询权限信息
     */
    public Privs findPrivsById(Integer privsId){
        PrivsMapper privsMapper = new PrivsMapper();
        return privsMapper.findPrivsById(privsId);
    }

    /**
     * 根据id查询权限信息
     */
    public List<Privs> queryAllById(Integer roleId){
        PrivsMapper privsMapper = new PrivsMapper();
        List<Privs> lists = privsMapper.queryListById(roleId);
        List<Privs> menus = privsMapper.queryMenuById(roleId);
        List<Privs> buttons = privsMapper.queryPrivsById(roleId);
        for(Privs privsGrandFather : lists){
            List<Privs> newMenus = new ArrayList<>();
            for(Privs privsFather : menus){
                List<Privs> newButtons = new ArrayList<>();
                if(privsFather.getParentId()==privsGrandFather.getId()){
                    for(Privs privsSon : buttons){
                        if(privsSon.getParentId()==privsFather.getId()){
                            newButtons.add(privsSon);
                        }
                    }
                    newMenus.add(privsFather);
                }
                privsFather.setList(newButtons);
            }
            privsGrandFather.setList(newMenus);
        }
        return lists;
    }


    /**
     * 给角色授权
     */
    public void empower(Integer roleId, Integer privsId, String createBy, Date createTime
                        ,String lastUpdateBy,Date lastUpdateTime){
        PrivsMapper privsMapper = new PrivsMapper();
        privsMapper.empower(roleId,privsId,createBy,createTime,lastUpdateBy,lastUpdateTime);
    }

    /**
     * 删除角色权限
     */
    public void deleteRolePrivs(Integer roleId,Integer privsId){
        PrivsMapper privsMapper = new PrivsMapper();
        privsMapper.deleteRolePrivs(roleId,privsId);
    }

    /**
     * 根据父级权限ID查询
     */
    public Privs findPrivsByParentId(Integer parentId){
        PrivsMapper privsMapper = new PrivsMapper();
        return privsMapper.findPrivsByParentId(parentId);
    }
}
