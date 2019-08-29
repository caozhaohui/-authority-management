package org.lanqiao.pojo;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
  private Long id;

  private String name;

  private String password;

  private String email;

  private String perms;

  private String mobile;

  private Byte status;

  private String createBy;

  private Date createTime;

  private String lastUpdateBy;

  private Date lastUpdateTime;

  private String role;

  private Byte delFlag;

  private String code;

  public User() {}

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getPerms() {
    return perms;
  }

  public void setPerms(String perms) {
    this.perms = perms;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name == null ? null : name.trim();
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password == null ? null : password.trim();
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email == null ? null : email.trim();
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile == null ? null : mobile.trim();
  }

  public Byte getStatus() {
    return status;
  }

  public void setStatus(Byte status) {
    this.status = status;
  }

  public String getCreateBy() {
    return createBy;
  }

  public void setCreateBy(String createBy) {
    this.createBy = createBy == null ? null : createBy.trim();
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
    this.lastUpdateBy = lastUpdateBy == null ? null : lastUpdateBy.trim();
  }

  public Date getLastUpdateTime() {
    return lastUpdateTime;
  }

  public void setLastUpdateTime(Date lastUpdateTime) {
    this.lastUpdateTime = lastUpdateTime;
  }

  public Byte getDelFlag() {
    return delFlag;
  }

  public void setDelFlag(Byte delFlag) {
    this.delFlag = delFlag;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code == null ? null : code.trim();
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", password='" + password + '\'' +
            ", email='" + email + '\'' +
            ", perms='" + perms + '\'' +
            ", mobile='" + mobile + '\'' +
            ", status=" + status +
            ", createBy='" + createBy + '\'' +
            ", createTime=" + createTime +
            ", lastUpdateBy='" + lastUpdateBy + '\'' +
            ", lastUpdateTime=" + lastUpdateTime +
            ", role='" + role + '\'' +
            ", delFlag=" + delFlag +
            ", code='" + code + '\'' +
            '}';
  }
}
