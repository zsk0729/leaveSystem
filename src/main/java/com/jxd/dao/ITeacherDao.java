package com.jxd.dao;

import com.jxd.model.LeaveMsg;
import com.jxd.model.Teacher;
import org.apache.ibatis.annotations.Param;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.List;

/**
 * @Desecription TODO
 * @Auther ZhengShouke
 * @Date 2020/9/8 19:21
 */
public interface ITeacherDao {

    /**
     * 获取该教师所在班级的请假信息
     * @param gradename
     * @return
     */
    List<LeaveMsg> teacherGetAllLeaveMsg(String gradename);

    /**
     * 分页获取该教师所在班级的请假信息
     * @param gradename
     * @param studentname
     * @param starttime
     * @param endtime
     * @param pageSize
     * @param pageIndex
     * @return
     */
    List<LeaveMsg> teacherGetAllLeaveMsgPage(@Param("gradename") String gradename,@Param("studentname") String studentname,
                                             @Param("starttime") String starttime,@Param("endtime") String endtime,
                                             @Param("pageSize") int pageSize,@Param("pageIndex") int pageIndex);

    /**
     * 教师端批量删除请假信息
     * @param leaveid
     * @return
     */
    boolean deleteTeacherLeaveMsgBatch(String[] leaveid);

    /**
     * 教师端删除单条请假信息
     * @param leaveid
     * @return
     */
    boolean deleteTeacherLeaveMsg(String leaveid);

    /**
     * 教师端获取详细请假信息
     * @param leaveid
     * @return
     */
    LeaveMsg teacherViewLeaveMsg(String leaveid);

    /**
     * 教师端获取等待审核的请假信息
     * @param gradename
     * @return
     */
    List<LeaveMsg> teacherGetWaitAgreeLeaveMsg(String gradename);

    /**
     * 教师端分页获取等待审核的请假信息
     * @param gradename
     * @param studentname
     * @param pageSize
     * @param pageIndex
     * @return
     */
    List<LeaveMsg> teacherGetWaitAgreeLeaveMsgPage(@Param("gradename") String gradename,@Param("studentname") String studentname,
                                                   @Param("pageSize") int pageSize,@Param("pageIndex") int pageIndex);

    /**
     * 批量同意请假申请
     * @param leaveid
     * @return
     */
    boolean agreeLeaveMsgBatch(@Param("leaveid") String[] leaveid,@Param("teachername") String teachername);

    /**
     * 批量拒绝请假申请
     * @param leaveid
     * @return
     */
    boolean refuseLeaveMsgBatch(@Param("leaveid") String[] leaveid,@Param("teachername") String teachername);

    /**
     * 同意请假申请
     * @param leaveid
     * @return
     */
    boolean agreeLeaveMsg(@Param("leaveid") String leaveid,@Param("teachername") String teachername);

    /**
     * 拒绝请假申请
     * @param leaveid
     * @return
     */
    boolean refuseLeaveMsg(@Param("leaveid") String leaveid,@Param("teachername") String teachername);

    /**
     * 教师端获取所有已经同意的请假信息
     * @param gradename
     * @return
     */
    List<LeaveMsg> getAlreadyAgreeLeaveMsg(String gradename);

    /**
     * 教师端分页获取所有已经同意的请假信息
     * @param gradename
     * @return
     */
    List<LeaveMsg> getAlreadyAgreeLeaveMsgPage(@Param("gradename") String gradename,@Param("studentname") String studentname,
                                               @Param("pageSize") int pageSize,@Param("pageIndex") int pageIndex);

    /**
     * 教师端获取所有已经拒绝的请假信息
     * @param gradename
     * @return
     */
    List<LeaveMsg> getRefuseLeaveMsg(String gradename);

    /**
     * 教师端分页获取所有已经拒绝的请假信息
     * @param gradename
     * @return
     */
    List<LeaveMsg> getRefuseLeaveMsgPage(@Param("gradename") String gradename,@Param("studentname") String studentname,
                                         @Param("pageSize") int pageSize,@Param("pageIndex") int pageIndex);

    /**
     * 教师端获取班级请假率
     * @return
     */
    DefaultCategoryDataset classLeaveRate(String gradename);

    /**
     * 教师端获取每月班内请假人数
     * @param month
     * @return
     */
    Teacher leaveStudentNumOfMonth(@Param("month") String month,@Param("gradename") String gradename);

    /**
     * 获取班级总人数
     * @param gradename
     * @return
     */
    Teacher getSumStudent(String gradename);

}
