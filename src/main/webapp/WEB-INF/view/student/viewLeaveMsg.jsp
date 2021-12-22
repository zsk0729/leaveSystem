<%@ page import="java.util.List" %>
<%@ page import="com.jxd.model.Grade" %>
<%@ page import="com.jxd.model.LeaveMsg" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zheng
  Date: 2020/8/24
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>请假详细信息</title>
    <script src="../../../static/js/jquery-3.3.1.js"></script>
    <link href="../../../static/layui/css/layui.css" rel="stylesheet">
    <script src="../../../static/layui/layui.js"></script>
</head>
<body>
<div class="layui-form layui-form-pane" >
    <div class="layui-form-item">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-inline">
            <input type="text" name="studentname" id="studentname" value="${leaveMsg.studentname}"
                   readonly autocomplete="off" class="layui-input">
        </div>
        <label class="layui-form-label">班级</label>
        <div class="layui-input-inline">
            <input type="text" name="gradename" id="gradename" value="${leaveMsg.gradename}"
                   readonly autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">请假时间</label>
        <div class="layui-input-inline">
            <input type="text" name="starttime" id="starttime" value="${leaveMsg.starttime}"
                   readonly autocomplete="off" class="layui-input">
        </div>
        <label class="layui-form-label">返校时间</label>
        <div class="layui-input-inline">
            <input type="text" name="endtime" id="endtime" value="${leaveMsg.endtime}"
                   readonly autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">申请家长</label>
        <div class="layui-input-inline">
            <input type="text" name="parentname" id="parentname" value="${leaveMsg.parentname}"
                   readonly autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">请假原因</label>
        <div class="layui-input-inline">
            <textarea readonly name="reason" id="reason" cols="60" rows="4">${leaveMsg.reason}</textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">审核教师</label>
        <div class="layui-input-inline">
            <input type="text" name="teachername" id="teachername" value="${leaveMsg.teachername}"
                   readonly autocomplete="off" class="layui-input">
        </div>
        <label class="layui-form-label">申请结果</label>
        <div class="layui-input-inline">
            <input type="text" name="state" id="state" value="${leaveMsg.state}"
                   readonly autocomplete="off" class="layui-input">
        </div>
    </div>
</div>
</body>
</html>
