package com.jxd.model;

/**
 * @Desecription TODO
 * @Auther ZhengShouke
 * @Date 2020/9/8 19:20
 */
public class Teacher {
    private String teacherid;//教师编号
    private String teachername;//教师姓名
    private String password;//密码
    private String gradename;//所属班级
    private String sex;//性别
    private String birthday;//出生日期
    private String degree;//学历
    private String inschooltime;//入职时间
    private int leaveStudentNumOfMonth;//每月请假人数
    private int sumStudent;//班级总人数

    public Teacher() {
    }

    public Teacher(String teachername, String password, String gradename, String sex, String birthday,
                   String degree,String inschooltime,int leaveStudentNumOfMonth, int sumStudent) {
        this.teachername = teachername;
        this.password = password;
        this.gradename = gradename;
        this.sex = sex;
        this.birthday = birthday;
        this.degree = degree;
        this.inschooltime = inschooltime;
        this.leaveStudentNumOfMonth = leaveStudentNumOfMonth;
        this.sumStudent = sumStudent;
    }

    public Teacher(String teacherid, String teachername, String password, String gradename, String sex,
                   String birthday, String degree,String inschooltime,int leaveStudentNumOfMonth,
                   int sumStudent) {
        this.teacherid = teacherid;
        this.teachername = teachername;
        this.password = password;
        this.gradename = gradename;
        this.sex = sex;
        this.birthday = birthday;
        this.degree = degree;
        this.inschooltime = inschooltime;
        this.leaveStudentNumOfMonth = leaveStudentNumOfMonth;
        this.sumStudent = sumStudent;
    }

    public String getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getInschooltime() {
        return inschooltime;
    }

    public void setInschooltime(String inschooltime) {
        this.inschooltime = inschooltime;
    }

    public int getLeaveStudentNumOfMonth() {
        return leaveStudentNumOfMonth;
    }

    public void setLeaveStudentNumOfMonth(int leaveStudentNumOfMonth) {
        this.leaveStudentNumOfMonth = leaveStudentNumOfMonth;
    }

    public int getSumStudent() {
        return sumStudent;
    }

    public void setSumStudent(int sumStudent) {
        this.sumStudent = sumStudent;
    }
}
