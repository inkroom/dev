<%@ page import="cn.inkroom.web.xu.hai.qiong.Controller" %><%--
  Created by IntelliJ IDEA.
  User: 许海群
  Date: 2017/4/17
  Time: 18:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <style type="text/css">
        .top
        {
            height:100px;
            width:1000px;
            background-color: beige;
        }
        p
        {
            display:block;
            background-color: darkblue;
        }
        input{margin-right: 150px}
        input:hover{color: black}

        li{
            display: inline-block;
        }

        .btn{
            display: block;
            background: red;
            padding: 10px;
        }
        .btn:hover{
            background: blue;
        }

    </style>
</head>
<body>
<div class="top">
    <ul>

        <li><a href="#" class="btn">按钮一</a></li>
        <li><a href="#"><input type="button" value="按钮二"/></a></li>
        <li><a href="#"><input type="button" value="按钮三"/></a></li>
        <li><a href="#"><input type="button" value="按钮四"/></a></li>
    </ul>
    <%
        Controller c = new Controller();
        c.show();
       for (int i=0;i<2;i++)
        {
    %>
    <a>12312</a>
    <%
        }


    %>
</div>
</body>>
</html>