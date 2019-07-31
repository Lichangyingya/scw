<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019-07-17
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>班级查看</title>
    <link href="css/add.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div id="topMenu">
    <span class="tip">[班级查看]</span>
    <button class="btn add" onClick="history.go(-1)"/>
</div>
<div id="addFrom">
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
                <td>开课时间:</td>
                <td>
                    <span id="startTip">${classVo.beginTime}</span>
                </td>
            </tr>
            <tr>
                <td>结业时间:</td>
                <td>
                    <span id="endTip">${classVo.endTime}</span>
                </td>
            </tr>
            <tr>
                <td>其他说明:</td>
                <td>
                    <textarea rows="10" cols="40" name="mark">${classVo.remark}</textarea>
                </td>
            </tr>
        </table>
</div>
<script type="text/javascript">
    function formSumit(){
        var formObj=document.getElementById("formId");
        if(classBlur() &&courseBlur() &&startBlur() && endBlur()){
            formObj.submit();//手动提交表单
            return true;
        }
        return false;
    }
    function classBlur(){
        var lid= document.getElementById("classId");
        var ltp= document.getElementById("classTip");
        if(lid.value ==""){
            ltp.innerHTML="请输入班级名称！！";
            return false;
        }else{
            ltp.innerHTML="<em style='color:green'>√</em>";
        }
        return true;
    }

    function courseBlur(){
        var lid= document.getElementById("courseId");
        var ltp= document.getElementById("courseTip");
        if(lid.value ==""){
            ltp.innerHTML="请选择课程！！";
            return false;
        }else{
            ltp.innerHTML="<em style='color:green'>√</em>";
        }
        return true;
    }

    function startBlur(){
        var lid= document.getElementById("startTime");
        var ltp= document.getElementById("startTip");
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
