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
    <title>账号密码重置页面</title>
    <script src="../../../static/js/jquery-3.3.1.js"></script>
    <link href="../../../static/layui/css/layui.css" rel="stylesheet">
    <script src="../../../static/layui/layui.js"></script>
    <style>
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
    <h1 style="margin: 30px 0px 20px 0px">账号列表</h1>
    <table id="demo" lay-filter="test"></table>
</div>
<script type="text/html" id="toolbarDemo">
    <div align="right">
        <div class="layui-input-inline">
            <input type="text" id="userid" placeholder="请输入账号" class="layui-input">
        </div>
        <div class="layui-input-inline">
            <input type="text" id="uname" placeholder="请输入姓名" class="layui-input">
        </div>
        <div class="layui-input-inline">
            <button class="layui-btn layui-btn-sm" lay-event="query">查询</button>
        </div>
    </div>

</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">重置</a>
</script>
<script>
    layui.use(['table','layer','jquery'], function(){
        var table = layui.table;
        var layer = layui.layer;
        var $ = layui.jquery;

        //第一个实例
        table.render({
            toolbar: '#toolbarDemo'//添加工具栏
            ,elem: '#demo'
            ,height: 500
            ,width: 605
            ,url: "getAllUser" //数据接口
            ,page: true //开启分页
            ,limit:10//每页显示几条数据
            ,limits:[5,10,15,20]//设置每页可以显示几条数据
            ,cols: [[ //表头
                {type:'checkbox'}
                ,{field: 'userid', title: '账号', width:100, sort: true}
                ,{field: 'uname', title: '姓名', width:150}
                ,{field: 'role', title: '角色', width:150}
                ,{fixed: 'right', title: '操作', width:150,align: 'left', toolbar: '#barDemo'}
            ]]
        });

        //监听事件,监听lay-filter="test"元素的工具栏
        table.on('toolbar(test)', function(obj){
            switch(obj.event){
                case 'query':
                    //获取查询条件
                    var uname = $("#uname").val();
                    var userid = $("#userid").val();
                    table.reload("demo",{//demo对应的是table的id
                        where:{uname:uname,userid:userid}//过滤条件
                        ,page:{
                            curr:1
                        }
                    });
                    break;
            }
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值

            if(layEvent === 'edit') {
                var userid = data.userid;
                $.ajax({
                    url:"editUser"
                    ,type:"post"
                    ,data:{
                        userid:userid
                    }
                    ,dataType:"text" //默认text
                    ,success:function (data) {
                        if ("true" === data){
                            layer.msg("重置成功")
                        }else {
                            layer.msg("只能重置非管理员用户密码")
                        }
                    }
                    ,error:function () {
                        layer.msg("执行失败");
                    }
                    , end: function () {
                        //刷新当前页
                        $(".layui-laypage-btn").click();
                    }
                })
            }
        });
    });
</script>
</body>
</html>
