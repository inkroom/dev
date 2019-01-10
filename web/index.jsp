<%--
  Created by IntelliJ IDEA.
  User: 墨盒
  Date: 2017/4/28
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${requestScope.get("title")}
    </title>
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
    <a href="navel?url= ${ requestScope.get("prev")}&content=${requestScope.get("contentId")}&title=${requestScope.get("titleId")}&next=${requestScope.get("nextId")}&prev=${requestScope.get("prevId")}">上一章</a>
    <a href="download?url=${ requestScope.get("next")} &content=${requestScope.get("contentId")}&title=${requestScope.get("titleId")}&next=${requestScope.get("nextId")}&prev=${requestScope.get("prevId")}">下载</a>
    <a href="navel?url=${ requestScope.get("next")} &content=${requestScope.get("contentId")}&title=${requestScope.get("titleId")}&next=${requestScope.get("nextId")}&prev=${requestScope.get("prevId")}">下一章</a>
</div>
</body>
</html>
