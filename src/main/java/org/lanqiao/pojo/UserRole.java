package org.lanqiao.pojo;

import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class UserRole {

  private String urId;

  public String getUrId() {
    return urId;
  }

  public void setUrId(String urId) {
    this.urId = urId;
  }
  private Long id;

  private Long userId;

  private Long setroleId;

  public Long getSetroleId() {
    return setroleId;
  }

  public void setSetroleId(Long setroleId) {
    this.setroleId = setroleId;
  }

  private Long roleId;

  private String createBy;

  private Date createTime;

  private String lastUpdateBy;

  private Date lastUpdateTime;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
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
}
