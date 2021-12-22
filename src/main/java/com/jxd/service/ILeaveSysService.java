package com.jxd.service;

import com.jxd.model.*;

import java.util.List;

public interface ILeaveSysService {

    /**
     * 判断用户名或密码是否正确
     * @param userLogin 用户实体类
     * @return
     */
    UserLogin isLogin(UserLogin userLogin);
    UserLogin isLogin(String userid,String pwd,String role);

    /**
     * 获取所有班级信息
     * @return
     */
    List<Grade> getAllGrade();

    /**
     * 获取该学生的所有家长信息
     * @param studentname 学生姓名
     * @return
     */
    List<Parent> getAllParent(String studentname);

    /**
     * 获取该生的班级信息
     * @param studentname 学生姓名
     * @return
     */
    List<Student> leaveStudentOfGrade(String studentname);

    /**
     * 获取该老师所在班级信息
     * @param teachername 教师名称
     * @return
     */
    List<Teacher> getTeacherOfGradename(String teachername);

    /**
     * 判断输入的旧密码是否与原密码相同
     * @param uname 用户名
     * @param pwd 密码
     * @return
     */
    List<UserLogin> pwdAjax(String uname,String pwd);

    /**
     * 修改密码
     * @param uname 用户名
     * @param pwd 密码
     * @return
     */
    boolean changePwd(String uname,String pwd);

    /**
     * 给用户上传一个头像
     * @param file 文件名
     * @param uname 用户名
     * @return
     */
    boolean addUpload(String file,String uname);

    /**
     * 根据名字取出用户信息
     * @param uname 用户名
     * @return
     */
    UserLogin  getUser(String uname);
}
