package com.jxd.service.impl;

import com.jxd.dao.ILeaveSysDao;
import com.jxd.model.*;
import com.jxd.service.ILeaveSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Desecription TODO
 * @Auther ZhengShouke
 * @Date 2020/9/9 8:41
 */
@Service
public class LeaveSysServiceImpl implements ILeaveSysService {
    @Autowired
    ILeaveSysDao leaveSysDao;

    @Override
    public UserLogin isLogin(UserLogin userLogin) {
        return leaveSysDao.isLogin(userLogin);
    }

    @Override
    public UserLogin isLogin(String userid, String pwd, String role) {
        return leaveSysDao.isLogin2(userid, pwd, role);
    }

    @Override
    public List<Grade> getAllGrade() {
        return leaveSysDao.getAllGrade();
    }

    @Override
    public List<Parent> getAllParent(String studentname) {
        return leaveSysDao.getAllParent(studentname);
    }

    @Override
    public List<Student> leaveStudentOfGrade(String studentname) {
        return leaveSysDao.leaveStudentOfGrade(studentname);
    }

    @Override
    public List<Teacher> getTeacherOfGradename(String teachername) {
        return leaveSysDao.getTeacherOfGradename(teachername);
    }

    @Override
    public List<UserLogin> pwdAjax(String uname, String pwd) {
        return leaveSysDao.pwdAjax(uname, pwd);
    }

    @Override
    public boolean changePwd(String uname, String pwd) {
        return leaveSysDao.changePwd(uname, pwd);
    }

    @Override
    public boolean addUpload(String file, String uname) {
        return leaveSysDao.addUpload(file,uname);
    }

    @Override
    public UserLogin getUser(String uname) {
        return leaveSysDao.getUser(uname);
    }

}
