<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019-07-17
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>班级管理</title>
    <link href="css/list.css" type="text/css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
    <script type="text/javascript">
        $(function () {
            var url="ajaxGetClasses";
            var arg={"time":new Date()}
            $.getJSON(url,arg,function (data) {
                for (var i = 0; i <data.length ; i++) {
                    $("#classId").append("<option value='" +data[i].state+"'>"+data[i].state+"</option>")
                }
            })
        })
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
        function query() {
            var formObj=document.getElementById("formId");
            formObj.submit();
        }
    </script>
</head>
<body>
<div id="main">
    <div id="topMenu">
        <span class="tip">[班级管理]</span>
        <a href="add_class.jsp" target="mainframe">
            <img src="images/button/tianjia.gif">
        </a>
        <button class="btn" onClick="query()"/>
    </div>
</div>
<div id="selectFrom">
    <form  id="formId" action="selByCondition" method="get">
        <table border="0" width="700px" height="40px" >
            <tr>
                <td>班级状态:</td>
                <td>
                    <select id="classId" name="cstate">
                        <option value="">--请选择--</option>
                    </select>
                </td>
                <td>开班时间:</td>
                <td>
                    <input type="date" name="stratTime"/>
                </td>
                <td>毕业时间:</td>
                <td>
                    <input type="date" name="jobTime"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="dataArea">
    <img src="images/result.gif"/>
    <table class="dataTable" width="100%" border="1" cellspacing="0" cellpadding="0">
        <thead>
        <tr class="hbg">
            <th width="150px" align="center">班级名称</th>
            <th width="200px" align="center">所属课程</th>
            <th width="150px" align="center">开班时间</th>
            <th width="150px" align="center">毕业时间</th>
            <th width="80px" align="center">状态</th>
            <th width="70px" align="center">学生总数</th>
            <th width="70px" align="center">升学数</th>
            <th width="70px" align="center">转班数</th>
            <th width="50px" align="center">编辑</th>
            <th width="50px" align="center">查看</th>
            <!--<th align="center">课表</th>-->
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageInfo.list}" var="classVo">
        <tr>
            <td align="center">${classVo.className} </td>
            <td align="center">${classVo.lessonName} </td>
            <td align="center">${classVo.beginTime} </td>
            <td align="center">${classVo.endTime} </td>
            <td align="center">${classVo.state} </td>
            <td align="center">${classVo.totalCount} </td>
            <td align="center">${classVo.goCount} </td>
            <td align="center">${classVo.leaveCount} </td>
            <td align="center">
                <a href="${pageContext.request.contextPath}/getClass?id=${classVo.id}"><img src="images/button/modify.gif" class="img"></a>
                <a href="${pageContext.request.contextPath}/delClass?id=${classVo.id}"><img src="images/button/delete.gif" class="img"></a>
            </td>
            <td align="center">
                <a href="${pageContext.request.contextPath}/queryClass?id=${classVo.id}">
                    <img src="images/button/view.gif" class="img">
                </a>
            </td>
            <!--
            <td align="center">
                <a href="班级课表上传.html" target="mainframe">上传</a>
                <a href="">下载</a>
            </td>
            -->
        </tr>
        </c:forEach>
    </table>
</div>
<div id="pageDiv">
    <span id="pageSpanId">
       <a href="${pageContext.request.contextPath}/selByCondition?pageNumber=1&&cstate=${sessionScope.cstate}&&stratTime=${sessionScope.stratTime}&&jobTime=${sessionScope.jobTime}">&nbsp;&nbsp;首页</a>
         <c:if test="${pageInfo.pageNumber>1}">
             <a href="${pageContext.request.contextPath}/selByCondition?pageNumber=${pageInfo.pageNumber-1}&&cstate=${sessionScope.cstate}&&stratTime=${sessionScope.stratTime}&&jobTime=${sessionScope.jobTime}">&nbsp;上一页</a>
         </c:if>
         <c:if test="${pageInfo.pageNumber<pageInfo.totalPage}">
             <a href="${pageContext.request.contextPath}/selByCondition?pageNumber=${pageInfo.pageNumber+1}&&cstate=${sessionScope.cstate}&&stratTime=${sessionScope.stratTime}&&jobTime=${sessionScope.jobTime}">&nbsp;下一页</a>
         </c:if>
        <a href="${pageContext.request.contextPath}/selByCondition?pageNumber=${pageInfo.totalPage}&&cstate=${sessionScope.cstate}&&stratTime=${sessionScope.stratTime}&&jobTime=${sessionScope.jobTime}">&nbsp;尾页</a>
        <span>当前${pageInfo.pageNumber}/共${pageInfo.totalPage}页</span>
        <span style="color: #3366FF">共${pageInfo.total}条记录</span>
    </span>
    <input type="text" id="pageGoInputId" size="4"><input type="button" onclick="pageGo()" value="go">
    <span id="page" style="color: #FF0000"></span>
</div>
<div style="text-align: center">
    <span style="color: #FF0000;"><h1>${msg}</h1></span>
</div>
<script type="text/javascript">

</script>
</div>
</body>
</html>

