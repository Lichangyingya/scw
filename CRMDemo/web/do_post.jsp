<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019-07-21
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>职务编辑</title>
    <link href="css/add.css" type="text/css" rel="stylesheet"/>
</head>

<body>

<div id="topMenu">
    <span class="tip">[职务编辑]</span>
    <button class="btn add" onClick="history.go(-1)"/>
    <button class="btn" onClick="formSumit()"/>
</div>
<div id="addFrom">
    <form id="formId" action="updPost" method="post" >
        <table width="400px" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td width="80px">部门名:</td>
                <td>
                    <input type="hidden" name="postId" value="${post.postId}">
                    <select name="depId" id="depId" onBlur="depBlur()">

                        <c:forEach items="${departments}" var="department">
                            <c:if test="${post.depId == department.id}">
                                <option value="${department.id}" selected>${department.depName}</option>
                            </c:if>
                            <option value="${department.id}" name="depId">${department.depName}</option>
                        </c:forEach>
                    </select>
                    <span id="depTip"></span>
                </td>
            </tr>
            <tr>
                <td width="80px">职务名:</td>
                <td>
                    <input id="postId" name="postName" onBlur="postBlur()" value="${post.postName}"/>
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
        if(lid.value ==""){
            ltp.innerHTML="请输入职务名！！";
            return false;
        }else{
            ltp.innerHTML="<em style='color:green'>√</em>";
        }
        return true;
    }
</script>
</body>
</html>
