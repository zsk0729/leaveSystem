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
    String studentid = (String) session.getAttribute("studentid");
%>

<body>
<div class="layui-form layui-form-pane" >
    <form class="layui-form" action="addParent" method="post">
        <input type="hidden" id="studentid" name="studentid" value="<%=studentid%>">
        <div class="layui-form-item">
            <label class="layui-form-label">学生姓名</label>
            <div class="layui-input-inline">
                <input type="text" name="studentname" id="studentname" lay-verify="required"
                       value="${studentname}" readonly class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">家长姓名</label>
            <div class="layui-input-inline">
                <input type="text" name="parentname" id="parentname" lay-verify="parentname" placeholder="请输入"
                       required autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">角色</label>
            <div class="layui-input-inline">
                <select name="role" id="role" lay-filter="role">
                    <option value="爸爸">爸爸</option>
                    <option value="妈妈">妈妈</option>
                    <option value="爷爷">爷爷</option>
                    <option value="奶奶">奶奶</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="radio" name="sex" value="男" title="男" checked>
                <input type="radio" name="sex" value="女" title="女">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">联系方式</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" name="phone" id="phone" autocomplete="off"
                       lay-verify="phone" placeholder="请输入手机号" required >
            </div>
        </div>
        <div class="layui-form-item">
            <button type="submit" id="addParent" class="layui-btn" lay-submit="" lay-filter="demo1">添加</button>
        </div>
    </form>

</div>

<script>
    layui.use(['form', 'layedit', 'laydate', 'jquery', 'layer'], function () {
        var form = layui.form
            , layer = layui.layer
            , layedit = layui.layedit
            , laydate = layui.laydate
            ,$=layui.jquery;

        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //自定义验证规则
        form.verify({
            phone: [
                    /^1[0-9]{10}$/
                    ,'联系方式输入格式不正确'
                ]
            ,parentname:function (value) {
                if (value.length < 2) {
                    return '名字不得少于两个字';
                }
            }
        });

        //表单验证
        $("#parentname").blur(function () {
            if ($("#parentname").val().length < 2){
                layer.msg("名字不得少于两个字");
            }
        });
        $("#phone").blur(function () {
            var phones = $("#phone").val();
            var reg1 = /^1[0-9]{10}$/;
            var reg2 = /^0\d{2,3}-\d{7,8}$/;
            if (reg1.test(phones)){
            }else if (reg2.test(phones)){
            }else {
                layer.msg("联系方式输入格式不正确")
            }
        });

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
