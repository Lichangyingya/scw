<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019-07-22
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>员工列表 V1.0</title>
    <link href="css/list.css" type="text/css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
    <script type="text/javascript">
        $(function () {
            $.getJSON("ajaxGetAll",function (data) {
                for (var i = 0; i <data.length ; i++) {
                    $("#depId").append("<option value='" +data[i].id+"'>"+data[i].depName+"</option>")
                }
            })
            $("#depId").change(function(){
                $("#postId option:not(:first)").remove();
                var dep = $(this).val();
                if(dep!=""){
                    var url="ajaxGetByDepId";
                    var args = {"depId":dep,"time":new Date()}
                    $.getJSON(url,args,function (data) {
                        for (var i = 0; i <data.length ; i++) {
                            $("#postId").append("<option value='" +data[i].postId+"'>"+data[i].postName+"</option>")
                        }
                    })
                }
            })
        })
        function pageGo(){
            var total = "${pageInfo.totalPage}";
            var inputValue = document.getElementById('pageGoInputId').value;
            if (inputValue<=total && inputValue>0){
                location.href = '${pageContext.request.contextPath}/showStaff?pageNumber=' + inputValue;
            }else{
                var page = document.getElementById('page');
                page.innerHTML="最后一页是第"+total+"页，请重新输入";
            }
        }
        function query() {
            var formObj=document.getElementById("formId");
            formObj.submit();
        }

    </script>
</head>

<body>
<div id="main">
    <div id="topMenu">
        <span class="tip">[员工管理]</span>
        <a href="add_employee.jsp" target="mainframe">
            <img src="images/button/tianjia.gif">
        </a>
        <button class="btn" onClick="query()"/>
    </div>
    <div id="selectFrom">
        <form id="formId" action="queryStaff" method="get">
            <table border="0" width="600px" height="40px" align="center">
                <tr>
                    <td>部门:</td>
                    <td>
                        <select name="depId" id="depId">
                            <option value="">--请选择部门--</option>
                        </select>
                    </td>

                    <td>职务:</td>
                    <td>
                        <select name="postId" id="postId">
                            <option value="">--请选择职务--</option>
                        </select>
                    </td>

                    <td>姓名:</td>
                    <td>
                        <input name="staffName"/>
                    </td>

                </tr>
            </table>
        </form>
    </div>
    <div id="dataArea">
        <img src="images/result.gif"/>
        <table class="dataTable" width="100%" border="1" cellspacing="0" cellpadding="0">
            <tr class="hbg">
                <th>员工姓名</th>
                <th>性别</th>
                <th>入职时间</th>
                <th>所属部门</th>
                <th>职务</th>
                <th>编辑</th>
            </tr>
            <c:forEach items="${pageInfo.list}" var="staff">
                <tr>
                    <td>${staff.staffName}</td>
                    <td>${staff.gender}</td>
                    <td>${staff.onDutyDate}</td>
                    <td>${staff.depName}</td>
                    <td>${staff.postName}</td>
                    <td>
                        <a href="getStaff?staffId=${staff.staffId}"><img src="images/button/modify.gif"/></a>
                        <a href="delStaff?staffId=${staff.staffId}"><img src="images/button/delete.gif"/></a>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </div>
    <div id="pageDiv">
    <span id="pageSpanId"><br>
         <a href="${pageContext.request.contextPath}/queryStaff?pageNumber=1&&depId=${sessionScope.depId}&&postId=${sessionScope.postId}&&staffName=${sessionScope.staffName}">&nbsp;&nbsp;首页</a>
         <c:if test="${pageInfo.pageNumber>1}">
             <a href="${pageContext.request.contextPath}/queryStaff?pageNumber=${pageInfo.pageNumber-1}&&depId=${sessionScope.depId}&&postId=${sessionScope.postId}&&staffName=${sessionScope.staffName}">&nbsp;上一页</a>
         </c:if>
         <c:if test="${pageInfo.pageNumber<pageInfo.totalPage}">
             <a href="${pageContext.request.contextPath}/queryStaff?pageNumber=${pageInfo.pageNumber+1}&&depId=${sessionScope.depId}&&postId=${sessionScope.postId}&&staffName=${sessionScope.staffName}">&nbsp;下一页</a>
         </c:if>
        <a href="${pageContext.request.contextPath}/queryStaff?pageNumber=${pageInfo.totalPage}&&depId=${sessionScope.depId}&&postId=${sessionScope.postId}&&staffName=${sessionScope.staffName}">&nbsp;尾页</a>
        <span>当前${pageInfo.pageNumber}/共${pageInfo.totalPage}页</span>
        <span style="color: blue">总记录数${pageInfo.total}</span>
    </span>
        <input type="text" id="pageGoInputId" size="4"><input type="button" onclick="pageGo()" value="go">
    </div>
</div>
</body>
</html>

