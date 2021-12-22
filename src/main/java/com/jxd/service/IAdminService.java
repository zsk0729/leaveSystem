package com.jxd.service;

import com.jxd.model.*;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;

import java.util.List;

/**
 * @Desecription TODO
 * @Auther ZhengShouke
 * @Date 2020/9/9 14:22
 */
public interface IAdminService {

    /**
     * 获取所有班级
     * @param gradename 班级名称
     * @return
     */
    List<Grade> getAllGrade(String gradename);

    /**
     * 分页查询获取班级列表
     * @param gradename 班级名称
     * @param pageSize 查询几页
     * @param pageIndex 跳过几页
     * @return
     */
    List<Grade> getAllGradePage(int pageSize, int pageIndex,String gradename);

    /**
     * 获取所有教师
     * @param teachername 教师名字
     * @return
     */
    List<Teacher> getAllTeacher(String teachername);

    /**
     * 获取所有没有分配班级的教师
     * @return
     */
    List<Teacher> getAllTeacherUnallocat();

    /**
     * 分页查询获取教师列表
     * @param teachername 教师名字
     * @param pageSize 获取几条数据
     * @param pageIndex 跳过几页
     * @return
     */
    List<Teacher> getAllTeacherPage(int pageSize, int pageIndex,String teachername);

    /**
     * 判断班级名称是否已经存在
     * @param gradename 班级名称
     * @return
     */
    List<Grade> gradeNameAjax(String gradename);

    /**
     * 添加班级
     * @param gradename 班级名称
     * @return
     */
    boolean addGrade(String gradename);

    /**
     * 设置老师所属班级
     * @param gradename 教师所在班级名字
     * @param teacherid 教师id
     * @return
     */
    boolean setTeacherOfGrade(String gradename,String teacherid);

    /**
     * 批量删除班级
     * @param gradeids 班级id
     * @return
     */
    boolean delGradeBatch(String[] gradeids);

    /**
     * 删除班级
     * @param gradeid 班级id
     * @return
     */
    boolean delGrade(String gradeid);

    /**
     * 根据班级名称获取该班级教师数量
     * @param gradename 班级名称
     * @return
     */
    List<Teacher> getTeacherNumberByGradename(String gradename);

    /**
     * 获取所有学生列表
     * @param gradename 班级名称
     * @param studentname 学生名称
     * @return
     */
    List<Student> getAllStudent(String gradename,String studentname);

    /**
     * 分页查询获取所有学生列表
     * @param pageSize 获取几条数据
     * @param pageIndex 跳过几条数据
     * @param gradename 班级名称
     * @param studentname 学生名字
     * @return
     */
    List<Student> getAllStudentPage(int pageSize, int pageIndex,String gradename,String studentname);

    /**
     * 添加学生
     * @param student 学生实体类
     * @return
     */
    boolean addStudent(Student student);

    /**
     * 将新添加的学生添加至用户表
     * @param studentname 学生名字
     * @return
     */
    boolean addUserStudent(String studentname);

    /**
     * 批量删除学生
     * @param studentid 学生id
     * @return
     */
    boolean deleteStudentBatch(String[] studentid);

    /**
     * 根据学生编号批量删除家长
     * @param studentid 学生id
     * @return
     */
    boolean deleteParentBatch(String[] studentid);

    /**
     * 删除学生
     * @param studentid 学生id
     * @return
     */
    boolean deleteStudent(String studentid);

    /**
     * 根据学号删除家长
     * @param studentid 学生id
     * @return
     */
    boolean deleteParent(String studentid);

    /**
     * 修改学生信息
     * @param student 学生实体类
     * @return
     */
    boolean updateStudent(Student student);

    /**
     * 添加教师
     * @param teacher 教师实体类
     * @return
     */
    boolean addTeacher(Teacher teacher);

    /**
     * 将新添加的教师添加至用户表
     * @param teacher 教师实体类
     * @return
     */
    boolean addUserTeacher(Teacher teacher);

    /**
     * 批量删除教师
     * @param teacherid 教师id
     * @return
     */
    boolean deleteTeacherBatch(String[] teacherid);

    /**
     * 删除教师
     * @param teacherid 教师id
     * @return
     */
    boolean deleteTeacher(String teacherid);

    /**
     * 编辑教师
     * @param teacher 教师实体类
     * @return
     */
    boolean updateTeacher(Teacher teacher);

    /**
     * 获取所有用户
     * @param uname 用户名
     * @param userid 用户id
     * @return
     */
    List<UserLogin> getAllUser(String uname, String userid);

    /**
     * 分页获取所有用户
     * @param uname 用户名
     * @param userid 用户id
     * @param pageSize 获取几条数据
     * @param pageIndex 跳过几条数据
     * @return
     */
    List<UserLogin> getAllUserPage(String uname, String userid, int pageSize, int pageIndex);

    /**
     * 重置用户密码
     * @param userid 用户id
     * @return
     */
    boolean editUser(String userid);

    /**
     * 管理员获取每月每个班级请假率
     * @param num 班级人数
     * @param gradename 班级名称
     * @return
     */
    TimeSeries classMonthLeaveRate(int num,String gradename);

    /**
     * 管理员获取每月每个班内请假人数
     * @param month 月份
     * @param gradename 班级名称
     * @return
     */
    Admin leaveStudentNumOfMonth(String month, String gradename);

    /**
     * 获取班级总人数
     * @param gradename 班级名称
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
     * @param month 月份
     * @return
     */
    Admin getSumLeaveMonth(String month);

    /**
     * 根据班级编号获取班级名称
     * @param gradeid 班级id
     * @return
     */
    Grade getGradenameByGradeid(String gradeid);


}
