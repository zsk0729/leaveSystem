package com.jxd.dao;

import com.jxd.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IAdminDao {

    /**
     * 获取所有班级
     * @param gradename
     * @return
     */
    List<Grade> getAllGrade(String gradename);

    /**
     * 分页查询获取班级列表
     * @param gradename
     * @param pageSize
     * @param pageIndex
     * @return
     */
    List<Grade> getAllGradePage(@Param("pageSize") int pageSize,@Param("pageIndex") int pageIndex,
                                @Param("gradename") String gradename);

    /**
     * 获取所有教师
     * @param teachername
     * @return
     */
    List<Teacher> getAllTeacher(String teachername);

    /**
     * 分页查询获取教师列表
     * @param teachername
     * @param pageSize
     * @param pageIndex
     * @return
     */
    List<Teacher> getAllTeacherPage(@Param("pageSize")int pageSize,@Param("pageIndex") int pageIndex,
                                  @Param("teachername") String teachername);

    /**
     * 获取所有没有分配班级的教师
     * @return
     */
    List<Teacher> getAllTeacherUnallocat();

    /**
     * 判断班级名称是否已经存在
     * @param gradename
     * @return
     */
    List<Grade> gradeNameAjax(String gradename);

    /**
     * 添加班级
     * @param gradename
     * @return
     */
    boolean addGrade(String gradename);

    /**
     * 设置老师所属班级
     * @param gradename
     * @param teacherid
     * @return
     */
    boolean setTeacherOfGrade(@Param("gradename") String gradename,@Param("teacherid") String teacherid);

    /**
     * 批量删除班级
     * @param gradeids
     * @return
     */
    boolean delGradeBatch(String[] gradeids);

    /**
     * 删除班级
     * @param gradeid
     * @return
     */
    boolean delGrade(String gradeid);

    /**
     * 根据班级名称获取该班级教师数量
     * @param gradename
     * @return
     */
    List<Teacher> getTeacherNumberByGradename(String gradename);

    /**
     * 获取所有学生列表
     * @param gradename
     * @param studentname
     * @return
     */
    List<Student> getAllStudent(@Param("gradename") String gradename,@Param("studentname") String studentname);

    /**
     * 分页查询获取所有学生列表
     * @param pageSize
     * @param pageIndex
     * @param gradename
     * @param studentname
     * @return
     */
    List<Student> getAllStudentPage(@Param("pageSize") int pageSize,@Param("pageIndex") int pageIndex,
                                    @Param("gradename") String gradename,@Param("studentname") String studentname);

    /**
     * 添加学生
     * @param student
     * @return
     */
    boolean addStudent(Student student);

    /**
     * 将新添加的学生添加至用户表
     * @param studentname
     * @return
     */
    boolean addUserStudent(String studentname);

    /**
     * 批量删除学生
     * @param studentid
     * @return
     */
    boolean deleteStudentBatch(String[] studentid);

    /**
     * 根据学生编号批量删除家长
     * @param studentid
     * @return
     */
    boolean deleteParentBatch(String[] studentid);

    /**
     * 根据学号删除家长
     * @param studentid
     * @return
     */
    boolean deleteParent(String studentid);

    /**
     * 删除学生
     * @param studentid
     * @return
     */
    boolean deleteStudent(String studentid);

    /**
     * 修改学生信息
     * @param student
     * @return
     */
    boolean updateStudent(Student student);

    /**
     * 添加教师
     * @param teacher
     * @return
     */
    boolean addTeacher(Teacher teacher);

    /**
     * 将新添加的教师添加至用户表
     * @param teacher
     * @return
     */
    boolean addUserTeacher(Teacher teacher);

    /**
     * 批量删除教师
     * @param teacherid
     * @return
     */
    boolean deleteTeacherBatch(String[] teacherid);

    /**
     * 删除教师
     * @param teacherid
     * @return
     */
    boolean deleteTeacher(String teacherid);

    /**
     * 编辑教师
     * @param teacher
     * @return
     */
    boolean updateTeacher(Teacher teacher);

    /**
     * 获取所有用户
     * @param uname
     * @param userid
     * @return
     */
    List<UserLogin> getAllUser(@Param("uname") String uname,@Param("userid") String userid);

    /**
     * 分页获取所有用户
     * @param uname
     * @param userid
     * @param pageSize
     * @param pageIndex
     * @return
     */
    List<UserLogin> getAllUserPage(@Param("uname") String uname,@Param("userid") String userid,
                                   @Param("pageSize") int pageSize,@Param("pageIndex") int pageIndex);

    /**
     * 重置用户密码
     * @param userid
     * @return
     */
    boolean editUser(String userid);

    /**
     * 管理员获取每月每个班内请假人数
     * @param month
     * @return
     */
    Admin leaveStudentNumOfMonth(@Param("month") String month,@Param("gradename") String gradename);

    /**
     * 获取班级总人数
     * @param gradename
     * @return
     */
    Admin getSumStudentClass(String gradename);

    /**
     * 获取学生总数
     * @return
     */
    Admin getSumAllStudent();

    /**
     * 获取每个月请假的总人数
     * @param month
     * @return
     */
    Admin getSumLeaveMonth(String month);

    /**
     * 根据班级编号获取班级名称
     * @param gradeid
     * @return
     */
    Grade getGradenameByGradeid(String gradeid);











}
