package com.jxd.controller;

import com.jxd.model.Grade;
import com.jxd.model.Student;
import com.jxd.model.Teacher;
import com.jxd.model.UserLogin;
import com.jxd.service.IAdminService;
import com.jxd.util.ChartFont;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.TextAnchor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Desecription TODO
 * @Auther ZhengShouke
 * @Date 2020/9/9 9:27
 */
@Controller
@SessionAttributes({"unallocatTeacher","editGradename","teacherNumber","studentid",
        "teacherid"})
public class AdminController {
    @Autowired
    IAdminService adminService;
    ChartFont chartFont = new ChartFont();

    /**
     * 管理员页面跳转至班级管理页面
     * @return
     */
    @RequestMapping("/adminGrade")
    public String adminCourse(){
        return "admin/adminGrade";
    }

    /**
     * 管理员页面跳转至学生管理页面
     * @return
     */
    @RequestMapping("/adminStudent")
    public String adminStudent(){
        return "admin/adminStudent";
    }

    /**
     * 管理员页面跳转至教师管理页面
     * @return
     */
    @RequestMapping("/adminTeacher")
    public String adminTeacher(){
        return "admin/adminTeacher";
    }

    /**
     * 管理员页面跳转至学生请假率页面
     * @return
     */
    @RequestMapping("/adminLeaveRate")
    public String adminLeaveRate(){
        return "admin/adminClassLeaveRate";
    }

    /**
     * 管理员页面跳转至账号密码管理页面
     * @return
     */
    @RequestMapping("/adminAccountPwd")
    public String adminAccountPwd(){
        return "admin/adminAccountPwd";
    }

    /**
     * 获取所有班级
     * @param gradename 班级名称
     * @param limit 跳过几条数据
     * @param page 获取几条数据
     * @return
     * @throws IOException
     */
    @RequestMapping("/getAllGrade")
    @ResponseBody
    public Map<String,Object> getAllGrade(String gradename, String limit, String page)throws IOException {
        //过滤条件
        List<Grade> list = adminService.getAllGrade(gradename);
        //获取分页数据
        //获取一页显示几条数据
        int pageSize = Integer.parseInt(limit);
        //获取当前页，默认为page
        int pageIndex = (Integer.parseInt(page) - 1)*pageSize;

        //分页查询
        List<Grade> list1 = adminService.getAllGradePage(pageSize,pageIndex,gradename);

        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",list.size());
        map.put("data",list1);
        return map;
    }

    /**
     * 跳转至添加班级页面
     * @return
     */
    @RequestMapping("/addGradeJsp")
    public String addCourseJsp(Model model){
        //获取所有没有分配班级的教师
        List<Teacher> list = adminService.getAllTeacherUnallocat();
        model.addAttribute("unallocatTeacher",list);
        return "admin/addGrade";
    }

