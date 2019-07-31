<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019-07-17
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8">
    <title>CRM登录 最终版</title>
    <style type="text/css">
        #apDiv1 {
            position: absolute;
            left: 50%;
            top: 50%;
            width: 480px;
            height: 240px;
            margin-top:-120px;
            margin-left:-220px; color:#FFF;
        }
        input{ border:1px solid #309; height:26px; border-radius:5px}
        .btn{ width:80px; height:30px; background-color:#CF6; font-size:16px; font-weight:bold}
        .tip{ color:red; font-weight:bold}
        h1{ color:#FFF}
    </style>
    <script type="text/javascript">
        //js代码推荐写在最后（js函数中直接使用了页面中的对象）
        //document.getElementById("uid").value="";
        function userFocus(){
            document.getElementById("uid").value="";
        }
        function userBlur(){
            var uobj= document.getElementById("uid");
            var utipObj = document.getElementById("utip");
            if(uobj.value == ""){
                utipObj.innerHTML="请填写用户名！";
                return false;
            }else{
                utipObj.innerHTML="";
            }
            return true;
        }
        function pwdBlur(){
            var pobj= document.getElementById("pwdid");
            var ptipObj = document.getElementById("ptip");
            if(pobj.value == ""){
                ptipObj.innerHTML="请填写密码！";
                return false;
            }else{
                ptipObj.innerHTML="";
            }
            return true;
        }
        function login(){
            if(userBlur() && pwdBlur()){
                return true;
            }
            return false;
        }
    </script>
</head>

<body background="images/rightbg.jpg">

<div id="apDiv1">
    <form action="staffLogin" onSubmit="return login()">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr height="48px">
                <td width="30%" colspan="2" align="center">
                    <h1>欢迎使用CRM系统</h1>
                </td>
            </tr>
            <tr>
                <td align="right">用户名：</td>
                <td>
                    <input id="uid" name="userName" value="请输入用户名" onFocus="userFocus()" onBlur="userBlur()"/>
                    <span id="utip" class="tip"></span>
                </td>
            </tr>
            <tr height="48px">
                <td align="right">密码：</td>
                <td>
                    <input type="password" name="password" id="pwdid" onBlur="pwdBlur()"/>
                    <span id="ptip" class="tip"></span>
                </td>
            </tr>
            <tr>
                <td align="center" colspan="2">
                    <input class="btn" type="submit" value=" 登 录 "/>
                </td>
            </tr>
        </table>
    </form>
    <div style="text-align: center">
        <span style="color: #FF0000;">${msg}</span>
    </div>
</div>

</body>
</html>
