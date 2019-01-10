<%--
  User: 墨盒
  Date: 2017/9/14
  Time: 21:27
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--<title><%=request.getAttribute("src")%>--%>
    <title>测试
    </title>
</head>
<body  style="text-align: center">
<video style="width: 100%" controls="controls"
<%--src="<%=request.getContextPath()%>s?file=<%=request.getAttribute("src")+(request.getParameter("hidden")==null?"":("&hidden="+request.getParameter("hidden")))%>">--%>
       src="v?path=<%=request.getParameter("path")%>&i=<%=(request.getParameter("i")==null?"0":request.getParameter("i"))%>">
    <%--src="<%=request.getContextPath()+(request.getParameter("hidden")==null?"video":"hidden")%>/<%=request.getAttribute("src")%>">--%>
    <%--src="<%=request.getContextPath()%>/v?file=<%=request.getAttribute("src")%>">--%>
    <%--src="<%=request.getContextPath()%>/vi?file=<%=request.getAttribute("src")%>">--%>
    不支持H5
</video>
</body>
</html>
