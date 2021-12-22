package com.jxd.model;

/**
 * @Desecription TODO
 * @Auther ZhengShouke
 * @Date 2020/9/8 19:37
 */
public class LeaveMsg {
    private String leaveid;//请假信息编号
    private String starttime;//开始时间
    private String endtime;//结束时间
    private String studentname;//请假学生姓名
    private String gradename;//请假学生所属班级名称
    private String teachername;//审核教师
    private String parentname;//请假学生申请父母姓名
    private String reason;//请假原因
    private String state;//批准状态
    private String teacherdel;//教师删除状态
    private String studentdal;//学生删除状态

    public LeaveMsg() {
    }

    public LeaveMsg(String starttime, String endtime, String studentname, String gradename, String teachername,
                    String parentname, String reason, String state, String teacherdel, String studentdal) {
        this.starttime = starttime;
        this.endtime = endtime;
        this.studentname = studentname;
        this.gradename = gradename;
        this.teachername = teachername;
        this.parentname = parentname;
        this.reason = reason;
        this.state = state;
        this.teacherdel = teacherdel;
        this.studentdal = studentdal;
    }

    public LeaveMsg(String leaveid, String starttime, String endtime, String studentname, String gradename,
                    String teachername, String parentname, String reason, String state, String teacherdel,
                    String studentdal) {
        this.leaveid = leaveid;
        this.starttime = starttime;
        this.endtime = endtime;
        this.studentname = studentname;
        this.gradename = gradename;
        this.teachername = teachername;
        this.parentname = parentname;
        this.reason = reason;
        this.state = state;
        this.teacherdel = teacherdel;
        this.studentdal = studentdal;
    }

    public String getLeaveid() {
        return leaveid;
    }

    public void setLeaveid(String leaveid) {
        this.leaveid = leaveid;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getGradename() {
        return gradename;
    }

    public void setGradename(String gradename) {
        this.gradename = gradename;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTeacherdel() {
        return teacherdel;
    }

    public void setTeacherdel(String teacherdel) {
        this.teacherdel = teacherdel;
    }

    public String getStudentdal() {
        return studentdal;
    }

    public void setStudentdal(String studentdal) {
        this.studentdal = studentdal;
    }
}
