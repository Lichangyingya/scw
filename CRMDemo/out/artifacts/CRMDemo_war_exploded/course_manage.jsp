<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019-07-17
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8">
    <title>课程管理</title>
    <link href="css/list.css" type="text/css" rel="stylesheet"/>
</head>

<body>
<div id="main">
    <div id="topMenu">
        <span class="tip">[课程管理]</span>
        <a href="add_course.jsp" target="mainframe">
            <img src="images/button/tianjia.gif">
        </a>
    </div>

    <div id="dataArea">
        <img src="images/result.gif"/>
        <table class="dataTable" width="100%" border="1" cellspacing="0" cellpadding="0">
            <tr class="hbg">
                <th width="14%">名称</th>
                <th width="33%">简介</th>
                <th width="13%">总学时</th>
                <th width="18%">收费标准</th>
                <th width="11%">编辑</th>
            </tr>
            <c:forEach items="${pageInfo.list}" var="lesson">
                </tr>
                <tr class="tabtd1">
                    <td align="center">${lesson.lessonName}</td>
                    <td align="center">${lesson.remark}</td>
                    <td align="center">${lesson.totalTime} </td>
                    <td align="center">${lesson.lessonCost} .0</td>
                    <td width="11%" align="center">
                        <a href="${pageContext.request.contextPath}/getLesson?id=${lesson.id}"><img src="images/button/modify.gif" class="img"></a>
                        <a href="${pageContext.request.contextPath}/deleteLesson?id=${lesson.id}"><img src="images/button/delete.gif" class="img"></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div id="pageDiv">
    <span id="pageSpanId">
       <a href="${pageContext.request.contextPath}/showLessons?pageNumber=1">&nbsp;&nbsp;首页</a>
         <c:if test="${pageInfo.pageNumber>1}">
             <a href="${pageContext.request.contextPath}/showLessons?pageNumber=${pageInfo.pageNumber-1}">&nbsp;上一页</a>
         </c:if>
         <c:if test="${pageInfo.pageNumber<pageInfo.totalPage}">
             <a href="${pageContext.request.contextPath}/showLessons?pageNumber=${pageInfo.pageNumber+1}">&nbsp;下一页</a>
         </c:if>
        <a href="${pageContext.request.contextPath}/showLessons?pageNumber=${pageInfo.totalPage}">&nbsp;尾页</a>
        <span>当前${pageInfo.pageNumber}/共${pageInfo.totalPage}页</span>
        <span style="color: blue">总记录数${pageInfo.total}</span>
    </span>
        <input type="text" id="pageGoInputId" size="4"><input type="button" onclick="pageGo()" value="go">
        <span id="page" style="color: #FF0000"></span>
    </div>
    <div style="text-align: center">
        <span style="color: #FF0000;"><h1>${msg}</h1></span>
    </div>
    <script type="text/javascript">
        function pageGo(){
            var total = "${pageInfo.totalPage}";
            var inputValue = document.getElementById('pageGoInputId').value;
            if (inputValue<=total && inputValue>0){
                location.href = '${pageContext.request.contextPath}/showLessons?pageNumber=' + inputValue;
            }else{
                var page = document.getElementById('page');
                page.innerHTML="最后一页是第"+total+"页，请重新输入";
            }
        }
    </script>
</div>
</body>
</html>
