package org.lanqiao.pojo;

import java.util.Date;

public class User {

  private Integer id;
  private String name;
  private String password;
  private String email;
  private String mobile;
  private byte status;
  private String createBy;
  private Date createTime;
  private String lastUpdateBy;
  private Date lastUpdateTime;
  private byte delFlag;
  private String perms;

  public String getPerms() {
    return perms;
  }

  public void setPerms(String perms) {
    this.perms = perms;
  }

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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public byte getStatus() {
    return status;
  }

  public void setStatus(byte status) {
    this.status = status;
  }

  public String getCreateBy() {
    return createBy;
  }

  public void setCreateBy(String createBy) {
    this.createBy = createBy;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public String getLastUpdateBy() {
    return lastUpdateBy;
  }

  public void setLastUpdateBy(String lastUpdateBy) {
    this.lastUpdateBy = lastUpdateBy;
  }

  public Date getLastUpdateTime() {
    return lastUpdateTime;
  }

  public void setLastUpdateTime(Date lastUpdateTime) {
    this.lastUpdateTime = lastUpdateTime;
  }

  public byte getDelFlag() {
    return delFlag;
  }

  public void setDelFlag(byte delFlag) {
    this.delFlag = delFlag;
  }

  @Override
  public String toString() {
    return "User{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", password='"
        + password
        + '\''
        + ", email='"
        + email
        + '\''
        + ", mobile='"
        + mobile
        + '\''
        + ", status="
        + status
        + ", createBy='"
        + createBy
        + '\''
        + ", createTime="
        + createTime
        + ", lastUpdateBy='"
        + lastUpdateBy
        + '\''
        + ", lastUpdateTime="
        + lastUpdateTime
        + ", delFlag="
        + delFlag
        + ", perms='"
        + perms
        + '\''
        + '}';
  }
}
