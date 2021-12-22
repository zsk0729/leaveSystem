package com.jxd.controller;

import com.jxd.model.LeaveMsg;
import com.jxd.model.Teacher;
import com.jxd.service.ITeacherService;
import com.jxd.util.ChartFont;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.DefaultCategoryDataset;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Desecription TODO
 * @Auther ZhengShouke
 * @Date 2020/9/8 19:20
 */
@Controller
@SessionAttributes({"adminname"})
public class TeacherController {
    @Autowired
    ITeacherService teacherService;
    ChartFont chartFont = new ChartFont();

    /**
     * 跳转至所有请假信息列表
     * @return
     */
    @RequestMapping("/teacherAllLeaveMsg")
    public String teacherAllLeaveMsg(){
        return "teacher/teacherAllLeaveMsg";
    }

    /**
     * 跳转至等待批准页面
     * @return
     */
    @RequestMapping("/teacherWaitAgree")
    public String teacherWaitAgree(){
        return "teacher/teacherWaitAgree";
    }

    /**
     * 跳转至已经同意请假页面
     * @return
     */
    @RequestMapping("/teacherAlreadyAgree")
    public String teacherAlreadyAgree(){
        return "teacher/teacherAlreadyAgree";
    }

    /**
     * 跳转至已经拒绝页面
     * @return
     */
    @RequestMapping("/teacherRefuse")
    public String teacherRefuse(){
        return "teacher/teacherRefuse";
    }

    /**
     * 跳转至查看班级请假率页面
     * @return
     */
    @RequestMapping("/teacherClassLeaveRate")
    public String teacherClassLeaveRate(){
        return "teacher/teacherClassLeaveRate";
    }

    /**
     * 跳转至查看学生个人请假率页面
     * @return
     */
    @RequestMapping("/teacherStudentLeaveRate")
    public String teacherStudentLeaveRate(){
        return "teacher/teacherStudentLeaveRate";
    }

    /**
     * 获取该教师所在班级的所有请假信息
     * @param gradename 教师所在班级名称
     * @param studentname 学生姓名
     * @param starttime 开始时间
     * @param endtime 结束时间
     * @param limit 跳过几条数据
     * @param page 获取几条数据
     * @return
     */
    @RequestMapping("/teacherGetAllLeaveMsg")
    @ResponseBody
    public Map<String,Object> teacherGetAllLeaveMsg(String gradename,String studentname,String starttime,String endtime, String limit,String page){
        //过滤条件
        List<LeaveMsg> list = teacherService.teacherGetAllLeaveMsg(gradename);
        //获取分页数据
        //获取一页显示几条数据
        int pageSize = Integer.parseInt(limit);
        //获取当前页，默认为page
        int pageIndex = (Integer.parseInt(page) - 1)*pageSize;

        //分页查询
        List<LeaveMsg> list1 = teacherService.teacherGetAllLeaveMsgPage(gradename,studentname,starttime,endtime,pageSize,pageIndex);

        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",list.size());
        map.put("data",list1);
        return map;
    }

