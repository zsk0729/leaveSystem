package com.jxd.controller;

import com.jxd.dao.IAdminDao;
import com.jxd.model.*;
import com.jxd.service.ILeaveSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @Desecription TODO
 * @Auther ZhengShouke
 * @Date 2020/9/9 8:28
 */
@Controller
@SessionAttributes({"管理员","教师","学生","uname","teacherList","gradeList","parentList","leaveStudentOfGradename",
        "teacherOfGradename","studentid","users"})
public class LeaveSysController {
    @Autowired
    ILeaveSysService leaveSysService;
    @Autowired
    IAdminDao adminDao;

    /**
     * 判断账号密码是否正确
     * @param userid 用户id
     * @param pwd 密码
     * @param role 登录角色
     * @param rememberMe 记住密码
     * @param model
     * @param request
     * @param response
     * @return 账号权限
     */
    @RequestMapping(value = "/login2" ,produces = "text/html;charset=utf-8")
    @ResponseBody //对ajax操作，将相应内容添加至响应流
    public String login2( String userid, String pwd, String role, String rememberMe, Model model,
                          HttpServletRequest request, HttpServletResponse response){
        //查询账号信息
        UserLogin userLogin = leaveSysService.isLogin(userid, pwd, role);
        if (userLogin == null){
            return "账号或密码错误";
        }else {
            String uname = userLogin.getUname();
            //判断是否要记住密码
            if ("y".equals(rememberMe)){
                //创建存放用户名的cookie
                Cookie useridCookie = new Cookie("useridCookie",userid);
                //设置cookie的生命周期,单位为秒
                useridCookie.setMaxAge(60 * 3);//三分钟
                //将cookie添加至响应流
                response.addCookie(useridCookie);
                //创建存放密码的cookie
                Cookie pwdCookie = new Cookie("pwdCookie",pwd);
                //设置cookie的生命周期,单位为秒
                pwdCookie.setMaxAge(60 * 3);//三分钟
                //将cookie添加至响应流
                response.addCookie(pwdCookie);
            }
            if ("管理员".equals(userLogin.getRole())){
                //获取个人信息
                UserLogin users= leaveSysService.getUser(uname);
                model.addAttribute("users",users);
                //获取所有班级信息
                List<Grade> gradeList = leaveSysService.getAllGrade();
                //获取所有教师信息
                List<Teacher> teacherList = adminDao.getAllTeacher("");
                model.addAttribute("gradeList",gradeList);
                model.addAttribute("teacherList",teacherList);
                model.addAttribute("管理员",uname);
                model.addAttribute("uname",uname);
                return "管理员";
                //return "index";
            }else if ("教师".equals(userLogin.getRole())){
                //获取个人信息
                UserLogin users= leaveSysService.getUser(uname);
                model.addAttribute("users",users);
                model.addAttribute("uname",uname);
                model.addAttribute("教师",uname);
                //获取该教师所在班级名称
                List<Teacher> teacherList = leaveSysService.getTeacherOfGradename(uname);
                String teacherOfGradename = null;
                //取出班级名称
                for (Teacher teacher : teacherList){
                    teacher.setGradename(teacherList.get(0).getGradename());
                    teacherOfGradename = teacher.getGradename();
                }
                model.addAttribute("teacherOfGradename",teacherOfGradename);
                return "教师";
            }else{
                //获取个人信息
                UserLogin users= leaveSysService.getUser(uname);
                model.addAttribute("users",users);
                model.addAttribute("uname", uname);
                model.addAttribute("学生", uname);
                //获取该学生的所有家长信息
                List<Parent> parentList = leaveSysService.getAllParent(uname);
                model.addAttribute("parentList",parentList);

                //获取该生班级信息
                List<Student> studentList = leaveSysService.leaveStudentOfGrade(uname);
                String gradename = null;
                //得到学生编号用于添加家长时设置学生编号
                String studentid = null;
                for (Student student:studentList){
                    student.setGradename(studentList.get(0).getGradename());
                    student.setStudentid(studentList.get(0).getStudentid());
                    studentid = student.getStudentid();
                    gradename = student.getGradename();
                }
                model.addAttribute("leaveStudentOfGradename",gradename);
                model.addAttribute("studentid",studentid);
                return "学生";
            }
        }
    }

