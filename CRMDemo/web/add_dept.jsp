<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019-07-17
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>部门添加</title>
    <link href="css/add.css" type="text/css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
</head>

<body>

<div id="topMenu">
    <span class="tip">[部门添加]</span>
    <button class="btn add" onClick="history.go(-1)"/>
    <button class="btn" onClick="formSumit()"/>
</div>
<div id="addFrom">
    <form id="formId" action="addDept" method="post" >
        <table width="400px" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td width="80px">部门名:</td>
                <td>
                    <input id="depId" name="depName" onBlur="depBlur()"/>
                    <span id="depTip"></span>
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
        var depName = lid.value
        console.log(depName)
        var args = {"depName":depName,"time":new Date()}
        var url="isExistDepName"
        if(lid.value ==""){
            ltp.innerHTML="请输入部门名！！";
            return false;
        }else{
            $.post(url,args,function (data) {
                if(data=1){
                    $("#depTip").text("该部门已存在!")
                    return false;
                }
            })
            ltp.innerHTML="<em style='color:green'>√</em>";
        }
        return true;
    }
</script>
</body>
</html>
