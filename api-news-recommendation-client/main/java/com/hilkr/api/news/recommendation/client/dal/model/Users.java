package com.hilkr.api.news.recommendation.client.dal.model;

import java.util.Date;

public class Users extends BaseModel{

    private Integer id;

    private String username;

    private String password;

    private String tel;

    private String email;

    // private String remark;
    //
    // private Integer createBy;
    //
    // private Date createTime;
    //
    // private Integer updateBy;
    //
    // private Date updateTime;
    //
    // private Integer delFlg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
    //
    // public String getRemark() {
    //     return remark;
    // }
    //
    // public void setRemark(String remark) {
    //     this.remark = remark == null ? null : remark.trim();
    // }
    //
    // public Integer getCreateBy() {
    //     return createBy;
    // }
    //
    // public void setCreateBy(Integer createBy) {
    //     this.createBy = createBy;
    // }
    //
    // public Date getCreateTime() {
    //     return createTime;
    // }
    //
    // public void setCreateTime(Date createTime) {
    //     this.createTime = createTime;
    // }
    //
    // public Integer getUpdateBy() {
    //     return updateBy;
    // }
    //
    // public void setUpdateBy(Integer updateBy) {
    //     this.updateBy = updateBy;
    // }
    //
    // public Date getUpdateTime() {
    //     return updateTime;
    // }
    //
    // public void setUpdateTime(Date updateTime) {
    //     this.updateTime = updateTime;
    // }
    //
    // public Integer getDelFlg() {
    //     return delFlg;
    // }
    //
    // public void setDelFlg(Integer delFlg) {
    //     this.delFlg = delFlg;
    // }
}