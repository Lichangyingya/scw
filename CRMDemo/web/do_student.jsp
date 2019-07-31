<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019-07-17
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>学生编辑</title>
    <link href="css/add.css" type="text/css" rel="stylesheet"/>
    <script src="js/jquery-1.12.4.js"></script>
</head>
<script type="text/javascript">
    $(function () {
        var url="classNames";
        var arg={"time":new Date()}
        $.getJSON(url,arg,function (data) {
            for (var i = 0; i <data.length ; i++) {
                $("#classId").append("<option value='" +data[i].id+"'>"+data[i].className+"</option>")
            }
        })
    })
    window.onload = function (ev) {
        var sex = "${studentVo.stusex}"
        if (sex == '男') {
            var man = document.getElementById("man")
            man.checked = true;
        }else{
            var woman = document.getElementById("women")
            woman.checked = true;
        }
    }
</script>
<body>
<div id="topMenu">
    <span class="tip">[学生编辑]</span>
    <button class="btn add" onClick="history.go(-1)"/>
    <button class="btn" onClick="formSumit()"/>
</div>
<div id="addFrom">
    <form id="formId" action="${pageContext.request.contextPath}/updateStudent" method="post" >
        <table width="400px" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td width="80px">学生姓名:</td>
                <td>
                    <input name="sid" type="hidden" value="${studentVo.id}" >
                    <input id="userId" name="userName" value="${studentVo.stuname}" onBlur="userNameBlur()"/>
                    <span id="userTip"></span>
                </td>
            </tr>
            <tr>
                <td>学号：</td>
                <td>
                    <input id="stuCodeId" name="code" value="${studentVo.stucode}" onBlur="codeBlur()"/>
                    <span id="codeTip"></span>
                </td>
            </tr>
            <tr>
                <td>性别:</td>
                <td>
                    <input type="radio" id="man" name="sex" value="男">男
                    <input type="radio" id="women" name="sex" value="女">女
                </td>
            </tr>
            <tr>
                <td>所在班级：</td>
                <td>
                    <select id="classId" name="class" onBlur="classBlur()">
                        <option value="">--请选择--</option>
                    </select>
                    <span id="classTip"></span>
                </td>
            </tr>
            <tr>
                <td>开学时间:</td>
                <td>
                    <input id="postTime" type="date" value="2019-02-22" name="PostTime" onBlur="timeBlur()"/>
                    <span id="timeTip"></span>
                </td>
            </tr>
            <tr>
                <td>结业时间:</td>
                <td>
                    <input id="endTime" type="date" value="2019-06-22" name="endTime" onBlur="endBlur()"/>
                    <span id="endTip"></span>
                </td>
            </tr>
            <tr>
                <td>备注:</td>
                <td>
                    <textarea rows="10" cols="40" name="mark"></textarea>
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
    function formSumit(){
        var formObj=document.getElementById("formId");
        if(codeBlur() && userNameBlur() && classBlur() && timeBlur() && endBlur() ){
            formObj.submit();//手动提交表单
            return true;
        }
        return false;
    }

    function codeBlur(){
        var lid= document.getElementById("stuCodeId");
        var ltp= document.getElementById("codeTip");
        if(lid.value ==""){
            ltp.innerHTML="请输入学号！！";
            return false;
        }else{
            ltp.innerHTML="<em style='color:green'>√</em>";
        }
        return true;
    }

    function userNameBlur(){
        var lid= document.getElementById("userId");
        var ltp= document.getElementById("userTip");
        if(lid.value ==""){
            ltp.innerHTML="请输入姓名！！";
            return false;
        }else{
            ltp.innerHTML="<em style='color:green'>√</em>";
        }
        return true;
    }

    function classBlur(){
        var lid= document.getElementById("classId");
        var ltp= document.getElementById("classTip");
        if(lid.value =="-1"){
            ltp.innerHTML="请选择班级！！";
            return false;
        }else{
            ltp.innerHTML="<em style='color:green'>√</em>";
        }
        return true;
    }

    function timeBlur(){
        var lid= document.getElementById("postTime");
        var ltp= document.getElementById("timeTip");
        if(lid.value ==""){
            ltp.innerHTML="请输入开课时间！！";
            return false;
        }else{
            ltp.innerHTML="<em style='color:green'>√</em>";
        }
        return true;
    }
    function endBlur(){
        var lid= document.getElementById("endTime");
        var ltp= document.getElementById("endTip");
        if(lid.value ==""){
            ltp.innerHTML="请输入结课时间！！";
            return false;
        }else{
            ltp.innerHTML="<em style='color:green'>√</em>";
        }
        return true;
    }
</script>
</body>
</html>
