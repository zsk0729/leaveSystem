package com.jxd.dao;

import com.jxd.model.LeaveMsg;
import com.jxd.model.Parent;
import org.apache.ibatis.annotations.Param;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.List;

public interface IStudentDao {

    /**
     * 得到学生个人所有请假信息
     * @return
     */
    List<LeaveMsg> studentGetPersonalAllLeaveMsg(@Param("studentname") String studentname, @Param("starttime") String starttime,
                                                 @Param("endtime") String endtime);

    /**
     * 分页获取学生个人所有请假信息
     * @param starttime
     * @param endtime
     * @param pageSize
     * @param pageIndex
     * @return
     */
    List<LeaveMsg> studentGetPersonalAllLeaveMsgPage(@Param("studentname") String studentname, @Param("starttime") String starttime, @Param("endtime") String endtime,
                                                     @Param("pageSize") int pageSize, @Param("pageIndex") int pageIndex);

    /**
     * 批量删除学生个人请假信息
     * @param leaveid
     * @return
     */
    boolean deletePersonalLeaveMsgBatch(String[] leaveid);

    /**
     * 删除单条学生个人请假信息
     * @param leaveid
     * @return
     */
    boolean deletePersonalLeaveMsg(String leaveid);

    /**
     * 查看详细请假信息
     * @param leaveid
     * @return
     */
    LeaveMsg viewLeaveMsg(String leaveid);

    /**
     * 学生添加申请请假
     * @param leaveMsg
     * @return
     */
    boolean addLeave(LeaveMsg leaveMsg);

    /**
     * 获取该生等待批准的请假信息列表
     * @param studentname
     * @param starttime
     * @param endtime
     * @return
     */
    List<LeaveMsg> studentGetPersonalWaitAgreeLeaveMsg(@Param("studentname") String studentname, @Param("starttime") String starttime,
                                                       @Param("endtime") String endtime);

    /**
     * 分页获取该生等待批准的请假信息列表
     * @param studentname
     * @param starttime
     * @param endtime
     * @param pageSize
     * @param pageIndex
     * @return
     */
    List<LeaveMsg> studentGetPersonalWaitAgreeLeaveMsgPage(@Param("studentname") String studentname, @Param("starttime") String starttime, @Param("endtime") String endtime,
                                                           @Param("pageSize") int pageSize, @Param("pageIndex") int pageIndex);

    /**
     * 批量取消请假
     * @param leaveid
     * @return
     */
    boolean cancelLeaveMsgBatch(String[] leaveid);

    /**
     * 取消请假
     * @param leaveid
     * @return
     */
    boolean cancelLeaveMsg(String leaveid);

    /**
     * 得到该生所有已经同意的请假申请
     * @return
     */
    List<LeaveMsg> studentGetPersonalAlreadyAgreeLeaveMsg(@Param("studentname") String studentname, @Param("starttime") String starttime, @Param("endtime") String endtime);

    /**
     * 分页获取该生所有已经同意的请假申请
     * @param starttime
     * @param endtime
     * @param pageSize
     * @param pageIndex
     * @return
     */
    List<LeaveMsg> studentGetPersonalAlreadyAgreeLeaveMsgPage(@Param("studentname") String studentname, @Param("starttime") String starttime, @Param("endtime") String endtime,
                                                              @Param("pageSize") int pageSize, @Param("pageIndex") int pageIndex);

    /**
     * 得到该生所有被拒绝的请假申请
     * @return
     */
    List<LeaveMsg> studentGetPersonalRefuseLeaveMsg(@Param("studentname") String studentname, @Param("starttime") String starttime, @Param("endtime") String endtime);

    /**
     * 分页获取该生所有被拒绝的请假申请
     * @param starttime
     * @param endtime
     * @param pageSize
     * @param pageIndex
     * @return
     */
    List<LeaveMsg> studentGetPersonalRefuseLeaveMsgPage(@Param("studentname") String studentname, @Param("starttime") String starttime, @Param("endtime") String endtime,
                                                        @Param("pageSize") int pageSize, @Param("pageIndex") int pageIndex);

    /**
     * 学生个人请假率折线图
     * @return
     */
    DefaultCategoryDataset monthLeaveRate();

    /**
     * 获取家长列表
     * @param studentname
     * @return
     */
    List<Parent> studentParentMsg(String studentname);

    /**
     * 添加家长信息
     * @param parent
     * @return
     */
    boolean addParent(Parent parent);

    /**
     * 删除家长信息
     * @param parentid
     * @return
     */
    boolean deleteParent(String parentid);



}
