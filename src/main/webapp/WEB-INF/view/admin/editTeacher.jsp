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
    <title>编辑教师</title>
    <script src="../../../static/js/jquery-3.3.1.js"></script>
    <link href="../../../static/layui/css/layui.css" rel="stylesheet">
    <script src="../../../static/layui/layui.js"></script>
</head>
<%
    String teacherid = (String) session.getAttribute("teacherid");
%>
<body>
<div class="layui-form layui-form-pane" >
    <form action="updateTeacher">
        <div class="layui-form-item">
            <label class="layui-form-label">教师编号</label>
            <div class="layui-input-inline">
                <input type="text" name="teacherid" id="teacherid" lay-verify="required" value="<%=teacherid%>"
                       readonly class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-inline">
                <input type="text" name="teachername" id="teachername" lay-verify="required" placeholder="请输入"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="radio" name="sex" value="男" title="男">
                <input type="radio" name="sex" value="女" title="女" checked>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">出生日期</label>
            <div class="layui-input-inline">
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" name="birthday" id="birthday"
                           autocomplete="off" placeholder="yyyy-MM-dd">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">入职时间</label>
            <div class="layui-input-inline">
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" name="inschooltime" id="inschooltime"
                           autocomplete="off" placeholder="yyyy-MM-dd">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">学历</label>
            <div class="layui-input-inline">
                <input type="text" name="degree" id="degree" lay-verify="required" placeholder="请输入"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <button type="submit" class="layui-btn" id="updateTeacher" lay-filter="demo2">提交</button>
        </div>
    </form>
</div>

<script>
    layui.use(['form', 'layedit', 'laydate', 'jquery', 'layer'], function () {
        var form = layui.form
            , layer = layui.layer
            , layedit = layui.layedit
            , laydate = layui.laydate
            ,$=layui.jquery
            ,numName = 1
            ,numBirth = 1
            ,numInschool = 1
            ,numDegree = 1;

        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //表单验证
        $("#teachername").blur(function () {
            if ($("#teachername").val() === ""){
                numName = 1;
            }else {
                if (reg.test($("#teachername").val())) {
                    numName = 0;
                } else {
                    numName = 1;
                    layer.msg("名字必须由两位以上汉字组成")
                }
            }
        });
        $("#birthday").blur(function () {
            if ($("#birthday").val() === ""){
                numBirth = 1;
                layer.msg("请输入出生日期")
            }else {
                numBirth = 0;
            }
        });
        $("#inschooltime").blur(function () {
            if ($("#inschooltime").val() === ""){
                numInschool = 1;
                layer.msg("请输入入学时间")
            }else if (Date.parse($("#inschooltime").val()) < Date.parse($("#birthday").val())){
                numInschool = 1;
                layer.msg("入学时间不能早与出生时间")
            }else {
                numInschool = 0;
            }
        });
        $("#degree").blur(function () {
            if ($("#degree").val() === ""){
                numDegree = 1;
                layer.msg("请输入学历信息")
            }else {
                numDegree = 0;
            }
        });

        //表单取值
        layui.$('#LAY-component-form-getval').on('click', function () {
            var data = form.val('example');
            alert(JSON.stringify(data));
        });

        laydate.render({
            elem: '#birthday'
            ,type: 'date' //默认，可不填
            ,trigger: 'click'
        });
        laydate.render({
            elem: '#inschooltime'
            ,type: 'date' //默认，可不填
            ,trigger: 'click'
        });
    });
    var closeAdd = function () {
        parent.location.reload();//刷新父页面
    };
</script>
</body>
</html>
