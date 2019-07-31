<%--
  Created by IntelliJ IDEA.
  User: 李长赢
  Date: 2019-07-25
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8">
    <title>在校生管理</title>
    <link href="css/list.css" type="text/css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
    <script type="text/javascript">
        <%--var total='${pageInfo.total}';--%>
        // alert(total);
        $(function () {
            console.log("hahh")
            var url="classNames";
            var arg={"time":new Date()}
            $.getJSON(url,arg,function (data) {
                for (var i = 0; i <data.length ; i++) {
                    $("#classId").append("<option value='" +data[i].className+"'>"+data[i].className+"</option>")
                }
            })
            console.log("hahh")
        })
        function pageGo(){
            var total = "${pageInfo.totalPage}";
            var inputValue = document.getElementById('pageGoInputId').value;
            if (inputValue<=total && inputValue>0){
                location.href = '${pageContext.request.contextPath}/ShowStudent?pageNumber=' + inputValue;
            }else{
                var page = document.getElementById('page');
                page.innerHTML="最后一页是第"+total+"页，请重新输入";
            }
        }
        function query() {
            $("#formId").submit()
        }
    </script>
</head>
<body>
<div id="main">
    <div id="topMenu">
        <span class="tip">[在校生管理]</span>
        <a href="add_student.jsp" target="mainframe">
            <img src="images/button/tianjia.gif">
        </a>
        <button class="btn"  onClick="query()"/>
    </div>
    <div id="selectFrom">
        <form id="formId" action="selStudentByCondition" method="get">
            <table border="0" width="600px" height="40px" style="margin-left:10px">
                <tr>
                    <td>班级:</td>
                    <td>
                        <select name="className" id="classId">
                            <option value="">请选择班级</option>

                        </select>
                    </td>

                    <td>学生姓名:</td>
                    <td>
                        <input name="stuName"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="dataArea">
        <img src="images/result.gif"/>
        <table class="dataTable" width="100%" border="1" cellspacing="0" cellpadding="0">
            <tr class="hbg">
                <th>编号</th>
                <th>学生姓名</th>
                <th>性别</th>
                <th>入学时间</th>
                <th>所在班级</th>
                <th>状态</th>
                <th>编辑</th>
            </tr>
            <c:forEach items="${pageInfo.list}" var="student">
                <tr>
                    <td>${student.id}</td>
                    <td>${student.stuname}</td>
                    <td>${student.stusex}</td>
                    <td>${student.begintime}</td>
                    <td>${student.className}</td>
                    <td>${student.stustate}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/showUpdate?sid=${student.id}"><img src="images/button/modify.gif" class="img"></a>
                        <a href="${pageContext.request.contextPath}/deleteStudent?sid=${student.id}&&pageNumber=${pageInfo.pageNumber}"><img src="images/button/delete.gif" class="img"></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div id="pageDiv">
    <span id="pageSpanId"><br>
         <a href="${pageContext.request.contextPath}/selStudentByCondition?pageNumber=1&&className=${student.className}&&stuName=${student.stuname}">&nbsp;&nbsp;首页</a>
         <c:if test="${pageInfo.pageNumber>1}">
             <a href="${pageContext.request.contextPath}/selStudentByCondition?pageNumber=${pageInfo.pageNumber-1}&&className=${student.className}&&stuName=${student.stuname}">&nbsp;上一页</a>
         </c:if>
         <c:if test="${pageInfo.pageNumber<pageInfo.totalPage}">
             <a href="${pageContext.request.contextPath}/selStudentByCondition?pageNumber=${pageInfo.pageNumber+1}&&className=${student.className}&&stuName=${student.stuname}">&nbsp;下一页</a>
         </c:if>
        <a href="${pageContext.request.contextPath}/selStudentByCondition?pageNumber=${pageInfo.totalPage}&&className=${student.className}&&stuName=${student.stuname}">&nbsp;尾页</a>
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
