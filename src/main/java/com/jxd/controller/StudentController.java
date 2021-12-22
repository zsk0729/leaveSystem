package com.jxd.controller;

import com.jxd.model.LeaveMsg;
import com.jxd.model.Parent;
import com.jxd.service.ILeaveSysService;
import com.jxd.service.IStudentService;
import com.jxd.util.ChartFont;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Desecription TODO
 * @Auther ZhengShouke
 * @Date 2020/9/12 9:00
 */
@Controller
@SessionAttributes({"leaveMsg","leaveStudentOfGradename","parentList"})
public class StudentController {
    @Autowired
    IStudentService studentService;
    @Autowired
    ILeaveSysService leaveSysService;
    //图表工具类
    private ChartFont chartFont = new ChartFont();

    /**
     * 跳转至学生所有请假信息页面
     * @return
     */
    @RequestMapping("/studentAllLeaveMsg")
    public String studentAllLeaveMsg(){
        return "student/studentAllLeaveMsg";
    }

    /**
     * 获取学生个人所有请假信息
     * @param studentname 学生姓名
     * @param starttime 请假开始时间
     * @param endtime 请假结束时间
     * @param limit 跳过几条是数据
     * @param page 获取几条数据
     * @return
     */
    @RequestMapping("/studentGetPersonalAllLeaveMsg")
    @ResponseBody
    public Map<String,Object> studentGetPersonalAllLeaveMsg(String studentname,String starttime,String endtime,
                                                            String limit,String page){
        //过滤条件
        List<LeaveMsg> list = studentService.studentGetPersonalAllLeaveMsg(studentname, starttime, endtime);
        //获取分页数据
        //获取一页显示几条数据
        int pageSize = Integer.parseInt(limit);
        //获取当前页，默认为page
        int pageIndex = (Integer.parseInt(page) - 1)*pageSize;

        //分页查询
        List<LeaveMsg> list1 = studentService.studentGetPersonalAllLeaveMsgPage(studentname,starttime,endtime,pageSize,pageIndex);

        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",list.size());
        map.put("data",list1);
        return map;
    }

