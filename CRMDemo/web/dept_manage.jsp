<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019-07-17
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8">
    <title>部门管理</title>
    <link href="css/list.css" type="text/css" rel="stylesheet"/>
    <style type="text/css">
        #pageSpanId a{
            text-decoration: none;
        }
    </style>
    <script type="text/javascript">
        function pageGo(){
            var total = "${pageInfo.totalPage}";
            var inputValue = document.getElementById('pageGoInputId').value;
            if (inputValue<=total && inputValue > 0 ){
                location.href = '${pageContext.request.contextPath}/dept?pageNumber='+ inputValue;
            }else{
                var page = document.getElementById('page');
                page.innerHTML="最后一页是第"+total+"页，请重新输入";
            }
        }
    </script>
</head>

<body>
<div id="main">
    <div id="topMenu">
        <span class="tip">[部门管理]</span>
        <!-- 添加部门 -->
        <a href="add_dept.jsp" target="mainframe">
            <img src="images/button/tianjia.gif">
        </a>
    </div>
    <div id="dataArea">
        <img src="images/result.gif"/>
        <table class="dataTable" width="100%" border="1" cellspacing="0" cellpadding="0">
            <tr class="hbg">
                <th>部门ID</th>
                <th>部门名称</th>
                <th>编辑</th>
            </tr>
            <c:forEach items="${pageInfo.list}" var="dept">
                <tr>
                    <td>${dept.id}</td>
                    <td>${dept.depName}</td>
                    <td>
                        <a href="selDept?id=${dept.id}"><img src="images/button/modify.gif"/></a>
                        <a href="delDept?id=${dept.id}">
                            <img src="images/button/delete.gif"/>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div id="pageDiv">
    <span id="pageSpanId"><br>
         <a href="${pageContext.request.contextPath}/dept?pageNumber=1">&nbsp;&nbsp;首页</a>
         <c:if test="${pageInfo.pageNumber>1}">
             <a href="${pageContext.request.contextPath}/dept?pageNumber=${pageInfo.pageNumber-1}">&nbsp;上一页</a>
         </c:if>
         <c:if test="${pageInfo.pageNumber<pageInfo.totalPage}">
             <a href="${pageContext.request.contextPath}/dept?pageNumber=${pageInfo.pageNumber+1}">&nbsp;下一页</a>
         </c:if>
        <a href="${pageContext.request.contextPath}/dept?pageNumber=${pageInfo.totalPage}">&nbsp;尾页</a>
        <span>当前${pageInfo.pageNumber}/共${pageInfo.totalPage}页</span>
    </span>
        <input type="text" id="pageGoInputId" size="4">
        <input type="button" onclick="pageGo()" value="go">
        <span id="page" style="color: #FF0000"></span>
    </div>
    <div style="text-align: center">
        <span style="color: #FF0000;"><h1>${msg}</h1></span>
    </div>
</div>
</body>
</html>
