package com.jxd.model;

/**
 * @Desecription TODO
 * @Auther ZhengShouke
 * @Date 2020/9/8 19:31
 */
public class Student {

    private String studentid;//学生编号
    private String studentname;//学生姓名
    private String password;//密码
    private String gradename;//所属班级
    private String birthday;//出生日期
    private String sex;//性别
    private String inschooltime;//入学时间

    public Student() {
    }

    public Student(String studentname, String password, String gradename, String birthday, String inschooltime,
                   String sex) {
        this.studentname = studentname;
        this.password = password;
        this.gradename = gradename;
        this.birthday = birthday;
        this.sex = sex;
        this.inschooltime = inschooltime;
    }

    public Student(String studentid, String studentname, String password, String gradename, String birthday,
                   String sex, String inschooltime) {
        this.studentid = studentid;
        this.studentname = studentname;
        this.password = password;
        this.gradename = gradename;
        this.birthday = birthday;
        this.sex = sex;
        this.inschooltime = inschooltime;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGradename() {
        return gradename;
    }

    public void setGradename(String gradename) {
        this.gradename = gradename;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getInschooltime() {
        return inschooltime;
    }

    public void setInschooltime(String inschooltime) {
        this.inschooltime = inschooltime;
    }
}
