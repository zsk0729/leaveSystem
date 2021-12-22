<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zheng
  Date: 2020/9/9
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>班级列表页面</title>
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
    <h1 style="margin: 30px 0px 20px 0px">班级列表</h1>
    <table id="demo" lay-filter="test"></table>
    <c:if test="${not empty requestScope.delMsg}">
        <p>${requestScope.delMsg}</p>
    </c:if>
</div>
<%--头部监听工具栏--%>
<script type="text/html" id="toolbarDemo">
    <div align="right">
        <div class="layui-input-inline">
            <input type="text" id="gradename" placeholder="请输入班级名称" class="layui-input">
        </div>
        <div class="layui-input-inline">
            <button class="layui-btn layui-btn-sm" lay-event="query">查询</button>
            <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
            <button class="layui-btn layui-btn-sm" lay-event="delete">删除</button>
        </div>
    </div>
</script>

<%--行监听工具栏--%>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">查看</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
    layui.use(['table','layer','jquery'], function(){
        var table = layui.table;
        var layer = layui.layer;
        var $ = layui.jquery;

        //数据表格实例
        table.render({
            toolbar: '#toolbarDemo'//添加工具栏
            ,elem: '#demo'
            ,height: 500
            ,width: 580
            ,url: "getAllGrade" //数据接口
            ,page: true //开启分页
            ,limit:9//每页显示几条数据
            ,limits:[5,9,10,15,20]//设置每页可以显示几条数据
            ,cols: [[ //表头
                {type:'checkbox'}
                ,{field: 'gradeid', title: '班级编号', width:50, sort: true,hide:true}
                ,{type:'numbers',title:'班级序号', width:100}
                ,{field: 'gradename', title: '班级名称', width:100}
                ,{field: 'gradeHaveStudent', title: '学生数量', width:100}
                ,{field: 'gradeHaveTeacher', title: '教师数量', width:100}
                ,{fixed: 'right', title: '操作', width:120,align: 'left', toolbar: '#barDemo'}
            ]]
        });

        //监听事件,监听lay-filter="test"元素的工具栏
        table.on('toolbar(test)', function(obj){
            switch(obj.event){
                case 'query':
                    //获取查询条件
                    var gradename = $("#gradename").val();
                    table.reload("demo",{//demo对应的是table的id
                        where:{gradename:gradename}//过滤条件
                        ,page:{
                            curr:1
                        }
                    });
                    break;
                case 'add':
                    layer.open({
                        type:2  //弹出完整jsp页面，type弹出隐藏div
                        ,title:"添加班级"
                        ,shadeClose:true  //点击遮罩关闭弹框
                        ,content:"addGradeJsp"
                        ,area:["400px","400px"]
                    });
                    break;
                case 'delete':
                    //批量删除班级
                    //获取选中行
                    // demo为table的id，即为基础参数 id 对应的值
                    var checkStatus = table.checkStatus('demo');
                    //获取选中行的数据
                    var data = checkStatus.data;
                    if (data.length < 1){
                        layer.msg("请选择要删除的数据")
                    }else {
                        layer.confirm('确定要删除吗','删除班级',function () {
                            //获取班级编号
                            var gradeids = "";
                            for (var i = 0; i < data.length; i++) {
                                gradeids += data[i].gradeid + ",";
                            }
                            gradeids = gradeids.substring(0,gradeids.length-1);
                            $.ajax({
                                url:"deleteGradeBatch"
                                ,type:"post"
                                ,data:{
                                    gradeids:gradeids
                                }
                                ,dataType:"text" //默认text
                                ,success:function (data) {
                                    if ("删除成功" === data){
                                        layer.msg("删除成功");
                                    }else {
                                        layer.msg("有学生在该班级，不能删除")
                                    }
                                    //重新加载表格
                                    table.reload("demo",function () {
                                        url:"getAllGrade"
                                    })
                                }
                                ,error:function () {
                                    layer.msg("有学生在该班级，不能删除");
                                }
                            })
                        });
                    }
                    console.log(checkStatus.data.length); //获取选中行数量，可作为是否有选中行的条件
                    console.log(checkStatus.isAll ); //表格是否全选
                    break;
            }
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值

            if(layEvent === 'del'){
                layer.confirm('真的删除行么', function(index){
                    layer.close(index);
                    //向服务端发送删除指令
                    var gradeid = data.gradeid;
                    $.ajax({
                        url: "deleteGrade"
                        , type: "post"
                        , data: {
                            gradeid: gradeid
                        }
                        , dataType: "text" //默认text
                        , success: function (data) {
                            if ("删除成功" === data) {
                                obj.del(); //删除对应行（tr）的DOM结构
                                layer.msg("删除成功",function() {
                                    setTimeout('window.location.reload()', 10);
                                })
                            } else {
                                layer.msg("有学生正在学习该课程，不能删除",function() {
                                    setTimeout('window.location.reload()', 10);
                                });
                            }
                        }
                        , error: function () {
                            layer.msg("有学生正在学习该课程，不能删除");
                        }
                        ,end:function () {
                            //刷新当前页
                            $(".layui-laypage-btn").click();
                        }
                    });
                });
            } else if(layEvent === 'edit') {
                var gradename = data.gradename;
                $.ajax({
                    url:"editGradeSetGradeid"
                    ,type:"post"
                    ,data:{
                        gradename:gradename
                    }
                    ,dataType:"text" //默认text
                    ,success:function (data) {
                        if ("true" === data){
                            window.location.href = "editGrade";
                        }else {
                            layer.msg("编辑失败")
                        }
                    }
                    ,error:function () {
                        layer.msg("执行失败");
                    }
                })
            }
        });
    });
</script>
</body>
</html>
