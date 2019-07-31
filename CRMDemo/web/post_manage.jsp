<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019-07-21
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8">
    <title>职务管理</title>
    <link href="css/list.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript">
        <%--var total='${pageInfo.total}';--%>
        // alert(total);
        function pageGo(){
            var total = "${pageInfo.totalPage}";
            var inputValue = document.getElementById('pageGoInputId').value;
            if (inputValue<=total && inputValue>0){
                location.href = '${pageContext.request.contextPath}/showPost?pageNumber=' + inputValue;
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
        <span class="tip">[职务管理]</span>
        <!-- 添加部门 -->
        <a href="showDeps" target="mainframe">
            <img src="images/button/tianjia.gif">
        </a>
    </div>
    <div id="dataArea">
        <img src="images/result.gif"/>
        <table class="dataTable" width="100%" border="1" cellspacing="0" cellpadding="0">
            <tr class="hbg">
                <th>职务ID</th>
                <th>部门名称</th>
                <th>职务名称</th>
                <th>编辑</th>
            </tr>
            <c:forEach items="${pageInfo.list}" var="postVo">
                <tr>
                    <td>${postVo.postId}</td>
                    <td>${postVo.department.depName}</td>
                    <td>${postVo.postName}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/selPost?id=${postVo.postId}"><img src="images/button/modify.gif"/></a>
                        <a href="${pageContext.request.contextPath}/delPost?id=${postVo.postId}">
                            <img src="images/button/delete.gif"/>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div id="pageDiv">
    <span id="pageSpanId">
       <a href="${pageContext.request.contextPath}/showPost?pageNumber=1">&nbsp;&nbsp;首页</a>
         <c:if test="${pageInfo.pageNumber>1}">
             <a href="${pageContext.request.contextPath}/showPost?pageNumber=${pageInfo.pageNumber-1}">&nbsp;上一页</a>
         </c:if>
         <c:if test="${pageInfo.pageNumber<pageInfo.totalPage}">
             <a href="${pageContext.request.contextPath}/showPost?pageNumber=${pageInfo.pageNumber+1}">&nbsp;下一页</a>
         </c:if>
        <a href="${pageContext.request.contextPath}/showPost?pageNumber=${pageInfo.totalPage}">&nbsp;尾页</a>
        <span>当前${pageInfo.pageNumber}/共${pageInfo.totalPage}页</span>
         <span style="color: blue">总记录数${pageInfo.total}</span>
    </span>
            <input type="text" id="pageGoInputId" size="4"><input type="button" onclick="pageGo()" value="go">
            <span id="page" style="color: #FF0000"></span>
        </div>
    </div>
    <div style="text-align: center">
        <span style="color: #FF0000;"><h1>${msg}</h1></span>
    </div>
</div>
</body>
</html>
