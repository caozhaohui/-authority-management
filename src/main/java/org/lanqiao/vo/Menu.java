package org.lanqiao.vo;


import java.util.ArrayList;
import java.util.List;

public class Menu {

    private Integer id;
    private String name;
    private Integer parent_id;
    private String url;
    private String perms;
    private int type;
    private byte delFlag;



    public byte getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(byte delFlag) {
        this.delFlag = delFlag;
    }



    List<Menu> sonMenus = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Menu> getSonMenus() {
        return sonMenus;
    }

    public void setSonMenus(List<Menu> sonMenus) {
        this.sonMenus = sonMenus;
    }



}