    /**
     * 教师端批量删除请假信息
     * @param leaveids 请假信息id
     * @return
     */
    @RequestMapping(value = "/deleteTeacherLeaveMsgBatch",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteTeacherLeaveMsgBatch(String leaveids){
        String[] leaveid = leaveids.split(",");
        if (teacherService.deleteTeacherLeaveMsgBatch(leaveid)){
            return "true";
        }else {
            return "false";
        }
    }

    /**
     * 教师端删除单条请假信息
     * @param leaveid 请假信息id
     * @return
     */
    @RequestMapping(value = "/deleteTeacherLeaveMsg",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteTeacherLeaveMsg(String leaveid){
        if (teacherService.deleteTeacherLeaveMsg(leaveid)){
            return "true";
        }else {
            return "false";
        }
    }

    /**
     * 教师端查看请假详细信息，并将根据leaveid获取的请假信息放入session
     * @param leaveid 请假信息id
     * @return
     */
    @RequestMapping("/teacherViewLeaveMsg")
    public String teacherViewLeaveMsg(String leaveid, Model model){
        LeaveMsg leaveMsg = teacherService.teacherViewLeaveMsg(leaveid);
        model.addAttribute("leaveMsg",leaveMsg);
        return "teacher/teacherViewLeaveMsg";
    }

    /**
     * 教师端获取等待审核的请假信息
     * @param gradename 班级名称
     * @param studentname 学生姓名
     * @param limit 跳过几条数据
     * @param page 获取几条数据
     * @return
     */
    @RequestMapping("/teacherGetWaitAgreeLeaveMsg")
    @ResponseBody
    public Map<String,Object> teacherGetWaitAgreeLeaveMsg(String gradename,String studentname,String limit,String page){
        //过滤条件
        List<LeaveMsg> list = teacherService.teacherGetWaitAgreeLeaveMsg(gradename);
        //获取分页数据
        //获取一页显示几条数据
        int pageSize = Integer.parseInt(limit);
        //获取当前页，默认为page
        int pageIndex = (Integer.parseInt(page) - 1)*pageSize;

        //分页查询
        List<LeaveMsg> list1 = teacherService.teacherGetWaitAgreeLeaveMsgPage(gradename,studentname,pageSize,pageIndex);

        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",list.size());
        map.put("data",list1);
        return map;
    }

    /**
     * 批量同意请假申请
     * @param leaveids 请假信息id
     * @param teachername 教师姓名
     * @return
     */
    @RequestMapping(value = "/agreeLeaveMsgBatch",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String agreeLeaveMsgBatch(String leaveids,String teachername){
        String[] leaveid = leaveids.split(",");
        if (teacherService.agreeLeaveMsgBatch(leaveid,teachername)){
            return "true";
        }else {
            return "false";
        }
    }

    /**
     * 批量拒绝请假申请
     * @param leaveids 请假信息id
     * @param teachername 教师姓名
     * @return
     */
    @RequestMapping(value = "/refuseLeaveMsgBatch",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String refuseLeaveMsgBatch(String leaveids,String teachername){
        String[] leaveid = leaveids.split(",");
        if (teacherService.refuseLeaveMsgBatch(leaveid,teachername)){
            return "true";
        }else {
            return "false";
        }
    }

    /**
     * 同意请假申请
     * @param leaveid 请假信息id
     * @param teachername 教师姓名
     * @return
     */
    @RequestMapping(value = "/agreeLeaveMsg",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String agreeLeaveMsg(String leaveid,String teachername){
        if (teacherService.agreeLeaveMsg(leaveid,teachername)){
            return "true";
        }else {
            return "false";
        }
    }

    /**
     * 拒绝请假申请
     * @param leaveid 请假信息id
     * @param teachername 教师姓名
     * @return
     */
    @RequestMapping(value = "/refuseLeaveMsg",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String refuseLeaveMsg(String leaveid,String teachername){
        if (teacherService.refuseLeaveMsg(leaveid,teachername)){
            return "true";
        }else {
            return "false";
        }
    }

    /**
     * 教师端获取所有已经同意的请假信息
     * @param gradename 教师所在班级名称
     * @param studentname 学生姓名
     * @param limit 跳过几条数据
     * @param page 获取几条数据
     * @return
     */
    @RequestMapping(value = "/getAlreadyAgreeLeaveMsg")
    @ResponseBody
    public Map<String,Object> getAlreadyAgreeLeaveMsg(String gradename,String studentname,String limit,String page){
        //过滤条件
        List<LeaveMsg> list = teacherService.getAlreadyAgreeLeaveMsg(gradename);
        //获取分页数据
        //获取一页显示几条数据
        int pageSize = Integer.parseInt(limit);
        //获取当前页，默认为page
        int pageIndex = (Integer.parseInt(page) - 1)*pageSize;

        //分页查询
        List<LeaveMsg> list1 = teacherService.getAlreadyAgreeLeaveMsgPage(gradename,studentname,pageSize,pageIndex);

        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",list.size());
        map.put("data",list1);
        return map;
    }

    /**
     * 教师端获取所有已经拒绝的请假信息
     * @param gradename 教师所在班级名称
     * @param studentname 学生姓名
     * @param limit 跳过几条数据
     * @param page 获取几条数据
     * @return
     */
    @RequestMapping(value = "/getRefuseLeaveMsg")
    @ResponseBody
    public Map<String,Object> getRefuseLeaveMsg(String gradename,String studentname,String limit,String page){
        //过滤条件
        List<LeaveMsg> list = teacherService.getRefuseLeaveMsg(gradename);
        //获取分页数据
        //获取一页显示几条数据
        int pageSize = Integer.parseInt(limit);
        //获取当前页，默认为page
        int pageIndex = (Integer.parseInt(page) - 1)*pageSize;

        //分页查询
        List<LeaveMsg> list1 = teacherService.getRefuseLeaveMsgPage(gradename,studentname,pageSize,pageIndex);

        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",list.size());
        map.put("data",list1);
        return map;
    }

    /**
     * 班级请假率
     * @param request
     * @param response
     * @param modelMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "teacherClassGetColumnLine")
    public ModelAndView teacherClassGetColumnLine(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,String gradename)
            throws Exception {
        Teacher teacher = teacherService.getSumStudent(gradename);
        int sumStudent = teacher.getSumStudent();

        DefaultCategoryDataset monthLeaveDate = teacherService.classLeaveRate(sumStudent,gradename);

        JFreeChart chart = ChartFactory.createLineChart("每月请假率", // 主标题的名称
                "月份", // X轴的标签
                "请假率(%)", // Y轴的标签
                monthLeaveDate, // 图标显示的数据集合
                PlotOrientation.VERTICAL, // 图像的显示形式（水平或者垂直）
                true, // 是否显示子标题
                true, // 是否生成提示的标签
                true); // 是否生成URL链接

        chartFont.setJfreeLine(chart);

        // 6. 将图形转换为图片，传到前台
        String fileName = ServletUtilities.saveChartAsPNG(chart, 1200, 650, null, request.getSession());
        String chartURL = request.getContextPath() + "/chart?filename=" + fileName;
        modelMap.put("chartURLLine", chartURL);
        return new ModelAndView("teacher/teacherClassLeaveRate", modelMap);
    }

    /**
     * 跳转至上传头像页面
     * @return
     */
    @RequestMapping("/teacherUploadImg")
    public String uploadImg(){
        return "teacher/teacherUploadImg";
    }
}
