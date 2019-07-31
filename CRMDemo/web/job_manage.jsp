<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019-07-17
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>职务管理</title>
    <link href="css/list.css" type="text/css" rel="stylesheet"/>
</head>

<body>
<div id="main">
    <div id="topMenu">
        <span class="tip">[职务管理]</span>
        <!-- 添加部门 -->
        <a href="职务添加.html" target="mainframe">
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
            <tr>
                <td>1</td>
                <td>教学部</td>
                <td align="center">教学总监</td>
                <td>
                    <a href="职务编辑.html"><img src="images/button/modify.gif"/></a>
                    <a href="">
                        <img src="images/button/delete.gif"/>
                    </a>
                </td>
            </tr>
            <tr>
                <td>1</td>
                <td>教学部</td>
                <td align="center">Java讲师</td>
                <td>
                    <a href="职务编辑.html"><img src="images/button/modify.gif"/></a>
                    <a href="">
                        <img src="images/button/delete.gif"/>
                    </a>
                </td>
            </tr>
            <tr>
                <td>2</td>
                <td>人力资源部</td>
                <td>人事经理</td>
                <td>
                    <a href="职务编辑.html"><img src="images/button/modify.gif"/></a>
                    <a href="">
                        <img src="images/button/delete.gif"/>
                    </a>
                </td>
            </tr>
            <tr>
                <td>3</td>
                <td>财务部</td>
                <td>财务总监</td>
                <td>
                    <a href="职务编辑.html"><img src="images/button/modify.gif"/></a>
                    <a href="">
                        <img src="images/button/delete.gif"/>
                    </a>
                </td>
            </tr>
            <tr>
            <tr>
                <td>4</td>
                <td>学工部</td>
                <td>班主任</td>
                <td>
                    <a href="职务编辑.html"><img src="images/button/modify.gif"/></a>
                    <a href="">
                        <img src="images/button/delete.gif"/>
                    </a>
                </td>
            </tr>
            <tr>
                <td>5</td>
                <td>咨询部</td>
                <td>初级咨询师</td>
                <td>
                    <a href="职务编辑.html"><img src="images/button/modify.gif"/></a>
                    <a href="">
                        <img src="images/button/delete.gif"/>
                    </a>
                </td>
            </tr>
        </table>
        <div id="pageDiv">
    <span id="pageSpanId">当前1页/共2页，总条数6条。<br>
        <a href="">&nbsp;1&nbsp;</a>
        <a href="">&nbsp;2&nbsp;</a>
        <a href="">下一页</a>
        <a href="">尾页</a>
    </span>
            <input type="text" id="pageGoInputId" size="4"><input type="button" onclick="pageGo()" value="go">
        </div>
        <script type="text/javascript">
            function pageGo(){
                var inputValue = document.getElementById('pageGoInputId').value;location.href = 'All?pageSize=5&pageNum=' + inputValue;
            }
        </script>
    </div>
</body>
</html>
