<%@ page import="java.util.List" %>
<%@ page import="com.jxd.model.Grade" %>
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
    <title>添加家长</title>
    <script src="../../../static/js/jquery-3.3.1.js"></script>
    <link href="../../../static/layui/css/layui.css" rel="stylesheet">
    <script src="../../../static/layui/layui.js"></script>
</head>
<%
    String uname = (String) session.getAttribute("学生");
%>
<body>
    <form id="uploadForm" method="post" enctype="multipart/form-data">
        <input type="file" id="upload" name="upload" style="display: none">
        <h1><a href="javascript:$('#upload').click()">点击上传头像</a></h1>
    </form>
    <script>
        layui.use(['table', 'layer'], function () {
            var table = layui.table;
            var layer = layui.layer;
            $ = layui.$;
            $("#upload").change(function () {
                //获取要上传的数据
                //使用FormData可以实现ajax的文件提交
                var formData = new FormData($("#uploadForm")[0]);
                $.ajax({
                    url:"uploadFile",
                    type:"post",
                    data:formData,
                    cache:false,//是否缓存
                    //contentType必须设定为false，
                    //即告诉服务器从浏览器提交过来的数据采用默认的数据格式
                    contentType:false,
                    //设定为false可避免jQuery对formData的默认处理
                    processData:false,
                    success:function (data) {
                        layer.msg(data);
                        location.reload()
                    },
                    error:function (data) {
                        layer.msg("上传失败")
                    }
                    , end: function () {
                        //刷新当前页
                        $(".layui-nav-img").click();
                    }
                })
            })
        })
    </script>
</body>
</html>
