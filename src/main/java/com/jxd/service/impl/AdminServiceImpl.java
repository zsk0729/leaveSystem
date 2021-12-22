package com.jxd.service.impl;

import com.jxd.dao.IAdminDao;
import com.jxd.model.*;
import com.jxd.service.IAdminService;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * @Desecription TODO
 * @Auther ZhengShouke
 * @Date 2020/9/9 14:25
 */
@Service
public class AdminServiceImpl implements IAdminService {
    @Autowired
    IAdminDao adminDao;

    @Override
    public List<Grade> getAllGrade(String gradename) {
        return adminDao.getAllGrade(gradename);
    }

    @Override
    public List<Grade> getAllGradePage(int pageSize, int pageIndex, String gradename) {
        return adminDao.getAllGradePage(pageSize, pageIndex, gradename);
    }

    @Override
    public List<Teacher> getAllTeacher(String teachername) {
        return adminDao.getAllTeacher(teachername);
    }

    @Override
    public List<Teacher> getAllTeacherUnallocat() {
        return adminDao.getAllTeacherUnallocat();
    }

    @Override
    public List<Teacher> getAllTeacherPage(int pageSize, int pageIndex, String teachername) {
        return adminDao.getAllTeacherPage(pageSize, pageIndex, teachername);
    }

    @Override
    public List<Grade> gradeNameAjax(String gradename) {
        return adminDao.gradeNameAjax(gradename);
    }

    @Override
    public boolean addGrade(String gradename) {
        return adminDao.addGrade(gradename);
    }

    @Override
    public boolean setTeacherOfGrade(String gradename,String teacherid) {
        return adminDao.setTeacherOfGrade(gradename,teacherid);
    }

    @Override
    public boolean delGradeBatch(String[] gradeids) {
        return adminDao.delGradeBatch(gradeids);
    }

    @Override
    public boolean delGrade(String gradeid) {
        return adminDao.delGrade(gradeid);
    }

    @Override
    public List<Teacher> getTeacherNumberByGradename(String gradename) {
        return adminDao.getTeacherNumberByGradename(gradename);
    }

    @Override
    public List<Student> getAllStudent(String gradename, String studentname) {
        return adminDao.getAllStudent(gradename, studentname);
    }

    @Override
    public List<Student> getAllStudentPage(int pageSize, int pageIndex, String gradename, String studentname) {
        return adminDao.getAllStudentPage(pageSize, pageIndex, gradename, studentname);
    }

    @Override
    public boolean addStudent(Student student) {
        return adminDao.addStudent(student);
    }

    @Override
    public boolean addUserStudent(String studentname) {
        return adminDao.addUserStudent(studentname);
    }

    @Override
    public boolean deleteStudentBatch(String[] studentid) {
        return adminDao.deleteStudentBatch(studentid);
    }

    @Override
    public boolean deleteParentBatch(String[] studentid) {
        return adminDao.deleteParentBatch(studentid);
    }

    @Override
    public boolean deleteStudent(String studentid) {
        return adminDao.deleteStudent(studentid);
    }

    @Override
    public boolean deleteParent(String studentid) {
        return adminDao.deleteParent(studentid);
    }

    @Override
    public boolean updateStudent(Student student) {
        return adminDao.updateStudent(student);
    }

    @Override
    public boolean addTeacher(Teacher teacher) {
        return adminDao.addTeacher(teacher);
    }

    @Override
    public boolean addUserTeacher(Teacher teacher) {
        return adminDao.addUserTeacher(teacher);
    }

    @Override
    public boolean deleteTeacherBatch(String[] teacherid) {
        return adminDao.deleteTeacherBatch(teacherid);
    }

    @Override
    public boolean deleteTeacher(String teacherid) {
        return adminDao.deleteTeacher(teacherid);
    }

    @Override
    public boolean updateTeacher(Teacher teacher) {
        return adminDao.updateTeacher(teacher);
    }

    @Override
    public List<UserLogin> getAllUser(String uname, String userid) {
        return adminDao.getAllUser(uname, userid);
    }

    @Override
    public List<UserLogin> getAllUserPage(String uname, String userid, int pageSize, int pageIndex) {
        return adminDao.getAllUserPage(uname, userid, pageSize, pageIndex);
    }

    @Override
    public boolean editUser(String userid){
        return adminDao.editUser(userid);
    }

    /**
     * 获取每个班级每月请假率
     * @return
     */
    @Override
    public TimeSeries classMonthLeaveRate(int num,String gradename) {
        TimeSeries timeSeries = new TimeSeries(gradename+"每月学生请假率", Month.class);

       //得到每月请假人数，放入数组months中
        int[] months = new int[13];
        for (int i = 1; i <= 12; i++) {
            months[i] = Integer.parseInt((leaveStudentNumOfMonth((i + "-"),gradename).getLeaveNumClassMonth()));
        }
        //muns班级内总人数
        Double nums = num + 0.0;
        BigDecimal sumStudent = new BigDecimal(nums.toString());
        BigDecimal a = new BigDecimal(100);

        //计算每月请假率，放入数组monthLeaveRate[]中
        BigDecimal[] monthLeaveRate = new BigDecimal[13];
        for (int i = 1; i <= 12; i++) {
            BigDecimal leaveNum = new BigDecimal(months[i]);
            monthLeaveRate[i] = leaveNum.divide(sumStudent,3, RoundingMode.HALF_UP).multiply(a);
            //将数据monthLeaveRate[i]加入timeSeries中
            timeSeries.add(new Month(i,2019),monthLeaveRate[i]);
        }

        return timeSeries;
    }

    /**
     * 管理员获取每月每个班内请假人数
     * @param month
     * @return
     */
    @Override
    public Admin leaveStudentNumOfMonth(String month, String gradename) {
        return adminDao.leaveStudentNumOfMonth(month, gradename);
    }

    /**
     * 获取班级总人数
     * @param gradename
     * @return
     */
    @Override
    public Admin getSumStudentClass(String gradename) {
        return adminDao.getSumStudentClass(gradename);
    }

    /**
     * 获取学生总数
     * @return
     */
    @Override
    public Admin getSumAllStudent() {
        return adminDao.getSumAllStudent();
    }

    /**
     * 获取每个月请假的总人数
     * @param month
     * @return
     */
    @Override
    public Admin getSumLeaveMonth(String month) {
        return adminDao.getSumLeaveMonth(month);
    }

    @Override
    public Grade getGradenameByGradeid(String gradeid) {
        return adminDao.getGradenameByGradeid(gradeid);
    }


}
