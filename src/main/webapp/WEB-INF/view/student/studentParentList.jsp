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
    <title>学生家长列表页面</title>
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

<%
    String studentname = (String) session.getAttribute("学生");
%>
<body>
<div align="center">
    <input type="hidden" id="studentname" value="<%=studentname%>">
    <h1 style="margin: 30px 0px 20px 0px">家长列表</h1>
    <table id="demo" lay-filter="test"></table>
</div>
<script type="text/html" id="toolbarDemo">
    <div align="right">
        <div class="layui-input-inline">
            <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
        </div>
    </div>
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
    layui.use(['table','layer','jquery'], function(){
        var table = layui.table,
            layer = layui.layer,
            $ = layui.jquery,
            studentname = $("#studentname").val();

        //第一个实例
        table.render({
            toolbar: '#toolbarDemo'//添加工具栏
            ,elem: '#demo'
            ,height: 480
            ,width: 610
            ,url: "studentParentMsg?studentname=" + studentname //数据接口
            ,page: false //关闭分页
            ,cols: [[ //表头
                {field: 'parentid', title: '家长编号', width:50, sort: true,hide:true}
                ,{type:'numbers',title:'序号', width:100}
                ,{field: 'parentname', title: '姓名', width: 100}
                ,{field: 'role', title: '角色', width:100}
                ,{field: 'sex', title: '性别', width: 100}
                ,{field: 'phone', title: '联系方式', width:120}
                ,{fixed: 'right', title: '操作', width:80,align: 'left', toolbar: '#barDemo'}
            ]]
        });

        //监听事件,监听lay-filter="test"元素的工具栏
        table.on('toolbar(test)', function(obj){
            switch(obj.event){
                case 'add':
                    layer.open({
                        type: 2  //弹出完整jsp页面，type弹出隐藏div
                        , title: "添加页面"
                        , shadeClose: true  //点击遮罩关闭弹框
                        , content:"addParentJsp?studentname=" +studentname
                        , area: ["480px", "500px"]
                        , end: function () {
                            //刷新当前页
                            $(".layui-laypage-btn").click();
                            setTimeout('window.location.reload()', 10);
                        }
                    });
                    break;
            }
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值

            if(layEvent === 'del'){
                layer.confirm('是否删除该条家长信息', function(index){
                    layer.close(index);
                    //向服务端发送删除指令
                    var parentid = data.parentid;
                    $.ajax({
                        url: "deleteParent"
                        , type: "post"
                        , data: {
                            parentid: parentid
                        }
                        , dataType: "text" //默认text
                        , success: function (data) {
                            obj.del(); //删除对应行（tr）的DOM结构
                            if ("true" === data) {
                                layer.msg("删除成功",function() {
                                    setTimeout('window.location.reload()', 10);
                                })
                            } else {
                                layer.msg("删除失败",function() {
                                    setTimeout('window.location.reload()', 10);
                                });
                            }
                        }
                        , error: function () {
                            layer.msg("执行失败");
                        }
                        ,end:function () {
                            //刷新当前页
                            $(".layui-laypage-btn").click();
                        }
                    });
                });
            }
        });

    });
</script>
</body>
</html>