    /**
     * 判断班级名称是否已经存在
     * @param gradename 班级名称
     * @return
     */
    @RequestMapping(value = "/gradeNameAjax",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String gradeNameAjax(String gradename){
        List<Grade> gradeList = adminService.gradeNameAjax(gradename);
        if (gradeList.size() == 0){
            //查不到该班级，说明可以添加该名称
            return "true";
        }else {
            return "false";
        }
    }

    /**
     * 添加班级
     * @param gradename 班级名称
     * @param teacherid 教师Id
     * @return
     */
    @RequestMapping(value = "/addGrade",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String addGrade(String gradename,String teacherid){
        //增加班级，修改老师所属班级
        if (adminService.addGrade(gradename) && adminService.setTeacherOfGrade(gradename,teacherid)){
            return "添加成功";
        }else {
            return "添加失败";
        }
    }

    /**
     * 批量删除班级
     * @param gradeids 班级编号
     * @return
     */
    @RequestMapping(value = "/deleteGradeBatch",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteGradeBatch(String gradeids){
        String[] gradeid = gradeids.split(",");
        for (int j = 0; j < gradeid.length; j++) {
            //在删除班级前先将教师的所属班级设为无(setTeacherOfGrade)
            //根据编号获取班级名称
            String gradename = adminService.getGradenameByGradeid(gradeid[j]).getGradename();
            //根据班级名称获取教师列表
            List<Teacher> list = adminService.getTeacherNumberByGradename(gradename);
            //设置老师的所属班级为无
            for (int i = 0; i < list.size(); i++) {
                adminService.setTeacherOfGrade("无",list.get(i).getTeacherid());
            }
        }
        //最后删除班级
        if (adminService.delGradeBatch(gradeid)){
            return "删除成功";
        }else {
            return "删除失败";
        }
    }

    /**
     * 删除班级
     * @param gradeid 班级编号
     * @return
     */
    @RequestMapping(value = "/deleteGrade",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteGrade(String gradeid){
        //在删除班级前先将教师的所属班级设为无(setTeacherOfGrade)
        //根据编号获取班级名称
        String gradename = adminService.getGradenameByGradeid(gradeid).getGradename();
        //根据班级名称获取教师列表
        List<Teacher> list = adminService.getTeacherNumberByGradename(gradename);
        //设置老师的所属班级为无
        for (int i = 0; i < list.size(); i++) {
            adminService.setTeacherOfGrade("无",list.get(i).getTeacherid());
        }
        //最后删除班级
        if (adminService.delGrade(gradeid)){
            return "删除成功";
        }else {
            return "删除失败";
        }
    }

    /**
     * 得到修改班级的班级名称放入session
     * 得到该班级所拥有的教师数量，放入session
     * @param gradename 班级名称
     * @return
     */
    @RequestMapping(value = "/editGradeSetGradeid",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String editGradeSetGradeid(String gradename,Model model){
        //获取所有没有分配班级的教师
        List<Teacher> list = adminService.getAllTeacherUnallocat();
        //得到该班级所拥有的教师数量，放入session
        List<Teacher> teacherList = adminService.getTeacherNumberByGradename(gradename);
        model.addAttribute("unallocatTeacher",list);
        model.addAttribute("editGradename",gradename);
        model.addAttribute("teacherNumber",teacherList.size());
        return "true";
    }

    /**
     * 得到该班级教师列表
     * @param gradename 班级名称
     * @return
     */
    @RequestMapping("/getAllTeacherAllocated")
    @ResponseBody
    public Map<String,Object> getAllTeacherAllocated(String gradename){
        List<Teacher> teacherList = adminService.getTeacherNumberByGradename(gradename);
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",teacherList.size());
        map.put("data",teacherList);
        return map;
    }

    /**
     * 编辑班级
     * @return
     */
    @RequestMapping("/editGrade")
    public String editGrade(){
        return "admin/editGrade";
    }

    /**
     * 跳转至为班级添加教师页面
     * @return
     */
    @RequestMapping("/addTeacherForGradeJsp")
    public String addTeacherForGradeJsp(){
        return "admin/addTeacherForGrade";
    }

    /**
     * 为该班级添加教师
     * @param gradename 班级名称
     * @param teacherid 教师编号
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/addTeacherForGrade",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String addTeacherForGrade(String gradename, String teacherid){
        if (adminService.setTeacherOfGrade(gradename, teacherid)){
            return "添加成功";
        }else {
            return "添加失败";
        }
    }

    /**
     * 删除班级内的某个教师
     * @param teacherid 教师编号
     * @return
     */
    @RequestMapping(value = "/deleteTeacherForGrade",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteTeacherForGrade(String teacherid){
        if (adminService.setTeacherOfGrade("无",teacherid)){
            return "删除成功";
        }else {
            return "删除失败";
        }
    }

    /**
     * 获取学生列表
     * @param gradename 班级名称
     * @param studentname 学生姓名
     * @param limit 跳过几条数据
     * @param page 获取几条数据
     * @return
     */
    @RequestMapping("/getAllStudent")
    @ResponseBody
    public Map<String,Object> getAllStudent(String gradename,String studentname,String limit,String page){
        //过滤条件
        List<Student> list = adminService.getAllStudent(gradename, studentname);
        //获取分页数据
        //获取一页显示几条数据
        int pageSize = Integer.parseInt(limit);
        //获取当前页，默认为page
        int pageIndex = (Integer.parseInt(page) - 1)*pageSize;

        //分页查询
        List<Student> list1 = adminService.getAllStudentPage(pageSize,pageIndex,gradename,studentname);

        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",list.size());
        map.put("data",list1);
        return map;
    }

    /**
     * 跳转至添加学生页面
     * @return
     */
    @RequestMapping("/addStudentJsp")
    public String addStudentJsp(){
        return "admin/addStudent";
    }

    /**
     * 添加学生
     * @param student 学生实体类
     * @return
     */
    @RequestMapping(value = "/addStudent",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String addStudent(Student student){
        if (adminService.addStudent(student) && adminService.addUserStudent(student.getStudentname())){
            return "添加成功";
        }else {
            return "添加失败";
        }
    }

    /**
     * 批量删除学生
     * @param studentids 学生编号
     * @return
     */
    @RequestMapping(value = "/deleteStudentBatch",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteStudentBatch(String studentids){
        String[] studentid = studentids.split(",");
        //删除学生之前先将家长删除
        boolean isdel = adminService.deleteParentBatch(studentid);
        if (adminService.deleteStudentBatch(studentid)){
            return "删除成功";
        }else {
            return "删除失败";
        }
    }

    /**
     * 删除学生
     * @param studentid 学生编号
     * @return
     */
    @RequestMapping(value = "/deleteStudent",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteStudent(String studentid){
        //删除学生前先删除家长
        boolean isdel = adminService.deleteParent(studentid);
        if (adminService.deleteStudent(studentid)){
                return "删除成功";
            }else {
                return "删除失败";
            }
    }

    /**
     * 将要编辑的学生编号放入session
     * @param studentid 学生编号
     * @param model
     * @return
     */
    @RequestMapping(value = "/editStudentSetStudentid",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String editStudentSetStudentid(String studentid,Model model){
        model.addAttribute("studentid",studentid);
        return "true";
    }

    /**
     * 跳转至学生编辑页面
     * @return
     */
    @RequestMapping("/editStudent")
    public String editStudent(){
        return "admin/editStudent";
    }

    /**
     * 根据学号修改学生信息
     * @param student 学生实体类
     * @return
     */
    @RequestMapping(value = "/updateStudent",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String updateStudent(Student student){
        if (adminService.updateStudent(student)){
            return "编辑成功";
        }else {
            return "编辑失败";
        }
    }

    /**
     * 获取所有教师列表
     * @return
     */
    @RequestMapping("/getAllTeacher")
    @ResponseBody
    public Map<String,Object> getAllTeacher(String teachername, String limit, String page){
        //过滤条件
        List<Teacher> list = adminService.getAllTeacher(teachername);
        //获取分页数据
        //获取一页显示几条数据
        int pageSize = Integer.parseInt(limit);
        //获取当前页，默认为page
        int pageIndex = (Integer.parseInt(page) - 1)*pageSize;

        //分页查询
        List<Teacher> list1 = adminService.getAllTeacherPage(pageSize,pageIndex,teachername);
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",list.size());
        map.put("data",list1);
        return map;
    }

    /**
     * 跳转至添加教师页面
     * @return
     */
    @RequestMapping("/addTeacherJsp")
    public String addTeacherJsp(){
        return "admin/addTeacher";
    }

    /**
     * 添加教师
     * @param teacher 教师实体类
     * @return
     */
    @RequestMapping(value = "/addTeacher",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String addTeacher(Teacher teacher){
        if (adminService.addTeacher(teacher) && adminService.addUserTeacher(teacher)){
            return "添加成功";
        }else {
            return "添加失败";
        }
    }

    /**
     * 批量删除教师
     * @param teacherids 教师编号
     * @return
     */
    @RequestMapping(value = "/deleteTeacherBatch",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteTeacherBatch(String teacherids){
        String[] teacherid = teacherids.split(",");
        if (adminService.deleteTeacherBatch(teacherid)){
            return "删除成功";
        }else {
            return "删除失败";
        }
    }

    /**
     * 删除教师
     * @param teacherid 教师编号
     * @return
     */
    @RequestMapping(value = "/deleteTeacher",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteTeacher(String teacherid){
        if (adminService.deleteTeacher(teacherid)){
            return "删除成功";
        }else {
            return "删除失败";
        }
    }

    /**
     * 将要编辑的教师的编号放入session
     * @param teacherid 教师编号
     * @param model
     * @return
     */
    @RequestMapping(value = "/editTeacherSetTeacherid",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String editTeacherSetTeacherid(String teacherid,Model model){
        model.addAttribute("teacherid",teacherid);
        return "true";
    }

    /**
     * 跳转至教师编辑页面
     * @return
     */
    @RequestMapping("/editTeacher")
    public String editTeacher(){
        return "admin/editTeacher";
    }

    /**
     * 编辑教师
     * @param teacher 教师实体类
     * @return
     */
    @RequestMapping(value = "/updateTeacher",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String updateTeacher(Teacher teacher){
        if (adminService.updateTeacher(teacher)){
            return "编辑成功";
        }else {
            return "编辑失败";
        }
    }

    /**
     * 获取所有登录用户
     * @param uname 用户名
     * @param userid 用户id
     * @param limit 跳过几条数据
     * @param page 获取几条数据
     * @return
     */
    @RequestMapping("/getAllUser")
    @ResponseBody
    public Map<String,Object> getAllUser(String uname, String userid, String limit, String page){
        //过滤条件
        List<UserLogin> list = adminService.getAllUser(uname, userid);
        //获取分页数据
        //获取一页显示几条数据
        int pageSize = Integer.parseInt(limit);
        //获取当前页，默认为page
        int pageIndex = (Integer.parseInt(page) - 1)*pageSize;

        //分页查询
        List<UserLogin> list1 = adminService.getAllUserPage(uname,userid,pageSize,pageIndex);
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",list.size());
        map.put("data",list1);
        return map;

    }

    /**
     * 得到重置用户编号，重置密码
     * @param userid 用户id
     * @return
     */
    @RequestMapping(value = "/editUser",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String editUser(String userid){
        if (adminService.editUser(userid)){
            return "true";
        }else {
            return "false";
        }

    }

    /**
     * 每个班级每月请假率
     * @param request
     * @param response
     * @param modelMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "adminClassLeaveRate")
    public ModelAndView adminClassLeaveRate(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
            throws Exception {
        //调用方法，获取所有班级信息
        List<Grade> gradeList = adminService.getAllGrade("");
        // 遍历集合，取出所有班级名称以及班级内人数
        // 将班级名放入数组gradename中，将人数放入numStudentClass中
        String[] gradename = new String[gradeList.size()];
        int[] numStudentClass = new int[gradeList.size()];
        for (int i = 0; i < gradeList.size(); i++) {
            gradename[i] = gradeList.get(i).getGradename();
            numStudentClass[i] = Integer.parseInt(gradeList.get(i).getGradeHaveStudent());
        }

        //获取每个班级每月请假率，将得到的数据放入数组monthLeaveDate中
        TimeSeries[] monthLeaveDate = new TimeSeries[gradename.length];
        for (int i = 0; i < gradename.length; i++) {
            monthLeaveDate[i] = adminService.classMonthLeaveRate(numStudentClass[i],gradename[i]);
        }

        //定义时间序列的集合
        TimeSeriesCollection lineDate = new TimeSeriesCollection();
        for (int i = 0; i < gradename.length; i++) {
            lineDate.addSeries(monthLeaveDate[i]);
        }

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "请假率折线图",
                "月份",
                "请假率(%)",
                lineDate,
                true,
                true,
                true
        );

        //设置主标题
        chart.setTitle(new TextTitle("各班级请假率对比图", new Font("隶书",Font.ITALIC,15)));
        //设置子标题
        TextTitle subTitle = new TextTitle("2019年",new Font("黑体",Font.BOLD,12));
        chart.addSubtitle(subTitle);
        chart.setAntiAlias(true);

        //设置时间轴范围
        XYPlot plot = (XYPlot) chart.getPlot();
        DateAxis dateAxis = (DateAxis) plot.getDomainAxis();
        dateAxis.setDateFormatOverride(new java.text.SimpleDateFormat("M月"));
        dateAxis.setTickUnit(new DateTickUnit(DateTickUnit.MONTH,1));

        //设置折线图是否显示数据点
        XYLineAndShapeRenderer xyLineAndShapeRenderer = (XYLineAndShapeRenderer) plot.getRenderer();
        xyLineAndShapeRenderer.setBaseShapesVisible(true);

        //设置折现显示各数据点的值
        XYItemRenderer xyItemRenderer = plot.getRenderer();
        xyItemRenderer.setBaseItemLabelsVisible(true);
        xyItemRenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
        xyItemRenderer.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
        xyItemRenderer.setBaseItemLabelFont(new Font("Dialog",1,12));
        plot.setRenderer(xyItemRenderer);

        //将图形转换为图片，传到前台
        String fileName = ServletUtilities.saveChartAsPNG(chart, 1200, 650, null, request.getSession());
        String chartURL = request.getContextPath() + "/chart?filename=" + fileName;
        modelMap.put("chartURLLine", chartURL);
        return new ModelAndView("admin/adminClassLeaveRate", modelMap);
    }

    /**
     * 跳转至上传头像页面
     * @return
     */
    @RequestMapping("/adminUploadImg")
    public String uploadImg(){
        return "admin/adminUploadImg";
    }



}
