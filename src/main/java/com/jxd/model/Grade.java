package com.jxd.model;

/**
 * @Desecription TODO
 * @Auther ZhengShouke
 * @Date 2020/9/8 19:35
 */
public class Grade {

    private String gradeid;//班级编号
    private String gradename;//班级名称
    private String gradeHaveStudent;//班级内学生数量
    private String gradeHaveTeacher;//班级内教师数量

    public Grade() {
    }

    public Grade(String gradename, String gradeHaveStudent, String gradeHaveTeacher) {
        this.gradename = gradename;
        this.gradeHaveStudent = gradeHaveStudent;
        this.gradeHaveTeacher = gradeHaveTeacher;
    }

    public Grade(String gradeid, String gradename, String gradeHaveStudent, String gradeHaveTeacher) {
        this.gradeid = gradeid;
        this.gradename = gradename;
        this.gradeHaveStudent = gradeHaveStudent;
        this.gradeHaveTeacher = gradeHaveTeacher;
    }

    public String getGradeid() {
        return gradeid;
    }

    public void setGradeid(String gradeid) {
        this.gradeid = gradeid;
    }

    public String getGradename() {
        return gradename;
    }

    public void setGradename(String gradename) {
        this.gradename = gradename;
    }

    public String getGradeHaveStudent() {
        return gradeHaveStudent;
    }

    public void setGradeHaveStudent(String gradeHaveStudent) {
        this.gradeHaveStudent = gradeHaveStudent;
    }

    public String getGradeHaveTeacher() {
        return gradeHaveTeacher;
    }

    public void setGradeHaveTeacher(String gradeHaveTeacher) {
        this.gradeHaveTeacher = gradeHaveTeacher;
    }
}
