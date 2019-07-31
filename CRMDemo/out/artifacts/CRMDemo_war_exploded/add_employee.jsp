<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019-07-17
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>员工添加</title>
    <link href="css/add.css" type="text/css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#go").click(function(){
                $("#formId").submit()
                console.log(result)
            })
            $.getJSON("ajaxGetAll",function (data) {
                for (var i = 0; i <data.length ; i++) {
                    $("#depId").append("<option value='" +data[i].id+"'>"+data[i].depName+"</option>")
                }
            })


            $("#depId").change(function(){
                $("#postId option:not(:first)").remove();
                var dep = $(this).val();
                if(dep!=""){
                    var url="ajaxGetByDepId";
                    var args = {"depId":dep,"time":new Date()}
                    $.getJSON(url,args,function (data) {
                        for (var i = 0; i <data.length ; i++) {
                            $("#postId").append("<option value='" +data[i].postId+"'>"+data[i].postName+"</option>")
                        }
                    })
                }
            })

        })
        function formSumit(){
            var formObj=document.getElementById("formId");
            if(loginBlur() &&pwdBlur() &&userNameBlur() && depBlur() &&postBlur() && timeBlur()){
                formObj.submit();//手动提交表单
                return true;
            }
            return false;
        }

        function loginBlur(){
            var lid= document.getElementById("loginId");
            var ltp= document.getElementById("loginTip");
            var loginName = $("#loginId").val();
            var url="isExistLoginName"
            var args = {"loginName":loginName,"time":new Date()}
            if(lid.value ==""){
                ltp.innerHTML="请输入登录名！！";
                return false;
            }
            else{
                $.post(url,args,function (data) {
                    if (data == 1){
                        $("#loginTip").text("该用户名已被注册!")
                        return false
                    }
                })
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

        function depBlur(){
            var lid= document.getElementById("depId");
            var ltp= document.getElementById("depTip");
            if(lid.value =="-1"){
                ltp.innerHTML="请选择部门！！";
                return false;
            }else{
                ltp.innerHTML="<em style='color:green'>√</em>";
            }
            return true;
        }
        function postBlur(){
            var lid= document.getElementById("postId");
            var ltp= document.getElementById("postTip");
            if(lid.value =="-1"){
                ltp.innerHTML="请选择职务！！";
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
                ltp.innerHTML="请选择入职时间！！";
                return false;
            }else{
                ltp.innerHTML="<em style='color:green'>√</em>";
            }
            return true;
        }

    </script>
</head>

<body>

<div id="topMenu">
    <span class="tip">[员工添加]</span>
    <button class="btn add" onClick="history.go(-1)"/>
    <button class="btn" onclick="formSumit()"/>
</div>
<div id="addFrom">
    <form id="formId" action="" method="post" >
        <table width="400px" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td width="80px">登录名:</td>
                <td>
                    <input id="loginId" name="loginName" onblur="loginBlur()"/>
                    <span id="loginTip"></span>
                </td>
            </tr>
            <tr>
                <td>密码：</td>
                <td>
                    <input id="pwdId" name="pwd" onblur="pwdBlur()"/>
                    <span id="pwdTip"></span>
                </td>
            </tr>
            <tr>
                <td>姓名：</td>
                <td>
                    <input id="userId" name="userName"onblur="userNameBlur()"/>
                    <span id="userTip"></span>
                </td>
            </tr>
            <tr>
                <td>性别:</td>
                <td>
                    <input type="radio" name="sex" value="男" checked>男
                    <input type="radio" name="sex" value="女">女
                </td>
            </tr>
            <tr>
                <td>所属部门：</td>
                <td>
                    <select id="depId" name="dep" onblur="depBlur()">
                        <option value="-1">--请选择部门--</option>
                    </select>
                    <span id="depTip"></span>
                </td>
            </tr>
            <tr>
                <td>职务：</td>
                <td>
                    <select id="postId" name="post" onblur="postBlur()">
                        <option value="">--请选择职务--</option>
                    </select>
                    <span id="postTip"></span>
                </td>
            </tr>
            <tr>
                <td>入职时间:</td>
                <td>
                    <input id="postTime" type="date" name="PostTime" onblur="timeBlur()"/>
                    <span id="timeTip"></span>
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">

</script>
</body>
</html>
