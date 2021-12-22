
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>幼儿请假系统-登陆</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="../../static/lib/layui-v2.5.5/css/layui.css" media="all">
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        html, body {width: 100%;height: 100%;overflow: hidden}
        body {background: #1E9FFF;}
        body:after {content:'';background-repeat:no-repeat;background-size:cover;-webkit-filter:blur(3px);-moz-filter:blur(3px);-o-filter:blur(3px);-ms-filter:blur(3px);filter:blur(3px);position:absolute;top:0;left:0;right:0;bottom:0;z-index:-1;}
        .layui-container {width: 100%;height: 100%;overflow: hidden}
        .admin-login-background {width:360px;height:300px;position:absolute;left:50%;top:40%;margin-left:-180px;margin-top:-100px;}
        .logo-title {text-align:center;letter-spacing:2px;padding:14px 0;}
        .logo-title h1 {color:#1E9FFF;font-size:25px;font-weight:bold;}
        .login-form {background-color:#fff;border:1px solid #fff;border-radius:3px;padding:14px 20px;box-shadow:0 0 8px #eeeeee;}
        .login-form .layui-form-item {position:relative;}
        .login-form .layui-form-item label {position:absolute;left:1px;top:1px;width:38px;line-height:36px;text-align:center;color:#d2d2d2;}
        .login-form .layui-form-item input {padding-left:36px;}
        .captcha {width:60%;display:inline-block;}
        .captcha-img {display:inline-block;width:34%;float:right;}
        .captcha-img img {height:34px;border:1px solid #e6e6e6;height:36px;width:100%;}
    </style>
</head>
<%--取cookie--%>
<%
    String userid = "";
    String pwd = "";
    //获取当前web应用的所有cookie
    Cookie[] cookies = request.getCookies();

    if (cookies != null){
        //遍历cookies取出用户名，密码
        for (int i = 0; i < cookies.length; i++) {
            //判断当前cookie是不是我们的用户名密码cookie
            if ("useridCookie".equals(cookies[i].getName())){
                userid = cookies[i].getValue();
            }else if ("pwdCookie".equals(cookies[i].getName())){
                pwd = cookies[i].getValue();
            }
        }
    }
%>
<body>
<div class="layui-container">
    <div class="admin-login-background">
        <div class="layui-form login-form">
            <div class="layui-form" action="">
                <div class="layui-form-item logo-title">
                    <h1>幼儿请假管理系统</h1>
                </div>
                <div class="layui-form-item">
                    <%--@declare id="username"--%><label class="layui-icon layui-icon-username" for="username"></label>
                    <input type="text" name="userid" id="userid" lay-verify="required|account" placeholder="账号" autocomplete="off" class="layui-input" value="<%=userid%>">
                </div>
                <div class="layui-form-item">
                    <%--@declare id="password"--%><label class="layui-icon layui-icon-password" for="password"></label>
                    <input type="password" name="pwd" id="pwd" lay-verify="required|password" placeholder="密码" autocomplete="off" class="layui-input" value="<%=pwd%>">
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-password" ></label>
                    <select name="role" id="role" lay-filter="role">
                        <option value="管理员">管理员</option>
                        <option value="教师">教师</option>
                        <option value="学生">学生</option>
                    </select>
                </div>
                <div class="layui-form-item">
                    <table>
                        <tr>
                            <td>
                                <canvas id="canvas" width="100" height="43" onclick="dj()"
                                        style="border: 1px solid #ccc;border-radius: 5px;"></canvas>
                            </td>
                            <td>
                                <input type="text" value=""  placeholder="请输入验证码（区分大小写）" class="layui-input" id ="text">
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="layui-form-item">
                    <input type="checkbox" name="rememberMe" id="rememberMe" value="y" lay-skin="primary" title="记住密码">
                </div>
                <div class="layui-form-item">
                    <button type="button" id="btn1" class="layui-btn layui-btn layui-btn-normal layui-btn-fluid" lay-submit="" lay-filter="login">登 录</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../../static/lib/jquery-3.4.1/jquery-3.4.1.min.js" charset="utf-8"></script>
<script src="../../static/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script src="../../static/lib/jq-module/jquery.particleground.min.js" charset="utf-8"></script>
<script>
    //图片验证码部分
    var show_num = [];
    draw(show_num);
    function dj(){
        draw(show_num);
    }
    function draw(show_num) {
        var canvas_width=document.getElementById('canvas').clientWidth;
        var canvas_height=document.getElementById('canvas').clientHeight;
        var canvas = document.getElementById("canvas");//获取到canvas的对象，演员
        var context = canvas.getContext("2d");//获取到canvas画图的环境，演员表演的舞台
        canvas.width = canvas_width;
        canvas.height = canvas_height;
        var sCode = "A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0,q,w,e,r,t,y,u,i,o,p,a,s,d,f,g,h,j,k,l,z,x,c,v,b,n,m";
        var aCode = sCode.split(",");
        var aLength = aCode.length;//获取到数组的长度

        for (var i = 0; i <= 3; i++) {
            var j = Math.floor(Math.random() * aLength);//获取到随机的索引值
            var deg = Math.random() * 30 * Math.PI / 180;//产生0~30之间的随机弧度
            var txt = aCode[j];//得到随机的一个内容
            show_num[i] = txt;
            var x = 10 + i * 20;//文字在canvas上的x坐标
            var y = 20 + Math.random() * 8;//文字在canvas上的y坐标
            context.font = "bold 23px 微软雅黑";

            context.translate(x, y);
            context.rotate(deg);

            context.fillStyle = randomColor();
            context.fillText(txt, 0, 0);

            context.rotate(-deg);
            context.translate(-x, -y);
        }
        for (var i = 0; i <= 5; i++) { //验证码上显示线条
            context.strokeStyle = randomColor();
            context.beginPath();
            context.moveTo(Math.random() * canvas_width, Math.random() * canvas_height);
            context.lineTo(Math.random() * canvas_width, Math.random() * canvas_height);
            context.stroke();
        }
        for (var i = 0; i <= 30; i++) { //验证码上显示小点
            context.strokeStyle = randomColor();
            context.beginPath();
            var x = Math.random() * canvas_width;
            var y = Math.random() * canvas_height;
            context.moveTo(x, y);
            context.lineTo(x + 1, y + 1);
            context.stroke();
        }
    }
    function randomColor() {//得到随机的颜色值
        var r = Math.floor(Math.random() * 256);
        var g = Math.floor(Math.random() * 256);
        var b = Math.floor(Math.random() * 256);
        return "rgb(" + r + "," + g + "," + b + ")";
    }


    layui.use(['form'], function () {
        var form = layui.form
            ,layer = layui.layer;

        // 登录过期的时候，跳出ifram框架
        if (top.location !== self.location) top.location = self.location;

        // 粒子线条背景
        $(document).ready(function(){
            $('.layui-container').particleground({
                dotColor:'#7ec7fd',
                lineColor:'#7ec7fd'
            });
        });

        //点击登录按钮实现登录
        $("#btn1").click(function () {
            if ($("#userid").val() == "" || $("#pwd").val() == ""){
                $("#tip1").innerHTML = "必填项不能为空";
            }else {
                $("#tip1").innerHTML = "";
                //检查验证码
                var val=document.getElementById("text").value;
                var num = show_num.join("");
                if(val==''){
                    layer.msg('请输入验证码！');
                }else if(val == num){
                    $.ajax({
                        url:"login2"
                        ,type:"post"
                        ,data:{
                            userid:$("#userid").val()
                            ,pwd:$("#pwd").val()
                            ,role:$("#role").val()
                            ,rememberMe:$("rememberMe").val()
                        }
                        ,success:function (data) {
                            if ("管理员" === data){
                                window.location.href = "admin";
                            }else if ("教师" === data) {
                                window.location.href = "teacher";
                            }else if ("学生" === data){
                                window.location.href = "student";
                            }else {
                                layer.msg("账号或密码错误");
                                document.getElementById("text").value='';
                            }
                        }
                        ,error:function (data) {
                            alert("执行失败")
                        }
                    });
                    document.getElementById(".input-val").val('');
                    draw(show_num);
                }else{
                    layer.msg('验证码错误！\n你输入的是:  '+val+"\n正确的是:  "+num+'\n请重新输入！');
                    document.getElementById("text").value='';
                    draw(show_num);
                }
            }
        });
    });
</script>
</body>
</html>