    @RequestMapping(value = "/login")
     //对ajax操作，将相应内容添加至响应流
    public String login(UserLogin userLogin,Model model){
        //查询账号信息
        UserLogin userLogin1 = leaveSysService.isLogin(userLogin);
        if (userLogin1 == null){
            return "账号或密码错误";
        }else {
            if ("管理员".equals(userLogin.getRole())){
                //获取所有班级信息
                List<Grade> gradeList = leaveSysService.getAllGrade();
                //获取所有教师信息
                List<Teacher> teacherList = adminDao.getAllTeacher("");
                model.addAttribute("gradeList",gradeList);
                model.addAttribute("teacherList",teacherList);
                model.addAttribute("uname",userLogin.getUname());
                return "admin/adminHomePage";
                //return "index";
            }else if ("教师".equals(userLogin.getRole())){
                model.addAttribute("uname",userLogin.getUname());
                //获取该教师所在班级名称
                List<Teacher> teacherList = leaveSysService.getTeacherOfGradename(userLogin.getUname());
                String teacherOfGradename = null;
                //去除班级名称
                for (Teacher teacher : teacherList){
                    teacher.setGradename(teacherList.get(0).getGradename());
                    teacherOfGradename = teacher.getGradename();
                }
                model.addAttribute("teacherOfGradename",teacherOfGradename);
                return "teacher/teacherHomePage";
            }else{
                model.addAttribute("uname", userLogin.getUname());
                //获取该学生的所有家长信息
                List<Parent> parentList = leaveSysService.getAllParent(userLogin.getUname());
                model.addAttribute("parentList",parentList);

                //获取该生班级信息
                List<Student> studentList = leaveSysService.leaveStudentOfGrade(userLogin.getUname());
                String gradename = null;
                for (Student student:studentList){
                    student.setGradename(studentList.get(0).getGradename());
                    gradename = student.getGradename();
                }
                model.addAttribute("leaveStudentOfGradename",gradename);
                return "studentHomePage";
            }
        }
    }

    /**
     * 跳转至管理员页面
     * @return
     */
    @RequestMapping("/admin")
    public String adminHomePage(){
        return "admin/adminHomePage";
    }

    /**
     * 跳转至教师页面
     * @return
     */
    @RequestMapping("/teacher")
    public String teacherHomePage(){
        return "teacher/teacherHomePage";
    }

    /**
     * 跳转至学生页面
     * @return
     */
    @RequestMapping("/student")
    public String student(){
        return "student/studentHomePage";
    }

    /**
     * 判断输入旧密码是否与原密码相同
     * @param uname 用户名
     * @param pwd 密码
     * @return
     */
    @RequestMapping(value = "/pwdAjax",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String pwdAjax(String uname,String pwd){
        List<UserLogin> userLoginList = leaveSysService.pwdAjax(uname,pwd);
        if (userLoginList.size() != 0){
            return "密码正确";
        }else {
            return "输入旧密码错误";
        }
    }

    /**
     * 跳转至修改密码页面
     * @return
     */
    @RequestMapping("/changePwdJsp")
    public String adminChangePwd(){
        return "changePwd";
    }

    /**
     * 修改密码
     * @param pwd 密码
     * @param uname 用户名
     * @return
     */
    @RequestMapping(value = "/changePwd",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String changePwd(String pwd,String uname){
        if (leaveSysService.changePwd(uname,pwd)){
            return "修改成功";
        }else {
            return "修改失败";
        }
    }

    /**
     * 退出
     * @return
     */
    @RequestMapping("/logout")
    public String logout(){
        return "login";
    }

    /**
     * 上传头像
     * @param multipartFile
     * @param request
     * @param httpSession
     * @param model
     * @return
     */
    @RequestMapping(value = "/uploadFile",produces =  "text/html;charset=utf-8")
    @ResponseBody
    public String uploadFile(@RequestParam("upload") MultipartFile multipartFile, HttpServletRequest request, HttpSession httpSession, Model model){

        //创建一个目录files，用于存放上传的文件
        String savePath = request.getServletContext().getRealPath("files");
        File file = new File(savePath);

        //如果files不存在，或者不是目录，我们创建它
        if (!file.exists() || !file.isDirectory()){
            file.mkdir();
        }

        //获取原文件名 xxx.png
        String oldName = multipartFile.getOriginalFilename();
        //对原文件名进行处理
        String newName = UUID.randomUUID() + "_" + oldName;

        //根据保存路径和新名字生成一个文件对象
        File saveFile = new File(savePath,newName);

        try {
            //将SpringMVC接收到的文件对象转换为普通的文件对象
            //转换的过程即保存的过程
            multipartFile.transferTo(saveFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回文件保存路径及文件名，以便删除时使用
        /*return "files/" + newName;*/
        String name= (String) httpSession.getAttribute("uname");
        String file3 = "files/" + newName;
        boolean isupload = leaveSysService.addUpload(file3,name);
        String names = (String) httpSession.getAttribute("uname");
        UserLogin user= leaveSysService.getUser(names);
        model.addAttribute("users",user);
        if (isupload){
            return "上传成功";
        }else{
            return "上传失败";
        }
    }

}
