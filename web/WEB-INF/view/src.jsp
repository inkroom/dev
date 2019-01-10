<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: inkbox
  Date: 18-2-28
  Time: 下午1:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>源码</title>
</head>
<body style="background: #C7EDCC" id="src">
<%
    request.setAttribute("vEnter", "\n");
    //奇怪的是这一行我用 <c:set var="vEnter" value="\n" scope="request"/>
    //都没用,估计是set标签里对value值处理了一下,没jstl的源码,不清楚JSTL是怎么处理的.
%>
<%--${fn:replace(, , )}--%>
<c:out value="${fn:replace(src,vEnter,'<br>')}" default="系统错误"/>
<script>
    document.getElementById('src').innerHTML = document.getElementById('src').innerHTML.replace(new RegExp("&lt;br&gt;","gm"), '<br/>');
</script>
</body>
</html>
