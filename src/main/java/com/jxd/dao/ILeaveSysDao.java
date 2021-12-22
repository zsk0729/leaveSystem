package com.jxd.dao;

import com.jxd.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ILeaveSysDao {

    /**
     * 判断用户名或密码是否正确
     * @param userLogin
     * @param
     * @return
     */
    UserLogin isLogin(UserLogin userLogin);
    UserLogin isLogin2(@Param("userid") String userid,@Param("pwd") String pwd,@Param("role") String role);

    /**
     * 获取所有班级信息
     * @return
     */
    List<Grade> getAllGrade();

    /**
     * 获取该学生的所有家长信息
     * @param studentname
     * @return
     */
    List<Parent> getAllParent(String studentname);

    /**
     * 获取该生的班级信息
     * @param studentname
     * @return
     */
    List<Student> leaveStudentOfGrade(String studentname);

    /**
     * 获取该老师所在班级信息
     * @param teachername
     * @return
     */
    List<Teacher> getTeacherOfGradename(String teachername);

    /**
     * 判断输入的旧密码是否与原密码相同
     * @param uname
     * @param pwd
     * @return
     */
    List<UserLogin> pwdAjax(@Param("uname") String uname,@Param("pwd") String pwd);

    /**
     * 修改密码
     * @param uname
     * @param pwd
     * @return
     */
    boolean changePwd(@Param("uname") String uname,@Param("pwd") String pwd);

    /**
     * 给用户上传一个头像
     * @param file
     * @param uname
     * @return
     */
    boolean addUpload(@Param("file") String file,@Param("uname") String uname);

    /**
     * 根据名字取出用户信息
     * @param uname
     * @return
     */
    UserLogin  getUser(String uname);
}
