<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019-07-17
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>在校生管理</title>
    <link href="css/list.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div id="main">
    <div id="topMenu">
        <span class="tip">[流失管理]</span>
        <a href="javascript:inputCheck()">
            <img src="images/button/liushi.gif">
        </a>
        <a href="javascript:inputCheck()">
            <img src="images/button/genjin.gif">
        </a>
        <button class="btn" onClick="query()"/>
    </div>
    <div id="selectFrom">
        <form action="" method="get">
            <table border="0" width="600px" height="40px" style="margin-left:10px">
                <tr>
                    <td>班级:</td>
                    <td>
                        <select name="className" id="classId">
                            <option value="-1">--请选择班级--</option>
                            <option value="1">Java基础班</option>
                            <option value="2">Java中级班</option>
                            <option value="3">Java高级班</option>
                        </select>
                    </td>
                    <td>状态:</td>
                    <td>
                        <select name="stuState" id="stateId">
                            <option value="-1">--请选择状态--</option>
                            <option value="1">学习中</option>
                            <option value="2">已留级</option>
                            <option value="3">已升班</option>
                            <option value="3">已流失</option>
                        </select>
                    </td>
                    <td>学生姓名:</td>
                    <td>
                        <input name="sename"/>
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
            <tr>
                <td> <input type="checkbox"/></td>
                <td>张三</td>
                <td>男</td>
                <td>2019-01-11</td>
                <td>JavaEE黑马班</td>
                <td>开课中</td>
                <td>
                    <a href="view.html">
                        <img src="images/button/view.gif"/>
                    </a>
                </td>
            </tr>
            <tr>
                <td> <input type="checkbox"/></td>
                <td>李四</td>
                <td>男</td>
                <td>2019-01-11</td>
                <td>JavaEE黑马班</td>
                <td>开课中</td>
                <td>
                    <a href="view.html">
                        <img src="images/button/view.gif"/>
                    </a>
                </td>
            </tr>
            <tr>
                <td> <input type="checkbox"/></td>
                <td>王五</td>
                <td>男</td>
                <td>2019-01-11</td>
                <td>JavaEE黑马班</td>
                <td>开课中</td>
                <td>
                    <a href="view.html">
                        <img src="images/button/view.gif"/>
                    </a>
                </td>
            </tr>
            <tr>
                <td> <input type="checkbox"/></td>
                <td>马六</td>
                <td>男</td>
                <td>2019-01-11</td>
                <td>JavaEE黑马班</td>
                <td>开课中</td>
                <td>
                    <a href="view.html">
                        <img src="images/button/view.gif"/>
                    </a>
                </td>
            </tr>
            <tr>
                <td> <input type="checkbox"/></td>
                <td>赵七</td>
                <td>男</td>
                <td>2019-01-11</td>
                <td>JavaEE黑马班</td>
                <td>开课中</td>
                <td>
                    <a href="view.html">
                        <img src="images/button/view.gif"/>
                    </a>
                </td>
            </tr>
            <tr>
                <td> <input type="checkbox"/></td>
                <td>陆八</td>
                <td>男</td>
                <td>2019-01-11</td>
                <td>JavaEE黑马班</td>
                <td>开课中</td>
                <td>
                    <a href="view.html">
                        <img src="images/button/view.gif"/>
                    </a>
                </td>
            </tr>
            <tr>
                <td> <input type="checkbox"/></td>
                <td>涂九</td>
                <td>男</td>
                <td>2019-01-11</td>
                <td>JavaEE黑马班</td>
                <td>开课中</td>
                <td>
                    <a href="view.html">
                        <img src="images/button/view.gif"/>
                    </a>
                </td>
            </tr>
            <tr>
                <td> <input type="checkbox"/></td>
                <td>张三</td>
                <td>男</td>
                <td>2019-01-11</td>
                <td>JavaEE黑马班</td>
                <td>开课中</td>
                <td>
                    <a href="view.html">
                        <img src="images/button/view.gif"/>
                    </a>
                </td>
            </tr>
        </table>
    </div>
    <div id="pageDiv">
    <span id="pageSpanId">当前1页/共2页,总条数6条。<br>
        <a href="">&nbsp;1&nbsp;</a>
        <a href="">&nbsp;2&nbsp;</a>
        <a href="">下一页</a>
        <a href="">尾页</a>
    </span>
        <input type="text" id="pageGoInputId" size="4"><input type="button" onclick="pageGo()" value="go">
    </div>
    <script type="text/javascript">
        //全选函数
        function setAll() {
            var ck = document.getElementById("ckall");
            var loves = document.getElementsByTagName("input");
            for (var i = 0; i < loves.length; i++) {
                loves[i].checked = ck.checked;;
            }
        }
        function inputCheck(){
            var flag=false;
            var loves = document.getElementsByTagName("input");
            for (var i = 0; i < loves.length; i++) {
                if(loves[i].checked){
                    flag=true;
                }
            }
            if(!flag){
                alert("请至少选择一项！");
            }
        }

    </script>
</div>
</body>
</html>
