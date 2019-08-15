package org.lanqiao.service.impl;

import org.lanqiao.mapper.PrivsMapper;

import org.lanqiao.pojo.Privs;
import org.lanqiao.service.PrivsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PrivsServiceImpl implements PrivsService {


    @Autowired
    PrivsMapper privsMapper;

    //查询所有菜单 初始化页面
    @Override
    public List<Privs> queryAll() {
        List<Privs> privses = privsMapper.queryAll();
        List<Privs> menus =privsMapper.queryMenu();
        for(Privs privs : privses){
            for(int i = 0 ; i < privs.getList().size() ; i++){
                List<Privs> menuses = new ArrayList<>();
                for(int j = 0 ; j < menus.size() ; j++){
                    if(menus.get(j).getParentId() == privs.getList().get(i).getId()){
                        menuses.add(menus.get(j));
                    }
                }
                privs.getList().get(i).setList(menuses);
            }
        }
        return privses;
    }

    //添加权限
    @Override
    public void addPrivs(Privs privs) {
        privsMapper.addPrivs( privs);
    }

    //动态菜单
    public List<Privs> menusPrivs(Integer id){
        return privsMapper.menusPrivs(id);
    }



    //删除权限
    public void deletePrivs(Integer id){
        privsMapper.deletePrivs(id);
    }

    //修改权限
    @Override
    public void updatePrivs(Privs privs) {
        privsMapper.updatePrivs(privs);
    }

    //根据角色id查询权限信息
    @Override
    public List<Privs> queryByRoleId(Integer roleId) {
        return privsMapper.queryByRoleId(roleId);
    }

    //给角色授权
    @Override
    public void addRolePrivs(Integer roleId,Integer privsId){
        privsMapper.addRolePrivs(roleId,privsId);
    }

    //删除角色的权限
    @Override
    public void delRolePrivs(Integer roleId,Integer privsId){
        privsMapper.delRolePrivs(roleId,privsId);
    }

    //根据权限id查询权限信息
    @Override
    public Privs queryPrivsById(Integer privsId){
        return privsMapper.queryPrivsById(privsId);
    }

    //批量删除
    @Override
    public void someDel(int [] id) {
        privsMapper.someDel(id);
    }

    //多条件查询
    @Override
    public List<Privs> conditionSearch(Integer type,String name){
        return privsMapper.conditionSearch(type,name);
    }

    //查询上级权限
    @Override
    public List<Privs> queryLastPrivs(Integer type){
        List<Privs> list = new ArrayList<>();
        if(type == 1){
            list = privsMapper.queryOne();
        }else if(type == 2){
            list = privsMapper.queryTwo();
        }
        return list;
    }
}
