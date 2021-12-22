package com.jxd.model;

/**
 * @Desecription TODO
 * @Auther ZhengShouke
 * @Date 2020/9/9 8:39
 */

public class UserLogin {

    public String userid;//用户编号
    public String uname;//用户名
    public String pwd;//密码
    public String role;//用户角色
    public String uploadfile;//头像

    public UserLogin() {
    }

    public UserLogin(String uname, String pwd, String role, String uploadfile) {
        this.uname = uname;
        this.pwd = pwd;
        this.role = role;
        this.uploadfile = uploadfile;
    }

    public UserLogin(String userid, String uname, String pwd, String role, String uploadfile) {
        this.userid = userid;
        this.uname = uname;
        this.pwd = pwd;
        this.role = role;
        this.uploadfile = uploadfile;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUploadfile() {
        return uploadfile;
    }

    public void setUploadfile(String uploadfile) {
        this.uploadfile = uploadfile;
    }
}
