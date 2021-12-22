
<%@ page import="java.util.List" %>
<%@ page import="com.jxd.model.Grade" %>
<%@ page import="com.jxd.model.Teacher" %>
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
    <title>查看班级</title>
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
<c:set var="teacherNumber"></c:set>
<%
    int teacherNumber = (int) session.getAttribute("teacherNumber");
    String editGradename = (String) session.getAttribute("editGradename");
%>
<body>
<div class="layui-form layui-form-pane" >
    <div action="updateGrade">
        <div class="layui-form-item">
            <label class="layui-form-label">班级名称</label>
            <div class="layui-input-inline">
                <input type="text" name="gradename" id="gradename" lay-verify="required" value="<%=editGradename%>"
                       readonly autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-form-item">
                <h3 style="margin: 30px 0px 20px 0px">教师列表</h3>
                <table id="demo" lay-filter="test"></table>
            </div>
        </div>
    </div>
</div>
<%--头部监听工具栏--%>
<script type="text/html" id="toolbarDemo">
    <div align="right">
        <div class="layui-input-inline">
            <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
        </div>
    </div>
</script>
<%--行监听工具栏--%>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script>
    layui.use(['form', 'layedit', 'laydate', 'jquery', 'layer','table'], function () {
        var form = layui.form
            , layer = layui.layer
            , layedit = layui.layedit
            , laydate = layui.laydate
            , $=layui.jquery
            , table = layui.table
            , gradename = $("#gradename").val();

        //数据表格实例
        table.render({
            toolbar: '#toolbarDemo'//添加工具栏
            ,elem: '#demo'
            ,height: 300
            ,width: 560
            ,url: "getAllTeacherAllocated?gradename=" + gradename //数据接口
            ,page: false //开启分页
            ,cols: [[ //表头
                {field: 'teacherid', title: '教师编号', width:50, sort: true,hide:true}
                ,{type:'numbers',title:'教师序号', width:100}
                ,{field: 'teachername', title: '教师姓名', width:100}
                ,{field: 'gradename', title: '班级名称', width:100,hide:true}
                ,{field: 'sex', title: '性别', width:70}
                ,{field: 'birthday', title: '出生日期', width:130}
                ,{field: 'degree', title: '学历', width: 70}
                ,{fixed: 'right', title: '操作', width:80,align: 'left', toolbar: '#barDemo'}
            ]]
        });

        //监听事件,监听lay-filter="test"元素的工具栏
        table.on('toolbar(test)', function(obj){
            var num = <%=teacherNumber%>;
            switch(obj.event){
                case 'add':
                    if (num < 3){
                        //alert("添加教师");
                        layer.open({
                            type:2  //弹出完整jsp页面，type弹出隐藏div
                            ,title:"添加教师"
                            ,shadeClose:true  //点击遮罩关闭弹框
                            ,content:"addTeacherForGradeJsp"
                            ,area:["400px","300px"]
                            , end: function () {
                                //刷新当前页
                                $(".layui-laypage-btn").click();
                                setTimeout('window.location.reload()', 10);
                            }
                        });
                        break;
                    }else {
                        layer.msg("该班级已有三位教师")
                    }
                    break;
            }
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'del'){
                layer.confirm('真的删除该教师嘛么', function(index){
                    layer.close(index);
                    //向服务端发送删除指令
                    var teacherid = data.teacherid;
                    var gradename = data.gradename;
                    var num = <%=teacherNumber%>;
                    if (num === 1){
                        layer.msg("班级必须保留一位教师")
                    }else {
                        obj.del(); //删除对应行（tr）的DOM结构
                        $.ajax({
                            url: "deleteTeacherForGrade"
                            , type: "post"
                            , data: {
                                gradename:gradename
                                ,teacherid: teacherid
                            }
                            , dataType: "text" //默认text
                            , success: function (data) {
                                if ("删除成功" === data) {
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
                    }

                });
            }
        });
        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //自定义验证规则
        form.verify({
            title: function (value) {
                if (value.length < 2) {
                    return '标题至少得2个字符啊';
                }
            }
            , content: function (value) {
                layedit.sync(editIndex);
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
        });
        laydate.render({
            elem: '#grade'
            ,type: 'date' //默认，可不填
        });
    });
    var closeAdd = function () {
        parent.location.reload();//刷新父页面
    };
</script>
</body>
</html>
