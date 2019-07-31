<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019-07-17
  Time: 11:38
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
        <span class="tip">[咨询管理]</span>
        <a href="咨询添加.html" target="mainframe">
            <img src="images/button/tianjia.gif">
        </a>
        <a href="javascript:inputCheck()">
            <img src="images/button/yijiao.gif">
        </a>
        <a href="javascript:inputCheck()">
            <img src="images/button/diudan.gif">
        </a>
    </div>
    <div id="selectFrom">
        <form action="" method="get">
            <table border="0" width="550px" height="40px" style="margin-left:10px">
                <tr>
                    <td>学生姓名:</td>
                    <td>
                        <input name="sename"/>
                    </td>
                    <td>来源:</td>
                    <td>
                        <select name="className" id="classId">
                            <option value="-1">--请选择--</option>
                            <option value="1">网站</option>
                            <option value="2">现场</option>
                            <option value="3">电话</option>
                        </select>
                    </td>
                    <td>咨询师:</td>
                    <td>
                        <select name="className" id="classId">
                            <option value="-1">--请选择--</option>
                            <option value="1">刘老师</option>
                            <option value="2">龚老师</option>
                            <option value="3">黄老师</option>
                        </select>
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
                <th>咨询来源</th>
                <th>咨询师</th>
                <th>状态</th>
                <th>查看</th>
            </tr>
            <tr>
                <td><input type="checkbox"/></td>
                <td>张三</td>
                <td>男</td>
                <td>网站</td>
                <td>黄老师</td>
                <td>咨询中</td>
                <td>
                    <img src="images/button/view.gif"/>
                </td>
            </tr>
            <tr>
                <td><input type="checkbox"/></td>
                <td>李四</td>
                <td>男</td>
                <td>网站</td>
                <td>王老师</td>
                <td>咨询中</td>
                <td>
                    <img src="images/button/view.gif"/>
                </td>
            </tr>
            <tr>
                <td><input type="checkbox"/></td>
                <td>王五</td>
                <td>男</td>
                <td>电话</td>
                <td>黄老师</td>
                <td>咨询中</td>
                <td>
                    <img src="images/button/view.gif"/>
                </td>
            </tr>
            <tr>
                <td><input type="checkbox"/></td>
                <td>马六</td>
                <td>男</td>
                <td>网站</td>
                <td>黄老师</td>
                <td>咨询中</td>
                <td>
                    <img src="images/button/view.gif"/>
                </td>
            </tr>
            <tr>
                <td><input type="checkbox"/></td>
                <td>赵七</td>
                <td>男</td>
                <td>网站</td>
                <td>刘老师</td>
                <td>咨询中</td>
                <td>
                    <img src="images/button/view.gif"/>
                </td>
            </tr>
            <tr>
                <td><input type="checkbox"/></td>
                <td>陆八</td>
                <td>男</td>
                <td>网站</td>
                <td>黄老师</td>
                <td>咨询中</td>
                <td>
                    <img src="images/button/view.gif"/>
                </td>
            </tr>
            <tr>
                <td><input type="checkbox"/></td>
                <td>涂九</td>
                <td>男</td>
                <td>网站</td>
                <td>黄老师</td>
                <td>咨询中</td>
                <td>
                    <img src="images/button/view.gif"/>
                </td>
            </tr>
            <tr>
                <td><input type="checkbox"/></td>
                <td>张三</td>
                <td>男</td>
                <td>网站</td>
                <td>黄老师</td>
                <td>咨询中</td>
                <td>
                    <img src="images/button/view.gif"/>
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
</body>
</html>
