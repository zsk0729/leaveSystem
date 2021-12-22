package com.jxd.model;

/**
 * @Desecription TODO
 * @Auther ZhengShouke
 * @Date 2020/9/8 19:36
 */
public class Admin {

    private String adminid;//管理员编号
    private String adminname;//管理员姓名
    private String password;//密码
    private String sumNumStudentOfClass;//每个班级总人数
    private String leaveNumClassMonth;//每个班级每月请假人数
    private String sumStudent;//学生总人数
    private String leaveSumMonth;//每个月请假总人数

    public Admin() {
    }

    public Admin(String adminname, String password, String sumNumStudentOfClass,
                 String leaveNumClassMonth, String sumStudent, String leaveSumMonth) {
        this.adminname = adminname;
        this.password = password;
        this.sumNumStudentOfClass = sumNumStudentOfClass;
        this.leaveNumClassMonth = leaveNumClassMonth;
        this.sumStudent = sumStudent;
        this.leaveSumMonth = leaveSumMonth;
    }

    public Admin(String adminid, String adminname, String password, String sumNumStudentOfClass,
                 String leaveNumClassMonth, String sumStudent, String leaveSumMonth) {
        this.adminid = adminid;
        this.adminname = adminname;
        this.password = password;
        this.sumNumStudentOfClass = sumNumStudentOfClass;
        this.leaveNumClassMonth = leaveNumClassMonth;
        this.sumStudent = sumStudent;
        this.leaveSumMonth = leaveSumMonth;
    }

    public String getAdminid() {
        return adminid;
    }

    public void setAdminid(String adminid) {
        this.adminid = adminid;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSumNumStudentOfClass() {
        return sumNumStudentOfClass;
    }

    public void setSumNumStudentOfClass(String sumNumStudentOfClass) {
        this.sumNumStudentOfClass = sumNumStudentOfClass;
    }

    public String getLeaveNumClassMonth() {
        return leaveNumClassMonth;
    }

    public void setLeaveNumClassMonth(String leaveNumClassMonth) {
        this.leaveNumClassMonth = leaveNumClassMonth;
    }

    public String getSumStudent() {
        return sumStudent;
    }

    public void setSumStudent(String sumStudent) {
        this.sumStudent = sumStudent;
    }

    public String getLeaveSumMonth() {
        return leaveSumMonth;
    }

    public void setLeaveSumMonth(String leaveSumMonth) {
        this.leaveSumMonth = leaveSumMonth;
    }
}
