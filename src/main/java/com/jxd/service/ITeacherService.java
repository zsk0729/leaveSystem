package com.jxd.service;

import com.jxd.model.LeaveMsg;
import com.jxd.model.Teacher;
import com.jxd.model.UserLogin;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.List;


/**
 * @Desecription TODO
 * @Auther ZhengShouke
 * @Date 2020/9/8 19:22
 */
public interface ITeacherService {

    /**
     * 获取该教师所在班级的请假信息
     * @param gradename 班级名称
     * @return
     */
    List<LeaveMsg> teacherGetAllLeaveMsg(String gradename);

    /**
     * 分页获取该教师所在班级的请假信息
     * @param gradename 班级名称
     * @param studentname 学生姓名
     * @param starttime 请假开始时间
     * @param endtime 请假结束时间
     * @param pageSize 获取几条数据
     * @param pageIndex 跳过几条数据
     * @return
     */
    List<LeaveMsg> teacherGetAllLeaveMsgPage(String gradename,String studentname,
                                             String starttime,String endtime,int pageSize, int pageIndex);

    /**
     * 教师端批量删除请假信息
     * @param leaveid 请假信息id
     * @return
     */
    boolean deleteTeacherLeaveMsgBatch(String[] leaveid);

    /**
     * 教师端删除单条请假信息
     * @param leaveid 请假信息id
     * @return
     */
    boolean deleteTeacherLeaveMsg(String leaveid);

    /**
     * 教师端获取详细请假信息
     * @param leaveid 请假信息id
     * @return
     */
    LeaveMsg teacherViewLeaveMsg(String leaveid);

    /**
     * 教师端获取等待审核的请假信息
     * @param gradename 班级名称
     * @return
     */
    List<LeaveMsg> teacherGetWaitAgreeLeaveMsg(String gradename);

    /**
     * 教师端分页获取等待审核的请假信息
     * @param gradename 班级名称
     * @param studentname 学生姓名
     * @param pageSize 获取几条数据
     * @param pageIndex 跳过几条数据
     * @return
     */
    List<LeaveMsg> teacherGetWaitAgreeLeaveMsgPage(String gradename,String studentname,int pageSize, int pageIndex);

    /**
     * 批量同意请假申请
     * @param leaveid 请假信息id
     * @param teachername 教师姓名
     * @return
     */
    boolean agreeLeaveMsgBatch(String[] leaveid,String teachername);

    /**
     * 批量拒绝请假申请
     * @param leaveid 请假信息id
     * @param teachername 教师姓名
     * @return
     */
    boolean refuseLeaveMsgBatch(String[] leaveid,String teachername);

    /**
     * 同意请假申请
     * @param leaveid 请假信息id
     * @param teachername 教师姓名
     * @return
     */
    boolean agreeLeaveMsg(String leaveid,String teachername);

    /**
     * 拒绝请假申请
     * @param leaveid 请假信息id
     * @param teachername 教师姓名
     * @return
     */
    boolean refuseLeaveMsg(String leaveid,String teachername);

    /**
     * 教师端获取所有已经同意的请假信息
     * @param gradename 班级名称
     * @return
     */
    List<LeaveMsg> getAlreadyAgreeLeaveMsg(String gradename);

    /**
     * 教师端分页获取所有已经同意的请假信息
     * @param gradename 班级名称
     * @param studentname 学生姓名
     * @param pageSize 获取几条数据
     * @param pageIndex 跳过几条数据
     * @return
     */
    List<LeaveMsg> getAlreadyAgreeLeaveMsgPage(String gradename,String studentname,int pageSize, int pageIndex);

    /**
     * 教师端获取所有已经拒绝的请假信息
     * @param gradename 班级名称
     * @return
     */
    List<LeaveMsg> getRefuseLeaveMsg(String gradename);

    /**
     * 教师端分页获取所有已经拒绝的请假信息
     * @param gradename 班级名称
     * @param studentname 学生姓名
     * @param pageSize 获取几条数据
     * @param pageIndex 跳过几条数据
     * @return
     */
    List<LeaveMsg> getRefuseLeaveMsgPage(String gradename,String studentname,int pageSize, int pageIndex);

    /**
     * 教师端获取班级请假率
     * @param num 班级人数
     * @param gradename 班级名称
     * @return
     */
    DefaultCategoryDataset classLeaveRate(int num,String gradename);

    /**
     * 教师端获取每月班内请假人数
     * @param month 月份
     * @param gradename 班级名称
     * @return
     */
    Teacher leaveStudentNumOfMonth(String month,String gradename);

    /**
     * 获取班级总人数
     * @param gradename 班级名称
     * @return
     */
    Teacher getSumStudent(String gradename);

}
