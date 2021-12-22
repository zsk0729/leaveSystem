package com.jxd.model;

/**
 * @Desecription TODO
 * @Auther ZhengShouke
 * @Date 2020/9/8 19:33
 */
public class Parent {
    private String parentid;//父母编号
    private String parentname;//父母姓名
    private String role;//父母角色
    private String studentname;//孩子姓名
    private String studentid;//学生编号
    private String sex;//性别
    private String phone;//联系电话

    public Parent() {
    }

    public Parent(String parentname, String role, String studentname, String studentid,
                  String sex, String phone) {
        this.parentname = parentname;
        this.role = role;
        this.studentname = studentname;
        this.studentid = studentid;
        this.sex = sex;
        this.phone = phone;
    }

    public Parent(String parentid, String parentname, String role, String studentname,
                  String studentid, String sex, String phone) {
        this.parentid = parentid;
        this.parentname = parentname;
        this.role = role;
        this.studentname = studentname;
        this.studentid = studentid;
        this.sex = sex;
        this.phone = phone;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }
}
