
<%@ page import="java.util.List" %>
<%@ page import="com.jxd.model.Teacher" %>

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
    <title>添加课程</title>
    <script src="../../../static/js/jquery-3.3.1.js"></script>
    <link href="../../../static/layui/css/layui.css" rel="stylesheet">
    <script src="../../../static/layui/layui.js"></script>
</head>
<%
    List<Teacher> list1 = (List<Teacher>) session.getAttribute("unallocatTeacher");
%>
<body>
<div class="layui-form layui-form-pane" >
    <div class="layui-form-item">
        <label class="layui-form-label">班级名称</label>
        <div class="layui-input-inline">
            <input type="text" name="gradename" id="gradename" required lay-verify="gradename" placeholder="请输入"
                   autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">请选择教师</label>
        <div class="layui-input-inline">
            <select name="teacherid" id="teacherid" lay-filter="aihao">
                <c:forEach var="num1" items="<%=list1%>">
                    <option value="${num1.teacherid}">${num1.teachername} </option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <button type="button" class="layui-btn" id="addCourse" lay-filter="demo2">添加</button>
    </div>
</div>

<script>
    layui.use(['form', 'layedit', 'laydate', 'jquery', 'layer'], function () {
        var form = layui.form
            , layer = layui.layer
            , layedit = layui.layedit
            , laydate = layui.laydate
            ,$=layui.jquery
            ,num = 1;

        //判断是否与其余班级重名
        $("#gradename").blur(function () {
            //判断班级名称是否为空
            if ($("#gradename").val() == ""){
                num = 1;
                layer.msg("请输入班级名称")
            }else {
                $.ajax({
                    url:"gradeNameAjax"
                    , type:"post"
                    ,data:{
                        gradename:$("#gradename").val()
                    }
                    ,dataType:"text"
                    ,success:function (data) {
                        if ("true" === data){
                            num = 0;
                            layer.msg("可采用该名称")
                        }else {
                            num = 1;
                            layer.msg("该名称已存在")
                        }
                    }
                })
            }

        });
        //点击提交按钮
        $("#addCourse").click(function () {
            if (num == 0){
                $.ajax({
                    url:"addGrade"
                    , type:"post"
                    ,data:{
                        gradename:$("#gradename").val()
                        ,teacherid:$("#teacherid").val()
                    }
                    ,dataType:"text"
                    ,success:function (data) {
                        if ("添加成功" === data){
                            layer.msg("添加成功");
                            setTimeout("closeAdd()",1000)
                        }else {
                            layer.msg("添加失败")
                        }
                    }
                    ,error:function () {
                        layer.msg("执行失败");
                    }
                })
            }else {
                layer.msg("请按要求输入班级名称")
            }
        });

        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //表单取值
        layui.$('#LAY-component-form-getval').on('click', function () {
            var data = form.val('example');
            alert(JSON.stringify(data));
        });

    });
    var closeAdd = function () {
        parent.location.reload();//刷新父页面
    };
</script>
</body>
</html>
