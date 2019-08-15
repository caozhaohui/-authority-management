//package org.lanqiao.mapper.impl;
//
//import org.apache.ibatis.io.Resources;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//import org.lanqiao.mapper.PrivsMapper;
//import org.lanqiao.pojo.Privs;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Repository
//public class PrivsMapperImpl implements PrivsMapper {
//
//
//    @Autowired
//    SqlSessionFactory sqlSessionFactory;
//
//    /*
//        添加权限
//     */
//    @Override
//    public void addPrivs(Privs privs) {
//        sqlSessionFactory.openSession().insert("org.example.privsMapper.addPrivs",privs);
//        sqlSessionFactory.openSession().commit();
//        sqlSessionFactory.openSession().close();
//    }
//
//    /*
//        查询所有权限
//     */
//    @Override
//    public List<Privs> queryAll() {
//        List<Privs> privses = null;
//        privses = sqlSessionFactory.openSession().selectList("org.example.privsMapper.queryAll");
//        sqlSessionFactory.openSession().close();
//        return privses;
//    }
//
//    /*
//        删除权限 逻辑删除
//     */
//    public void deletePrivs(Integer id){
//        sqlSessionFactory.openSession().update("org.example.privsMapper.deletePrivs",id);
//        sqlSessionFactory.openSession().commit();
//        sqlSessionFactory.openSession().close();
//    }
//
//
//    /*
//        动态菜单
//     */
//    public List<Privs> menusPrivs(Integer id){
//        List<Privs> privses = sqlSessionFactory.openSession().selectList("org.example.privsMapper.menusPrivs",id);
//        sqlSessionFactory.openSession().close();
//        return privses;
//    }
//
//    /*
//        修改权限
//     */
//    @Override
//    public void updatePrivs(Privs privs) {
//        sqlSessionFactory.openSession().update("org.example.privsMapper.updatePrivs",privs);
//        sqlSessionFactory.openSession().commit();
//        sqlSessionFactory.openSession().close();
//    }
//
//    /*
//        根据角色id查询权限信息
//     */
//    @Override
//    public List<Privs> queryByRoleId(Integer roleId){
//        List<Privs> privses = sqlSessionFactory.openSession().selectList("org.example.privsMapper.selectRolePrivs",roleId);
//        sqlSessionFactory.openSession().close();
//        return privses;
//    }
//
//    /*
//        给角色授权
//     */
//    @Override
//    public void addRolePrivs(Integer roleId,Integer privsId){
//        Map<String,Integer> map = new HashMap<>();
//        map.put("roleId",roleId);
//        map.put("privsId",privsId);
//        sqlSessionFactory.openSession().insert("org.example.privsMapper.addRolePrivs",map);
//        sqlSessionFactory.openSession().commit();
//        sqlSessionFactory.openSession().close();
//    }
//
//    /*
//        取消角色的权限
//     */
//    @Override
//    public void delRolePrivs(Integer roleId,Integer privsId){
//        Map<String,Integer> map = new HashMap<>();
//        map.put("roleId",roleId);
//        map.put("privsId",privsId);
//        sqlSessionFactory.openSession().delete("org.example.privsMapper.delRolePrivs",map);
//        sqlSessionFactory.openSession().commit();
//        sqlSessionFactory.openSession().close();
//    }
//
//    /*
//        根据权限id查询权限信息
//     */
//    @Override
//    public Privs queryPrivsById(Integer privsId) {
//        Privs privs = sqlSessionFactory.openSession().selectOne("org.example.privsMapper.selectByPrivsId",privsId);
//        sqlSessionFactory.openSession().close();
//        return privs;
//    }
//
//    /*
//        批量删除
//     */
//    @Override
//    public void someDel(int [] id) {
//        sqlSessionFactory.openSession().update("org.example.privsMapper.someDelPrivs",id);
//        sqlSessionFactory.openSession().commit();
//        sqlSessionFactory.openSession().close();
//    }
//
//    /*
//        多条件查询
//     */
//    @Override
//    public List<Privs> conditionSearch(Integer type,String name){
//        Map<String,Object> map = new HashMap<>();
//        map.put("type",type);
//        map.put("name",name);
//        List<Privs> privses = sqlSessionFactory.openSession().selectList("org.example.privsMapper.delRolePrivs",map);
//        sqlSessionFactory.openSession().close();
//        return privses;
//    }
//}
