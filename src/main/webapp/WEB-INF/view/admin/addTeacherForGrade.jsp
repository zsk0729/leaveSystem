<%@ page import="com.jxd.model.Teacher" %>
<%@ page import="java.util.List" %>
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
    <title>添加教师</title>
    <script src="../../../static/js/jquery-3.3.1.js"></script>
    <link href="../../../static/layui/css/layui.css" rel="stylesheet">
    <script src="../../../static/layui/layui.js"></script>
</head>
<%
    List<Teacher> list1 = (List<Teacher>) session.getAttribute("unallocatTeacher");
    String editGradename = (String) session.getAttribute("editGradename");
%>
<body>
<div >
    <form action="addTeacherForGrade">
        <div class="layui-form-item">
            <label class="layui-form-label">班级名称</label>
            <div class="layui-input-inline">
                <input type="text" name="gradename" id="gradename" lay-verify="required" value="<%=editGradename%>"
                       readonly autocomplete="off" class="layui-input">
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
        <button type="submit" class="layui-btn" lay-filter="demo2">提交</button>
    </form>
</div>
</body>
</html>
