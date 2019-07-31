<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019-07-17
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>班级课表上传</title>
    <link href="css/add.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div id="topMenu">
    <span class="tip">[班级课表上传]</span>
    <button class="btn add" onClick="history.go(-1)"/>
    <button class="btn" onClick="formSumit()"/>
</div>
<div id="addFrom">
    <form id="formId" action="uploadCourse" method="post" name="upload" enctype="multipart/form-data">
        <table width="500px" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td width="80px">班级名称:</td>
                <td>
                    <span id="classTip">${classVo.className}</span>
                </td>
            </tr>
            <tr>
                <td>所上课程：</td>
                <td>
                    <span id="courseTip">${classVo.lessonName}</span>
                </td>
            </tr>
            <tr>
                <td>选择课表:</td>
                <td>

                    <input id="courseFileId" type="file" name="courseFile"/>
                    <input type="hidden" name="id" value="${classVo.id}">
                    <span id="courseFileTip"></span>
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
    function formSumit(){
        var formObj=document.getElementById("formId");
        var lid= document.getElementById("courseFileId");
        var ltp= document.getElementById("courseFileTip");
        if(lid.value ==""){
            ltp.innerHTML="请选择课表文件！！";
        }else{
            ltp.innerHTML="<em style='color:green'>√</em>";
            formObj.submit();//手动提交表单
        }
    }
</script>
</body>
</html>
