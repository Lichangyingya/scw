
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019-07-21
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>职务添加</title>
    <link href="css/add.css" type="text/css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
</head>

<body>

<div id="topMenu">
    <span class="tip">[职务添加]</span>
    <button class="btn add" onClick="history.go(-1)"/>
    <button class="btn" onClick="formSumit()"/>
</div>
<div id="addFrom">
    <form id="formId" action="addPost" method="post" >
        <table width="400px" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td width="80px">部门名:</td>
                <td>
                    <select name="depId" id="depId" onBlur="depBlur()">
                        <c:forEach items="${departments}" var="department">
                            <option value="${department.id}" >${department.depName}</option>
                        </c:forEach>
                    </select>
                    <span id="depTip"></span>
                </td>
            </tr>
            <tr>
                <td width="80px">职务名:</td>
                <td>
                    <input id="postId" name="postName" onBlur="postBlur()"/>
                    <span id="postTip"></span>
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
    function formSumit(){
        var formObj=document.getElementById("formId");
        if(depBlur()){
            formObj.submit();//手动提交表单
            return true;
        }
        return false;
    }

    function depBlur(){
        var lid= document.getElementById("depId");
        var ltp= document.getElementById("depTip");
        if(lid.value ==""){
            ltp.innerHTML="请选择部门名！！";
            return false;
        }else{
            ltp.innerHTML="<em style='color:green'>√</em>";
        }
        return true;
    }

    function postBlur(){
        var lid= document.getElementById("postId");
        var ltp= document.getElementById("postTip");
        var depId = $("#depId").val()
        var postName = $("#postId").val()
        var args = {"depId":depId,"postName":postName,"time":new Date()}
        var url="isExistPost"
        if(lid.value ==""){
            ltp.innerHTML="请输入职务名！！";
            return false;
        }else{
            $.post(url,args,function (data) {
                if(data==1){
                    $("#postTip").text("该职位已存在")
                    return false
                }
            })
            ltp.innerHTML="<em style='color:green'>√</em>";
        }
        return true;
    }
</script>
</body>
</html>
