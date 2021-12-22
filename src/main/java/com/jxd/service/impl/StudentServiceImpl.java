package com.jxd.service.impl;

import com.jxd.dao.IStudentDao;
import com.jxd.model.LeaveMsg;
import com.jxd.model.Parent;
import com.jxd.service.IStudentService;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * @Desecription TODO
 * @Auther ZhengShouke
 * @Date 2020/9/12 8:34
 */
@Service
public class StudentServiceImpl implements IStudentService {
    @Autowired
    IStudentDao studentDao;

    @Override
    public List<LeaveMsg> studentGetPersonalAllLeaveMsg(String studentname, String starttime, String endtime) {
        return studentDao.studentGetPersonalAllLeaveMsg(studentname,starttime, endtime);
    }

    @Override
    public List<LeaveMsg> studentGetPersonalAllLeaveMsgPage(String studentname, String starttime, String endtime, int pageSize, int pageIndex) {
        return studentDao.studentGetPersonalAllLeaveMsgPage(studentname,starttime, endtime, pageSize, pageIndex);
    }

    @Override
    public boolean deletePersonalLeaveMsgBatch(String[] leaveid) {
        return studentDao.deletePersonalLeaveMsgBatch(leaveid);
    }

    @Override
    public boolean deletePersonalLeaveMsg(String leaveid) {
        return studentDao.deletePersonalLeaveMsg(leaveid);
    }

    @Override
    public LeaveMsg viewLeaveMsg(String leaveid) {
        return studentDao.viewLeaveMsg(leaveid);
    }

    @Override
    public boolean addLeave(LeaveMsg leaveMsg) {
        return studentDao.addLeave(leaveMsg);
    }

    @Override
    public List<LeaveMsg> studentGetPersonalWaitAgreeLeaveMsg(String studentname, String starttime, String endtime) {
        return studentDao.studentGetPersonalWaitAgreeLeaveMsg(studentname, starttime, endtime);
    }

    @Override
    public List<LeaveMsg> studentGetPersonalWaitAgreeLeaveMsgPage(String studentname, String starttime, String endtime, int pageSize, int pageIndex) {
        return studentDao.studentGetPersonalWaitAgreeLeaveMsgPage(studentname, starttime, endtime, pageSize, pageIndex);
    }

    @Override
    public boolean cancelLeaveMsgBatch(String[] leaveid) {
        return studentDao.cancelLeaveMsgBatch(leaveid);
    }

    @Override
    public boolean cancelLeaveMsg(String leaveid) {
        return studentDao.cancelLeaveMsg(leaveid);
    }

    @Override
    public List<LeaveMsg> studentGetPersonalAlreadyAgreeLeaveMsg(String studentname, String starttime, String endtime) {
        return studentDao.studentGetPersonalAlreadyAgreeLeaveMsg(studentname, starttime, endtime);
    }

    @Override
    public List<LeaveMsg> studentGetPersonalAlreadyAgreeLeaveMsgPage(String studentname, String starttime, String endtime, int pageSize, int pageIndex) {
        return studentDao.studentGetPersonalAlreadyAgreeLeaveMsgPage(studentname, starttime, endtime, pageSize, pageIndex);
    }

    @Override
    public List<LeaveMsg> studentGetPersonalRefuseLeaveMsg(String studentname, String starttime, String endtime) {
        return studentDao.studentGetPersonalRefuseLeaveMsg(studentname, starttime, endtime);
    }

    @Override
    public List<LeaveMsg> studentGetPersonalRefuseLeaveMsgPage(String studentname, String starttime, String endtime, int pageSize, int pageIndex) {
        return studentDao.studentGetPersonalRefuseLeaveMsgPage(studentname, starttime, endtime, pageSize, pageIndex);
    }

    // 学生个人请假率获取饼状图数据集
    public DefaultPieDataset monthLeaveRate() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        dataset.setValue("北京", 13);
        dataset.setValue("深圳", 6);
        dataset.setValue("上海", 2);
        return dataset;
    }



    @Override
    public List<Parent> studentParentMsg(String studentname) {
        return studentDao.studentParentMsg(studentname);
    }

    @Override
    public boolean addParent(Parent parent) {
        return studentDao.addParent(parent);
    }

    @Override
    public boolean deleteParent(String parentid) {
        return studentDao.deleteParent(parentid);
    }

}
