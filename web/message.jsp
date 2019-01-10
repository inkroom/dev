<%@ page import="java.io.File" %><%--
  Created by IntelliJ IDEA.
  User: 墨盒
  Date: 2017/4/28
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>提示</title>
    <style type="text/css">
        .content {
            padding: 10px;
            font-size: 22px;
        }

        .content > p {
            text-indent: 2em;
        }

        .page {
            text-align: center;
            font-size: 22px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="content">
    ${requestScope.get("content")}
    <%--<%=request.getAttribute("content")%>--%>
</div>
<div class="page">
    <ul>
        <%
            File files[] = new File(request.getServletContext().getRealPath("/")).listFiles();
            for (File file : files) {
        %>
        <li>
            <a href="<%=file.getName()%>"><%=file.getName()%>
            </a>
        </li>
        <%}%>
    </ul>
</div>
</body>
</html>
