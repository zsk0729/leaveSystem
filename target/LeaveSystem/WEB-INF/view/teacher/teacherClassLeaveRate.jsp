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
</head>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String gradename = (String) session.getAttribute("teacherOfGradename");
%>
<body>

<div style="text-align: center">
    每月学生请假率 <br>
    <br> <a href="teacherClassGetColumnLine.do?gradename=<%=gradename%>">获取每个月学生请假率</a> <br>
    <br>

    <img src="${chartURLLine}" width=1200 height=500 border=0
         color=gray />
</div>
</body>
</html>
