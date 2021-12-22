package com.jxd.service;

import com.jxd.model.LeaveMsg;
import com.jxd.model.Parent;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.util.List;

public interface IStudentService {

    /**
     * 得到学生个人所有请假信息
     * @return
     */
    List<LeaveMsg> studentGetPersonalAllLeaveMsg(String studentname, String starttime, String endtime);

    /**
     * 分页获取学生个人所有请假信息
     * @param starttime 请假开始时间
     * @param endtime 请假结束时间
     * @param pageSize 获取几条数据
     * @param pageIndex 跳过几条数据
     * @return
     */
    List<LeaveMsg> studentGetPersonalAllLeaveMsgPage(String studentname, String starttime, String endtime, int pageSize, int pageIndex);

    /**
     * 批量删除学生个人请假信息
     * @param leaveid 请假信息id
     * @return
     */
    boolean deletePersonalLeaveMsgBatch(String[] leaveid);

    /**
     * 删除单条学生个人请假信息
     * @param leaveid 请假信息id
     * @return
     */
    boolean deletePersonalLeaveMsg(String leaveid);

    /**
     * 查看详细请假信息
     * @param leaveid 请假信息id
     * @return
     */
    LeaveMsg viewLeaveMsg(String leaveid);

    /**
     * 学生添加申请请假
     * @param leaveMsg 请假实体类
     * @return
     */
    boolean addLeave(LeaveMsg leaveMsg);

    /**
     * 获取该生等待批准的请假信息列表
     * @param studentname 学生姓名
     * @param starttime 请假开始时间
     * @param endtime 请假结束时间
     * @return
     */
    List<LeaveMsg> studentGetPersonalWaitAgreeLeaveMsg(String studentname, String starttime, String endtime);

    /**
     * 分页获取该生等待批准的请假信息列表
     * @param studentname 请假学生姓名
     * @param starttime 请假开始时间
     * @param endtime 请假结束时间
     * @param pageSize 获取几条数据
     * @param pageIndex 跳过几条数据
     * @return
     */
    List<LeaveMsg> studentGetPersonalWaitAgreeLeaveMsgPage(String studentname, String starttime, String endtime, int pageSize, int pageIndex);

    /**
     * 批量取消请假
     * @param leaveid 请假信息id
     * @return
     */
    boolean cancelLeaveMsgBatch(String[] leaveid);

    /**
     * 取消请假
     * @param leaveid 请假信息id
     * @return
     */
    boolean cancelLeaveMsg(String leaveid);

    /**
     * 得到该生所有已经同意的请假申请
     * @param studentname 请假学生姓名
     * @param starttime 开始时间
     * @param endtime 结束时间
     * @return
     */
    List<LeaveMsg> studentGetPersonalAlreadyAgreeLeaveMsg(String studentname, String starttime, String endtime);

    /**
     * 分页获取该生所有已经同意的请假申请
     * @param starttime 开始时间
     * @param endtime 结束时间
     * @param pageSize 获取几条数据
     * @param pageIndex 跳过几条数据
     * @return
     */
    List<LeaveMsg> studentGetPersonalAlreadyAgreeLeaveMsgPage(String studentname, String starttime, String endtime, int pageSize, int pageIndex);

    /**
     * 得到该生所有被拒绝的请假申请
     * @param studentname 学生姓名
     * @param starttime 开始时间
     * @param endtime 结束时间
     * @return
     */
    List<LeaveMsg> studentGetPersonalRefuseLeaveMsg(String studentname, String starttime, String endtime);

    /**
     * 分页获取该生所有被拒绝的请假申请
     * @param starttime 开始时间
     * @param endtime 结束时间
     * @param pageSize 获取几条数据
     * @param pageIndex 跳过几条数据
     * @return
     */
    List<LeaveMsg> studentGetPersonalRefuseLeaveMsgPage(String studentname, String starttime, String endtime, int pageSize, int pageIndex);

    /**
     * 学生个人请假率饼状图
     * @return
     */
    DefaultPieDataset monthLeaveRate();

    /**
     * 获取家长列表
     * @param studentname 学生姓名
     * @return
     */
    List<Parent> studentParentMsg(String studentname);

    /**
     * 添加家长信息
     * @param parent 家长实体类
     * @return
     */
    boolean addParent(Parent parent);

    /**
     * 删除家长信息
     * @param parentid 家长id
     * @return
     */
    boolean deleteParent(String parentid);
}
