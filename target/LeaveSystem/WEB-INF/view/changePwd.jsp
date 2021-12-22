<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zheng
  Date: 2020/8/21
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码页面</title>
    <script src="../../static/js/jquery-3.3.1.js"></script>

    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../../static/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="../../static/css/public.css" media="all">
    <script src="../../static/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
    <script src="../../static/js/lay-config.js?v=1.0.4" charset="utf-8"></script>

    <style>
        .layui-form-item .layui-input-company {
            width: auto;
            padding-right: 10px;
            line-height: 38px;
        }
        .layui-table-tool-self{
            display: none;
        }
        .layui-icon-ok{
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div align="center">
    <h1 style="margin: 30px 0px 20px 0px">修改密码</h1>
    <table id="demo" lay-filter="test"></table>
</div>

<div align="center">
    <div class="layuimini-container">
        <div class="layuimini-main">
            <div class="layui-form layuimini-form">
                <div class="layui-form-item">
                    <label class="layui-form-label required">用户名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="uname" id="uname" readonly value="${sessionScope.uname}" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label required">旧的密码</label>
                    <div class="layui-input-inline">
                        <input type="password" name="oldpwd" id="oldpwd" lay-verify="required" lay-reqtext="旧的密码不能为空" placeholder="请输入旧的密码"  value="" class="layui-input">
                        <tip>填写自己账号的旧的密码。</tip>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label required">新的密码</label>
                    <div class="layui-input-inline">
                        <input type="password" name="newpwd" id="newpwd" lay-verify="required" lay-reqtext="新的密码不能为空" placeholder="请输入新的密码"  value="" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label required">新的密码</label>
                    <div class="layui-input-inline">
                        <input type="password" name="againpwd" id="againpwd" lay-verify="required" lay-reqtext="新的密码不能为空" placeholder="请输入新的密码"  value="" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button type="submit" class="layui-btn layui-btn-normal" id="change" lay-submit lay-filter="saveBtn">确认保存</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">重置</a>
</script>
<script>
    layui.use(['table','layer','jquery'], function(){
        var table = layui.table;
        var layer = layui.layer;
        var $ = layui.jquery;
        var num1 = 0;
        var num2 = 0;
        var num3 = 0;

        //旧密码框失去焦点
        $("#oldpwd").blur(function () {
            num1 = 0;
            $.ajax({
                url:"pwdAjax" //要请求的后台资源
                ,type:"post"  //Ajax请求类型
                ,data:{  //向服务器发送的数据
                    uname:$("#uname").val()
                    ,pwd: $("#oldpwd").val()
                }
                ,dataType:"text"  //服务器响应（返回）数据的类型
                ,success:function (data) {
                    layer.msg(data);
                    /*$("#oldpwdDiv").text(data);*/
                    if ("密码正确" === data){
                        num1 = 1;
                    }
                }
                ,error:function () {
                    layer.msg("执行失败");
                }
            });
        });

        //判断密码格式是否正确
        $("#newpwd").blur(function () {
            num2 = 0;
            var reg = /^([a-z]|[A-Z])(\d|[a-z]|[A-Z]|_|.){5,9}$/;
            var pwd = $("#newpwd").val();
            if (pwd === ""){
                layer.msg("请输入密码")
            } else if (!reg.test(pwd)){
                layer.msg("密码由字母、数字、下划线组成，字母开头长度为6-10位")
            } else {
                num2 = 1;
            }
        });

        //判断两次输入密码是否相同
        $("#againpwd").blur(function () {
            num3 = 0;
            var reg = /^([a-z]|[A-Z])(\d|[a-z]|[A-Z]|_|.){5,9}$/;
            var repwd = $("#againpwd").val();
            $("#repwdDiv").text("");
            if (repwd === ""){
                layer.msg("请输入重复密码")
            } else if (!reg.test(repwd)){
                layer.msg("两次输入密码不一致")
            } else {
                num3 = 1;
            }
        });

        //点击提交按钮
        $("#change").click(function () {
            if (num1 === 1 && num2 === 1 && num3 === 1){
                $.ajax({
                    url:"changePwd"
                    , type:"post"
                    ,data:{
                        uname:$("#uname").val()
                        ,pwd:$("#newpwd").val()
                    }
                    ,dataType:"text"
                    ,success:function (data) {
                        if ("修改成功" === data){
                            layer.msg("修改成功");
                            setTimeout("closeAdd()",1000)
                        }else {
                            layer.msg("修改失败")
                        }
                    }
                    ,error:function () {
                        layer.msg("执行失败");
                    }
                })
            }else {
                layer.msg("请按要求输入新旧密码")
            }

        });

    });
</script>
</body>
</html>
