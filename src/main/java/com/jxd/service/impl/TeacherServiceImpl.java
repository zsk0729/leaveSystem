package com.jxd.service.impl;

import com.jxd.dao.ITeacherDao;
import com.jxd.model.LeaveMsg;
import com.jxd.model.Teacher;
import com.jxd.service.ITeacherService;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class TeacherServiceImpl implements ITeacherService {
    @Autowired
    ITeacherDao teacherDao;

    @Override
    public List<LeaveMsg> teacherGetAllLeaveMsg(String gradename) {
        return teacherDao.teacherGetAllLeaveMsg(gradename);
    }

    @Override
    public List<LeaveMsg> teacherGetAllLeaveMsgPage(String gradename, String studentname, String starttime, String endtime, int pageSize, int pageIndex) {
        return teacherDao.teacherGetAllLeaveMsgPage(gradename, studentname, starttime, endtime, pageSize, pageIndex);
    }

    @Override
    public boolean deleteTeacherLeaveMsgBatch(String[] leaveid) {
        return teacherDao.deleteTeacherLeaveMsgBatch(leaveid);
    }

    @Override
    public boolean deleteTeacherLeaveMsg(String leaveid) {
        return teacherDao.deleteTeacherLeaveMsg(leaveid);
    }

    @Override
    public LeaveMsg teacherViewLeaveMsg(String leaveid) {
        return teacherDao.teacherViewLeaveMsg(leaveid);
    }

    @Override
    public List<LeaveMsg> teacherGetWaitAgreeLeaveMsg(String gradename) {
        return teacherDao.teacherGetWaitAgreeLeaveMsg(gradename);
    }

    @Override
    public List<LeaveMsg> teacherGetWaitAgreeLeaveMsgPage(String gradename, String studentname, int pageSize, int pageIndex) {
        return teacherDao.teacherGetWaitAgreeLeaveMsgPage(gradename, studentname, pageSize, pageIndex);
    }

    @Override
    public boolean agreeLeaveMsgBatch(String[] leaveid,String teachername) {
        return teacherDao.agreeLeaveMsgBatch(leaveid,teachername);
    }

    @Override
    public boolean refuseLeaveMsgBatch(String[] leaveid,String teachername) {
        return teacherDao.refuseLeaveMsgBatch(leaveid,teachername);
    }

    @Override
    public boolean agreeLeaveMsg(String leaveid,String teachername) {
        return teacherDao.agreeLeaveMsg(leaveid,teachername);
    }

    @Override
    public boolean refuseLeaveMsg(String leaveid,String teachername) {
        return teacherDao.refuseLeaveMsg(leaveid,teachername);
    }

    @Override
    public List<LeaveMsg> getAlreadyAgreeLeaveMsg(String gradename) {
        return teacherDao.getAlreadyAgreeLeaveMsg(gradename);
    }

    @Override
    public List<LeaveMsg> getAlreadyAgreeLeaveMsgPage(String gradename, String studentname, int pageSize, int pageIndex) {
        return teacherDao.getAlreadyAgreeLeaveMsgPage(gradename, studentname, pageSize, pageIndex);
    }

    @Override
    public List<LeaveMsg> getRefuseLeaveMsg(String gradename) {
        return teacherDao.getRefuseLeaveMsg(gradename);
    }

    @Override
    public List<LeaveMsg> getRefuseLeaveMsgPage(String gradename, String studentname, int pageSize, int pageIndex) {
        return teacherDao.getRefuseLeaveMsgPage(gradename, studentname, pageSize, pageIndex);
    }

    /**
     * 学生个人请假率获取折线图数据集
     * @return
     */
    @Override
    public DefaultCategoryDataset classLeaveRate(int num,String gradename) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //得到每月请假人数，放入数组months中
        int[] months = new int[13];
        for (int i = 1; i <= 12; i++) {
            months[i] = (leaveStudentNumOfMonth((i + "-"),gradename).getLeaveStudentNumOfMonth());
        }

        Double nums = num + 0.0;
        BigDecimal sumStudent = new BigDecimal(nums.toString());
        BigDecimal a = new BigDecimal(100);

        //计算每月请假率，放入数组monthLeaveRate[]中
        BigDecimal[] monthLeaveRate = new BigDecimal[13];
        for (int i = 1; i <= 12; i++) {
            BigDecimal leaveNum = new BigDecimal(months[i]);
            monthLeaveRate[i] = leaveNum.divide(sumStudent,3,RoundingMode.HALF_UP).multiply(a);
        }
        for (int i = 1; i <= 12; i++) {
            dataset.addValue(monthLeaveRate[i],"每月请假率",i + "月");
        }
        return dataset;
    }

    @Override
    public Teacher leaveStudentNumOfMonth(String month,String gradename) {
        Teacher teacher =  teacherDao.leaveStudentNumOfMonth(month,gradename);
        int num =teacher.getLeaveStudentNumOfMonth();
        return teacher;
    }

    @Override
    public Teacher getSumStudent(String gradename) {
        Teacher teacher = teacherDao.getSumStudent(gradename);
        int num = teacher.getSumStudent();
        return teacher;
    }


}
