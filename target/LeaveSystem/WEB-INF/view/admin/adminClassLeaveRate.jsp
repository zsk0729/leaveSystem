<%--
  Created by IntelliJ IDEA.
  User: zheng
  Date: 2020/9/13
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="../../../static/js/jquery-3.3.1.js"></script>
    <link href="../../../static/layui/css/layui.css" rel="stylesheet">
    <script src="../../../static/layui/layui.js"></script>
</head>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<body>

<div style="text-align: center">
    每月学生请假率 <br>
    <br> <a id="a1" href="adminClassLeaveRate.do">获取每月各班级学生请假率</a> <br>
    <br>
    <img id="img1" src="${chartURLLine}" width=1200 height=500 border=0
         color=gray />
</div>
</body>
</html>
