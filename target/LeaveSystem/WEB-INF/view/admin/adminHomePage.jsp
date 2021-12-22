<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zheng
  Date: 2020/9/9
  Time: 9:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员页面</title>
    <link href="../../../static/layui/css/layui.css" rel="stylesheet">
    <script src="../../../static/layui/layui.js"></script>
    <style>
        h6{
            left: 30px;
            position: relative;
            background-color: bisque;
            width: 1000px;
            height: 5px;
        }
        #div3{
            top: 10px;
            left: 50px;
            position: absolute;
        }
        .kuangjia{
            display: none;
            width: 1300px;
            height: 600px;
        }
        #div31{
            display: block;
        }
        iframe{
            width: 1300px;
            height: 600px
        }
    </style>
</head>
<body class="layui-layout-body">
<script>
    function showTime() {
        var date = new Date();
        var yy = date.getFullYear();
        var MM = date.getMonth()+1;
        var dd = date.getDate();
        var hh = date.getHours();
        var mm = date.getMinutes();
        var ss = date.getSeconds();
        var ww = date.getDay();
        var div = document.getElementById("currentTime");
        //innerHTML为div内的内容
        div.innerHTML = yy + ":" + MM + ":" + dd + "&nbsp;&nbsp;" + hh + ":" + mm + ":" + ss;
    }
</script>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo"><img src="../../../static/layui/images/img/img2.png" width="200px"></div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item">幼儿请假系统&nbsp;&nbsp;&nbsp;</li>
            <li class="layui-nav-item"><p id="currentTime"></p></li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="#">
                    <img src="../../../${sessionScope.users.uploadfile}" class="layui-nav-img">
                    ${sessionScope.管理员}
                </a>
                <dl class="layui-nav-child">
                    <dd><a id="a38">上传头像</a></dd>
                    <dd><a id="a36" href="changePwdJsp" target="iframe7">修改密码</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="logout">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <div style="margin-left: 50px">
                <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                    <li class="layui-nav-item layui-this">
                        <a id="a31" href="adminGrade" style="font-size: larger" target="iframe1">班级管理</a>
                    </li>
                    <li class="layui-nav-item">
                        <a id="a32" href="adminStudent" style="font-size: larger" target="iframe2">学生管理</a>
                    </li>
                    <li class="layui-nav-item">
                        <a id="a33" href="adminTeacher" style="font-size: larger" target="iframe3">教师管理</a>
                    </li>
                    <li class="layui-nav-item">
                        <a id="a34" href="adminLeaveRate" style="font-size: larger" target="iframe4">学生请假率</a>
                    </li>
                    <li class="layui-nav-item">
                        <a id="a35" href="adminAccountPwd" style="font-size: larger" target="iframe5">账号密码重置</a>
                    </li>
                    <li class="layui-nav-item">
                        <a id="a37" href="logout" style="font-size: larger">退出系统</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!-- 内容主体区域 -->
    <div class="layui-body">
        <div id="div3" style="height: 300px">
            <%--班级管理--%>
            <div class="kuangjia" id="div31">
                <iframe name="iframe1" src="adminGrade"></iframe>
            </div>
            <%--学生管理--%>
            <div class="kuangjia" id="div32">
                <iframe name="iframe2" src="adminStudent"></iframe>
            </div>
            <%--教师管理--%>
            <div class="kuangjia" id="div33">
                <iframe name="iframe3" src="adminTeacher"></iframe>
            </div>
            <%--学生请假率--%>
            <div class="kuangjia" id="div34">
                <iframe name="iframe4" src="adminLeaveRate"></iframe>
            </div>
            <%--账号密码重置--%>
            <div class="kuangjia" id="div35">
                <iframe name="iframe5" src="adminAccountPwd"></iframe>
            </div>
            <%--修改密码--%>
            <div class="kuangjia" id="div36">
                <iframe name="iframe6" src="changePwdJsp"></iframe>
            </div>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © www.LeaveSystem.com - 请假系统
    </div>
</div>
<script>
    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;

    });

    layui.use(['carousel', 'form','table','layer','jquery'], function(){
        var carousel = layui.carousel
            ,form = layui.form
            ,table = layui.table
            ,layer = layui.layer
            ,$ = layui.jquery;

        /*各个div的显示于隐藏*/
        $("#a31").click(function () {
            $("#div31").css('display','block');
            $("#div32").css('display','none');
            $("#div33").css('display','none');
            $("#div34").css('display','none');
            $("#div35").css('display','none');
            $("#div36").css('display','none');
        });
        $("#a32").click(function () {
            $("#div31").css('display','none');
            $("#div32").css('display','block');
            $("#div33").css('display','none');
            $("#div34").css('display','none');
            $("#div35").css('display','none');
            $("#div36").css('display','none');
        });
        $("#a33").click(function () {
            $("#div31").css('display','none');
            $("#div32").css('display','none');
            $("#div33").css('display','block');
            $("#div34").css('display','none');
            $("#div35").css('display','none');
            $("#div36").css('display','none');
        });
        $("#a34").click(function () {
            $("#div31").css('display','none');
            $("#div32").css('display','none');
            $("#div33").css('display','none');
            $("#div34").css('display','block');
            $("#div35").css('display','none');
            $("#div36").css('display','none');
        });
        $("#a35").click(function () {
            $("#div31").css('display','none');
            $("#div32").css('display','none');
            $("#div33").css('display','none');
            $("#div34").css('display','none');
            $("#div35").css('display','block');
            $("#div36").css('display','none');
        });
        $("#a36").click(function () {
            $("#div31").css('display','none');
            $("#div32").css('display','none');
            $("#div33").css('display','none');
            $("#div34").css('display','none');
            $("#div35").css('display','none');
            $("#div36").css('display','block');
        });
        $("#a38").click(function () {
            layer.open({
                type: 2  //弹出完整jsp页面，type弹出隐藏div
                , title: "查看页面"
                , shadeClose: true  //点击遮罩关闭弹框
                , content:"adminUploadImg"
                , area: ["400px","400px"]
                , end: function () {
                    //刷新当前页
                    $(".layui-laypage-btn").click();
                    setTimeout('window.location.reload()', 10);
                }
            });
        });

        //设定各种参数
        var ins3 = carousel.render({
            elem: '#test3'
        });
    });
</script>
</body>
</html>
