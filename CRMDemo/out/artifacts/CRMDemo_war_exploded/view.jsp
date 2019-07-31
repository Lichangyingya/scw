<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019-07-17
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>查看学生信息</title>
    <link href="css/add.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div id="topMenu">
    <span class="tip">[查看学生信息]</span>
    <button class="btn add" onClick="history.go(-1)"/>
</div>
<div id="addFrom">
    <table width="400px" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td width="80px">学生姓名:</td>
            <td>
                <span id="userTip">${studentVo.stuname}</span>
            </td>
        </tr>
        <tr>
            <td>学号：</td>
            <td>
                <span id="codeTip">${studentVo.stucode}</span>
            </td>
        </tr>
        <tr>
            <td>性别:</td>
            <td>
               <span>${studentVo.stusex}</span>
            </td>
        </tr>
        <tr>
            <td>所在班级：</td>
            <td>
               <span>${studentVo.crmClass.className}</span>
            </td>
        </tr>
        <tr>
            <td>开学时间:</td>
            <td>
                <span id="timeTip">${studentVo.crmClass.beginTime}</span>
            </td>
        </tr>
        <tr>
            <td>结业时间:</td>
            <td>
                <span id="endTip">${studentVo.crmClass.endTime}</span>
            </td>
        </tr>
        <tr>
            <td>备注:</td>
            <td>
               ${studentVo.remark}
            </td>
        </tr>
    </table>
</div>
</body>
</html>
