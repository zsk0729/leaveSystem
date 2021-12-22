<%@ page import="java.util.List" %>
<%@ page import="com.jxd.model.Grade" %>
<%@ page import="com.jxd.model.Parent" %>
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
    <title>添加学生</title>
    <script src="../../../static/js/jquery-3.3.1.js"></script>
    <link href="../../../static/layui/css/layui.css" rel="stylesheet">
    <script src="../../../static/layui/layui.js"></script>
</head>
<%
    String uname = (String) session.getAttribute("学生");
    List<Parent> parentList = (List<Parent>) session.getAttribute("parentList");
    String gradename = (String) session.getAttribute("leaveStudentOfGradename");
%>
<body>
<div class="layui-form layui-form-pane" >
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-inline">
                <input type="text" name="studentname" id="studentname" value="<%=uname%>"
                       readonly autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">班级</label>
            <div class="layui-input-inline">
                <input type="text" name="gradename" id="gradename" value="<%=gradename%>"
                       readonly autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">请假时间</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" name="starttime" id="starttime" autocomplete="off"
                       required placeholder="yyyy-MM-dd">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">返校时间</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" name="endtime" id="endtime" autocomplete="off"
                       required placeholder="yyyy-MM-dd">
            </div>
        </div>
        <div class="layui-form-item">

            <label class="layui-form-label">申请家长</label>
            <div class="layui-input-inline">
                <select name="parentname" id="parentname" lay-filter="aihao">
                    <c:forEach var="num1" items="<%=parentList%>">
                        <option value="${num1.parentname}">${num1.parentname} </option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">请假原因</label>
            <div class="layui-input-inline">
                <input type="text" name="reason" id="reason" required lay-verify="reason" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <button type="button" class="layui-btn" lay-submit="" id="addLeave" lay-filter="demo1">提交</button>
        </div>
</div>

<script>
    layui.use(['form', 'layedit', 'laydate', 'jquery', 'layer'], function () {
        var form = layui.form
            , layer = layui.layer
            , layedit = layui.layedit
            , laydate = layui.laydate
            ,$=layui.jquery
            ,num3 = 1;

        //判断时间选择是否符合要求
        $("#endtime").blur(function () {
            var num1 = transdate($("#endtime").val())
                ,num2 = transdate($("#starttime").val());
            num3 = 1;
            if ((num1 - num2) > 432000){
                num3 = 1;
                layer.msg("请假时间不能超过5天");
                $("#endtime").val("")
            }else if ((num1 - num2) < 0){
                num3 = 1;
                layer.msg("返校时间不能早于请假时间");
                $("#endtime").val("")
            }else {
                num3 = 0;
            }
        });

        //点击提交按钮
        $("#addLeave").click(function () {
            if ($("#parentname").val() == null){
                layer.msg("必须选择家长，若没有家长请先添加")
            }else if (num3 === 0) {
                $.ajax({
                    url:"addLeave"
                    , type:"post"
                    ,data:{
                        studentname:$("#studentname").val()
                        ,gradename:$("#gradename").val()
                        ,starttime:$("#starttime").val()
                        ,endtime:$("#endtime").val()
                        ,parentname:$("#parentname").val()
                        ,reason:$("#reason").val()
                    }
                    ,dataType:"text"
                    ,success:function (data) {
                        if ("true" === data){
                            layer.msg("申请成功");
                            setTimeout("closeAdd()",1000)
                        }else {
                            layer.msg("申请失败")
                        }
                    }
                    ,error:function () {
                    }
                })
            }else {
                layer.msg("时间选择错误")
            }
        });

        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //自定义验证规则
        form.verify({
            reason:function (value) {
                if (value.length < 2) {
                    return '原因不得少于2个字';
                }
            }
        });

        //表单取值
        layui.$('#LAY-component-form-getval').on('click', function () {
            var data = form.val('example');
            alert(JSON.stringify(data));
        });

        //日期转时间戳
        function transdate(time){
            var date = new Date();
            date.setFullYear(time.substring(0, 4));
            date.setMonth(time.substring(5, 7) - 1);
            date.setDate(time.substring(8, 10));
            date.setHours(time.substring(11, 13));
            date.setMinutes(time.substring(14, 16));
            date.setSeconds(time.substring(17, 19));
            return Date.parse(date) / 1000;
        }

        laydate.render({
            elem: '#starttime'
            ,type: 'datetime' //默认，可不填
        });

        laydate.render({
            elem: '#endtime'
            ,type: 'datetime'
        });
    });

    var closeAdd = function () {
        parent.location.reload();//刷新父页面
    };
</script>
</body>
</html>
