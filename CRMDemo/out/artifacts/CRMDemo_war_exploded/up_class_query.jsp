<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019-07-17
  Time: 11:28
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
        $(function () {
            var url="classNames";
            var arg={"time":new Date()}
            $.getJSON(url,arg,function (data) {
                for (var i = 0; i <data.length ; i++) {
                    $("#classId").append("<option value='" +data[i].className+"'>"+data[i].className+"</option>")
                }
            })
            var url2 = "ajaxStudentStatus";
            $.getJSON(url2,arg,function (data) {
                for (var i = 0; i <data.length ; i++) {
                    $("#stateId").append("<option value='" +data[i].stustate+"'>"+data[i].stustate+"</option>")
                }
            })
        })
        function query() {
            $("#formId").submit()
        }
    </script>
</head>
<body>
<div id="main">
    <div id="topMenu">
        <span class="tip">[升班转班管理]</span>
        <a href="javascript:inputCheck()">
            <img src="images/button/shengban.gif">
        </a>
        <a href="javascript:inputCheck()">
            <img src="images/button/liuji.gif">
        </a>
        <button class="btn" onClick="query()"/>
    </div>
    <div id="selectFrom">
        <form  id="formId" action="advancedStudentQuery"  method="get">
            <table border="0" width="600px" height="40px" style="margin-left:10px">
                <tr>
                    <td>班级:</td>
                    <td>
                        <select name="className" id="classId">
                            <option value="">--请选择班级--</option>
                        </select>
                    </td>
                    <td>状态:</td>
                    <td>
                        <select name="stuState" id="stateId">
                            <option value="">--请选择状态--</option>
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
                <th>
                    <input type="checkbox" id="ckall" onClick="setAll()"/>
                </th>
                <th>学生姓名</th>
                <th>性别</th>
                <th>入学时间</th>
                <th>所在班级</th>
                <th>状态</th>
                <th>查看详情</th>
            </tr>
            <c:forEach items="${pageInfo.list}" var="student">
                <tr>
                    <td> <input type="checkbox" name ="changeClass" value="${student.id}"/></td>
                    <td>${student.stuname}</td>
                    <td>${student.stusex}</td>
                    <td>${student.begintime}</td>
                    <td>${student.className}</td>
                    <td>${student.stustate}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/studentMessage?id=${student.id}">
                            <img src="images/button/view.gif"/>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div id="pageDiv">
    <span id="pageSpanId">
         <a href="${pageContext.request.contextPath}/advancedStudentQuery?pageNumber=1&&className=${sessionScope.className}&&stuName=${sessionScope.stuName}&&stuState=${sessionScope.stuState}">&nbsp;&nbsp;首页</a>
         <c:if test="${pageInfo.pageNumber>1}">
             <a href="${pageContext.request.contextPath}/advancedStudentQuery?pageNumber=${pageInfo.pageNumber-1}&&className=${sessionScope.className}&&stuName=${sessionScope.stuName}&&stuState=${sessionScope.stuState}">&nbsp;上一页</a>
         </c:if>
         <c:if test="${pageInfo.pageNumber<pageInfo.totalPage}">
             <a href="${pageContext.request.contextPath}/advancedStudentQuery?pageNumber=${pageInfo.pageNumber+1}&&className=${sessionScope.className}&&stuName=${sessionScope.stuName}&&stuState=${sessionScope.stuState}">&nbsp;下一页</a>
         </c:if>
        <a href="${pageContext.request.contextPath}/advancedStudentQuery?pageNumber=${pageInfo.totalPage}&&className=${sessionScope.className}&&stuName=${sessionScope.stuName}&&stuState=${sessionScope.stuState}">&nbsp;尾页</a>
        <span>当前${pageInfo.pageNumber}/共${pageInfo.totalPage}页</span>
         <span style="color: blue">总记录数${pageInfo.total}</span>
    </span>
        <input type="text" id="pageGoInputId" size="4"><input type="button" onclick="pageGo()" value="go">
        <span id="page" style="color: #FF0000"></span>
    </div>
    <script type="text/javascript">
        //全选函数
        function setAll() {
            var ck = document.getElementById("ckall");
            var loves = document.getElementsByTagName("input");
            for (var i = 0; i < loves.length; i++) {
                loves[i].checked = ck.checked;
            }
        }

        function inputCheck(){
            var flag=false;//状态标志
            var loves = document.getElementsByTagName("input");
            for (var i = 0; i < loves.length; i++) {
                if(loves[i].checked){
                    flag=true;
                    break;
                }
            }
            if(flag==false){// !flag
                alert("请至少选择一项！");
            }else {
                var chk_value =[];//定义一个数组
                $('input[name="changeClass"]:checked').each(function(){
                    chk_value.push($(this).val());
                });
                var s = "";
                for (var i = 0; i <chk_value.length ; i++) {
                    s += chk_value[i]+","
                }

            }
        }
    </script>
</div>
</body>
</html>
