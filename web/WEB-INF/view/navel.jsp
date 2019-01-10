<%@ page import="java.net.URLEncoder" %><%--
  Created by IntelliJ IDEA.
  User: inkbox
  Date: 18-2-21
  Time: 下午7:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${title}</title>
</head>
<body style="background: #C7EDCC">

    <div class="content">${content}</div>
    <div class="edit" style="text-align: center">
        <a href="read?title=<%=URLEncoder.encode(request.getParameter("title"),"UTF-8") %>&prev=<%=URLEncoder.encode(request.getParameter("prev"),"UTF-8") %>&next=<%=URLEncoder.encode(request.getParameter("next"),"UTF-8") %>&content=<%=URLEncoder.encode(request.getParameter("content"),"UTF-8") %>&url=${pUrl}">上一章</a>
        <a href="read?title=<%=URLEncoder.encode(request.getParameter("title"),"UTF-8") %>&prev=<%=URLEncoder.encode(request.getParameter("prev"),"UTF-8") %>&next=<%=URLEncoder.encode(request.getParameter("next"),"UTF-8") %>&content=<%=URLEncoder.encode(request.getParameter("content"),"UTF-8") %>&url=${nUrl}">下一章</a>
    </div>
</body>
</html>
