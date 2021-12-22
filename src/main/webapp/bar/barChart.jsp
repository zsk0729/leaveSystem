<%@ page import="com.jxd.chart.BarChart" %><%--
  Created by IntelliJ IDEA.
  User: zheng
  Date: 2020/9/12
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String fileName = BarChart.getBarChart(session);
    System.out.println(fileName);
%>

<img src="DisplayChart?filename=<%=fileName%>" width="700px" height="500px" border="0" alt="">
<img src="../static/layui/images/img/123.png" width="700px" height="500px" border="0" alt="">
</body>
</html>
