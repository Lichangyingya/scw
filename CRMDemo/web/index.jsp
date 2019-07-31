<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019-07-17
  Time: 09:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>CRM首页 V2.0</title>
  <link href="css/index.css" type="text/css" rel="stylesheet"/>
  <script type="text/javascript">
    function showMenu(mid){
      var mObj= document.getElementById(mid);
      var md = mObj.style.display;
      if(md=="none"){
        mObj.style.display="block";
      }else{
        mObj.style.display="none";
      }
    }
  </script>
</head>

<body>
<c:if test="${loginUser == null}">
  <c:redirect url="login.jsp"></c:redirect>
</c:if>
<div id="top">
  <h1>传习教育学员CRM系统</h1>
  <div class="showdate">
    <img src="images/calendar.gif"/>
    <span id="dt">2019年3月4日 星期一</span>
  </div>
  <div class="showTitle">
    <span class="show1"> 欢迎你：杰克 </span>
    <span>
         <img src="images/pwd.gif"/>
         <a href="change_password.jsp" target="mainframe">更改口令</a>
       </span>
    <span>
         <img src="images/login.gif"/>
         <a href="login.jsp">重新登录</a>
       </span>
  </div>
</div>

<div id="left">
  <div class="showMenu">
    <img src="images/folder.gif"/>
    <a href="javascript:void(0)" onClick="showMenu('menu1')">人力资源管理</a>
    <ul id="menu1" style="display:none">
      <li>
        <a href="pageDept" target="mainframe">部门管理</a>
      </li>
      <li>
        <a href="showPost" target="mainframe">职务管理</a>
      </li>
      <li>
        <a href="showStaff" target="mainframe">员工管理</a>
      </li>
    </ul>
  </div>
  <div class="showMenu">
    <img src="images/folder.gif"/>
    <a href="javascript:void(0)" onClick="showMenu('menu2')">教学管理</a>
    <ul id="menu2" style="display:none">
      <li>
        <a href="showClass" target="mainframe">班级管理</a>
      </li>
      <li>
        <a href="showLessons" target="mainframe">课程管理</a>
      </li>
    </ul>
  </div>
  <div class="showMenu">
    <img src="images/folder.gif"/>
    <a href="javascript:void(0)" onClick="showMenu('menu3')">学工管理</a>
    <ul id="menu3" style="display:none">
      <li>
        <a href="${pageContext.request.contextPath}/showStudent" target="mainframe">在校生管理</a>
      </li>
      <li>
        <a href="upgrade" target="mainframe">学生升班转班</a>
      </li>
      <li>
        <a href="lose.jsp" target="mainframe">学生流失管理</a>
      </li>
    </ul>
  </div>
  <div class="showMenu">
    <img src="images/folder.gif"/>
    <a href="javascript:void(0)" onClick="showMenu('menu4')">咨询管理</a>
    <ul id="menu4" style="display:none">
      <li>
        <a href="course_manage.jsp" target="mainframe">咨询学生管理</a>
      </li>
      <li>
        <a href="enroll.jsp" target="mainframe">学生报名管理</a>
      </li>
    </ul>
  </div>
  <div class="showMenu">
    <img src="images/folder.gif"/>
    <a href="javascript:void(0)" onClick="showMenu('menu5')">就业管理</a>
    <ul id="menu5" style="display:none">
      <li>
        <a href="employement.jsp" target="mainframe">学生就业情况</a>
      </li>
      <li>
        <a href="employement_table.jsp" target="mainframe">学生就业报表</a>
      </li>
    </ul>
  </div>

</div>

<div id="main">
  <iframe name="mainframe"
          scrolling="no" src="images/right.jpg" width="100%" height="100%" frameborder="0"/>
</div>
</body>
</html>
