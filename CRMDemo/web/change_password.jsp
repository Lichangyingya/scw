<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019-07-17
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>更改口令</title>
    <link href="css/add.css" type="text/css" rel="stylesheet"/>
</head>

<body>

<div id="topMenu">
    <span class="tip">[更改口令]</span>
    <button class="btn add" onClick="history.go(-1)"/>
    <button class="btn" onClick="formSumit()"/>
</div>
<div id="addFrom">
    <form id="formId" action="http://www.163.com" method="post" >
        <table width="400px" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td width="80px">登录名:</td>
                <td>
                    <input id="loginId" name="loginName" onBlur="loginBlur()"/>
                    <span id="loginTip"></span>
                </td>
            </tr>
            <tr>
                <td>密码：</td>
                <td>
                    <input id="pwdId" name="pwd" onBlur="pwdBlur()"/>
                    <span id="pwdTip"></span>
                </td>
            </tr>
            <tr>
                <td>新密码：</td>
                <td>
                    <input id="newPwdId" name="newPwd" onBlur="newPwdBlur()"/>
                    <span id="newPwdTip"></span>
                </td>
            </tr>

        </table>
    </form>
</div>
<script type="text/javascript">
    function formSumit(){
        var formObj=document.getElementById("formId");
        if(loginBlur() &&pwdBlur() &&newPwdBlur()){
            formObj.submit();//手动提交表单
            return true;
        }
        return false;
    }
    function loginBlur(){
        var lid= document.getElementById("loginId");
        var ltp= document.getElementById("loginTip");
        if(lid.value ==""){
            ltp.innerHTML="请输入登录名！！";
            return false;
        }else{
            ltp.innerHTML="<em style='color:green'>√</em>";
        }
        return true;
    }

    function pwdBlur(){
        var lid= document.getElementById("pwdId");
        var ltp= document.getElementById("pwdTip");
        if(lid.value ==""){
            ltp.innerHTML="请输入密码！！";
            return false;
        }else{
            ltp.innerHTML="<em style='color:green'>√</em>";
        }
        return true;
    }

    function newPwdBlur(){
        var lid= document.getElementById("newPwdId");
        var ltp= document.getElementById("newPwdTip");
        if(lid.value ==""){
            ltp.innerHTML="请输入新密码！！";
            return false;
        }else{
            ltp.innerHTML="<em style='color:green'>√</em>";
        }
        return true;
    }
</script>
</body>
</html>
