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
    <title>请假信息等待批准列表页面</title>
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
    <h1 style="margin: 30px 0px 20px 0px">请假信息列表</h1>
    <table id="demo" lay-filter="test"></table>
</div>
<script type="text/html" id="toolbarDemo">
    <div align="right">
        <div class="layui-input-inline">
            <input type="text" id="starttime" placeholder="请输入开始时间" class="layui-input">
        </div>
        <div class="layui-input-inline">
            <input type="text" id="endtime" placeholder="请输入结束时间" class="layui-input">
        </div>
        <div class="layui-input-inline">
            <button class="layui-btn layui-btn-sm" lay-event="query">查询</button>
            <button class="layui-btn layui-btn-sm" lay-event="cancel">取消</button>
        </div>
    </div>

</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="view">查看</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="cancel">取消</a>
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
            ,width: 1100
            ,url: "studentGetPersonalWaitAgreeLeaveMsg?studentname=" + studentname //数据接口
            ,page: true //开启分页
            ,limit:8//每页显示几条数据
            ,limits:[5,8,10,15,20]//设置每页可以显示几条数据
            ,cols: [[ //表头
                {type:'checkbox'}
                ,{field: 'leaveid', title: '请假信息编号', width:50, sort: true,hide:true}
                ,{type:'numbers',title:'序号', width:100}
                ,{field: 'reason', title: '请假原因', width:100}
                ,{field: 'starttime', title: '开始时间', width:170}
                ,{field: 'endtime', title: '结束时间', width:170}
                ,{field: 'parentname', title: '请假家长', width: 100}
                ,{field: 'state', title: '状态', width: 100}
                ,{fixed: 'right', title: '操作', width:150,align: 'left', toolbar: '#barDemo'}
            ]]
        });

        //监听事件,监听lay-filter="test"元素的工具栏
        table.on('toolbar(test)', function(obj){
            switch(obj.event){
                case 'query':
                    //获取查询条件
                    var starttime = $("#starttime").val();
                    var endtime = $("#endtime").val();
                    table.reload("demo",{//demo对应的是table的id
                        where:{starttime:starttime,endtime:endtime}//过滤条件
                        ,page:{
                            curr:1
                        }
                    });
                    break;
                case 'cancel':
                    //获取选中行
                    // demo为table的id，即为基础参数 id 对应的值
                    var checkStatus = table.checkStatus('demo');
                    //获取选中行的数据
                    var data = checkStatus.data;
                    if (data.length < 1){
                        layer.msg("请选择要取消的请假申请")
                    }else {
                        layer.confirm('确定要取消吗','取消请假申请',function () {
                            //获取请假信息编号
                            var leaveids = "";
                            for (var i = 0; i < data.length; i++) {
                                leaveids += data[i].leaveid + ",";
                            }
                            leaveids = leaveids.substring(0,leaveids.length-1);
                            $.ajax({
                                url:"cancelLeaveMsgBatch"
                                ,type:"post"
                                ,data:{
                                    leaveids:leaveids
                                }
                                ,dataType:"text" //默认text
                                ,success:function (data) {
                                    if ("true" === data){
                                        layer.msg("取消成功");
                                    }else {
                                        layer.msg("取消失败")
                                    }
                                    //重新加载表格
                                    table.reload("demo",function () {
                                        url:"studentGetPersonalWaitAgreeLeaveMsg"
                                    })
                                }
                                ,error:function () {
                                    layer.msg("执行失败");
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

            if(layEvent === 'cancel'){
                layer.confirm('是否取消该条请假申请', function(index){
                    layer.close(index);
                    //向服务端发送删除指令
                    var leaveid = data.leaveid;
                    $.ajax({
                        url: "cancelLeaveMsg"
                        , type: "post"
                        , data: {
                            leaveid: leaveid
                        }
                        , dataType: "text" //默认text
                        , success: function (data) {
                            obj.del(); //删除对应行（tr）的DOM结构
                            if ("true" === data) {
                                layer.msg("取消成功",function() {
                                    setTimeout('window.location.reload()', 10);
                                })
                            } else {
                                layer.msg("取消失败",function() {
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
            } else if(layEvent === 'view') {
                var leaveid = data.leaveid;
                layer.open({
                    type: 2  //弹出完整jsp页面，type弹出隐藏div
                    , title: "查看页面"
                    , shadeClose: true  //点击遮罩关闭弹框
                    , content:"viewLeaveMsg?leaveid=" +leaveid
                    , area: ["625px","500px"]
                    , end: function () {
                        //刷新当前页
                        $(".layui-laypage-btn").click();
                    }
                });
            }
        });

    });
</script>
</body>
</html>