    /**
     * 批量删除学生个人请假信息
     * @param leaveids 请假信息id
     * @return
     */
    @RequestMapping(value = "/deletePersonalLeaveMsgBatch",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deletePersonalLeaveMsgBatch(String leaveids) {
        String[] leaveid = leaveids.split(",");
        //获取要删除请假信息的审核状态，如果已经同意或拒绝则只改变学生删除的状态位，
        //如果还处于等待状态，那么删除就应该为取消请假
        for (int i = 0; i < leaveid.length; i++) {
            String state = studentService.viewLeaveMsg(leaveid[i]).getState();
            if ("等待批准".equals(state)) {
                //取消请假
                if (studentService.cancelLeaveMsg(leaveid[i])) {
                    return "删除成功";//实际为取消成功
                } else {
                    return "删除失败";//实际为取消失败
                }
            } else {
                if (studentService.deletePersonalLeaveMsg(leaveid[i])) {
                    return "删除成功";
                } else {
                    return "删除失败";
                }
            }
        }
        return "执行失败";
    }

    /**
     * 删除单条学生个人请假信息
     * @param leaveid 请假信息id
     * @return
     */
    @RequestMapping(value = "/deletePersonalLeaveMsg",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deletePersonalLeaveMsg(String leaveid){
        String state = studentService.viewLeaveMsg(leaveid).getState();
        if ("等待批准".equals(state)) {
            //取消请假
            if (studentService.cancelLeaveMsg(leaveid)) {
                return "删除成功";//实际为取消成功
            } else {
                return "删除失败";//实际为取消失败
            }
        } else {
            if (studentService.deletePersonalLeaveMsg(leaveid)) {
                return "删除成功";
            } else {
                return "删除失败";
            }
        }
    }

    /**
     * 查看请假详细信息，并将根据leaveid获取的请假信息放入session
     * @param leaveid 请假信息id
     * @return
     */
    @RequestMapping("/viewLeaveMsg")
    public String viewLeaveMsg(String leaveid, Model model){
        LeaveMsg leaveMsg = studentService.viewLeaveMsg(leaveid);
        String leaveStudentOfGradename = leaveMsg.getGradename();
        model.addAttribute("leaveMsg",leaveMsg);
        model.addAttribute("leaveStudentOfGradename",leaveStudentOfGradename);
        return "student/viewLeaveMsg";
    }

    /**
     * 跳转至添加新的请假页面
     * @return
     */
    @RequestMapping("/studentAddNewLeave")
    public String studentAddNewLeave(){
        return "student/studentAddNewLeave";
    }

    /**
     * 申请请假
     * @param leaveMsg 请假信息实体类
     * @return
     */
    @RequestMapping(value = "/addLeave",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String addLeave(LeaveMsg leaveMsg){
        if (studentService.addLeave(leaveMsg)){
            return "true";
        }else {
            return "false";
        }
    }

    /**
     * 跳转至请假信息等待批准页面
     * @return
     */
    @RequestMapping("/studentWaitAgree")
    public String studentWaitAgree(){
        return "student/studentWaitAgree";
    }

    /**
     * 获取该生等待批准的请假列表
     * @param studentname 请假学生新明
     * @param starttime 请假开始时间
     * @param endtime 请假结束时间
     * @param limit 跳过几条数据
     * @param page 获取几条数据
     * @return
     */
    @RequestMapping("/studentGetPersonalWaitAgreeLeaveMsg")
    @ResponseBody
    public Map<String,Object> studentGetPersonalWaitAgreeLeaveMsg(String studentname,String starttime,String endtime,
                                                                  String limit,String page){
        //过滤条件
        List<LeaveMsg> list = studentService.studentGetPersonalWaitAgreeLeaveMsg(studentname, starttime, endtime);
        //获取分页数据
        //获取一页显示几条数据
        int pageSize = Integer.parseInt(limit);
        //获取当前页，默认为page
        int pageIndex = (Integer.parseInt(page) - 1)*pageSize;

        //分页查询
        List<LeaveMsg> list1 = studentService.studentGetPersonalWaitAgreeLeaveMsgPage(studentname,starttime,endtime,pageSize,pageIndex);

        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",list.size());
        map.put("data",list1);
        return map;
    }

    /**
     * 批量取消请假
     * @param leaveids 请假信息id
     * @return
     */
    @RequestMapping(value = "/cancelLeaveMsgBatch",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String cancelLeaveMsgBatch(String leaveids){
        String[] leaveid = leaveids.split(",");
        if (studentService.cancelLeaveMsgBatch(leaveid)){
            return "true";
        }else {
            return "false";
        }
    }

    /**
     * 取消请假
     * @param leaveid 请假信息id
     * @return
     */
    @RequestMapping(value = "/cancelLeaveMsg",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String cancelLeaveMsg(String leaveid){
        if (studentService.cancelLeaveMsg(leaveid)){
            return "true";
        }else {
            return "false";
        }
    }

    /**
     * 跳转至已经批准的请假信息页面
     * @return
     */
    @RequestMapping("/studentAlreadyAgree")
    public String studentAlreadyAgree(){
        return "student/studentAlreadyAgree";
    }

    /**
     * 获取该生所有已经同意的请假申请
     * @param studentname 学生姓名
     * @param starttime 请假开始时时间
     * @param endtime 结束时间
     * @param limit 跳过几条数据
     * @param page 获取几条数据
     * @return
     */
    @RequestMapping("/studentGetPersonalAlreadyAgreeLeaveMsg")
    @ResponseBody
    public Map<String,Object> studentGetPersonalAlreadyAgreeLeaveMsg(String studentname,String starttime,String endtime,
                                                                     String limit,String page){
        //过滤条件
        List<LeaveMsg> list = studentService.studentGetPersonalAlreadyAgreeLeaveMsg(studentname, starttime, endtime);
        //获取分页数据
        //获取一页显示几条数据
        int pageSize = Integer.parseInt(limit);
        //获取当前页，默认为page
        int pageIndex = (Integer.parseInt(page) - 1)*pageSize;

        //分页查询
        List<LeaveMsg> list1 = studentService.studentGetPersonalAlreadyAgreeLeaveMsgPage(studentname,starttime,endtime,pageSize,pageIndex);

        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",list.size());
        map.put("data",list1);
        return map;
    }

    /**
     * 跳转至被请假申请被拒绝页面
     * @return
     */
    @RequestMapping("/studentRefuse")
    public String studentRefuse() {
        return "student/studentRefuse";
    }

    /**
     * 获取该生所有被拒绝的请假申请
     * @param studentname 学生姓名
     * @param starttime 开始时间
     * @param endtime 结束时间
     * @param limit 跳过几条数据
     * @param page 获取几条数据
     * @return
     */
    @RequestMapping("/studentGetPersonalRefuseLeaveMsg")
    @ResponseBody
    public Map<String,Object> studentGetPersonalRefuseLeaveMsg(String studentname,String starttime,String endtime,
                                                                     String limit,String page){
        //过滤条件
        List<LeaveMsg> list = studentService.studentGetPersonalRefuseLeaveMsg(studentname, starttime, endtime);
        //获取分页数据
        //获取一页显示几条数据
        int pageSize = Integer.parseInt(limit);
        //获取当前页，默认为page
        int pageIndex = (Integer.parseInt(page) - 1)*pageSize;

        //分页查询
        List<LeaveMsg> list1 = studentService.studentGetPersonalRefuseLeaveMsgPage(studentname,starttime,endtime,pageSize,pageIndex);

        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",list.size());
        map.put("data",list1);
        return map;
    }

    /**
     * 跳转至个人请假率页面
     * @return
     */
    @RequestMapping("/studentLeaveRate")
    public String studentLeaveRate(){
        return "/WEB-INF/view/student/studentUploadImg.jsp";
    }

    /**
     * 跳转至学生家长页面
     * @return
     */
    @RequestMapping("/studentParentList")
    public String studentParentList(){
        return "student/studentParentList";
    }

    /**
     * 跳转至添加家长页面
     * @param studentname 学生姓名
     * @param model
     * @return
     */
    @RequestMapping("/addParentJsp")
    public String addParentJsp(String studentname,Model model){
        model.addAttribute("studentname",studentname);
        return "student/addParent";
    }

    /**
     * 获取家长列表
     * @param studentname 学生姓名
     * @return
     */
    @RequestMapping("/studentParentMsg")
    @ResponseBody
    public Map<String,Object> studentParentMsg(String studentname){
        //过滤条件
        List<Parent> list = studentService.studentParentMsg(studentname);

        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",list.size());
        map.put("data",list);
        return map;
    }

    /**
     * 添加家长信息
     * @param parent 家长信息实体类
     * @return
     */
    @RequestMapping(value = "/addParent",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String addParent(Parent parent,Model model){
        if (studentService.addParent(parent)){
            //获取该学生的所有家长信息
            List<Parent> parentList = leaveSysService.getAllParent(parent.getStudentname());
            model.addAttribute("parentList",parentList);
            return "添加成功";
        }else {
            return "添加失败";
        }
    }

    /**
     * 删除家长信息
     * @param parentid 家长信息id
     * @return
     */
    @RequestMapping(value = "/deleteParent",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteParent(String parentid){
        if (studentService.deleteParent(parentid)){
            return "true";
        }else {
            return "false";
        }
    }

    /**
     * 跳转至修改密码页面
     * @return
     */
    @RequestMapping("/studentChangePwd")
    public String studentChangePwd(){
        return "student/studentChangePwd";
    }

    /**
     * 跳转至上传头像页面
     * @return
     */
    @RequestMapping("/studentUploadImg")
    public String uploadImg(){
        return "student/studentUploadImg";
    }

}
