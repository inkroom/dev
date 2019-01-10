<%@ page import="java.io.File" %>
<%@ page import="java.util.ArrayList" %><%--
  User: 墨盒
  Date: 2017/9/14
  Time: 21:23
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>视频列表</title>
</head>
<body style="text-align: center">
<ul style="width: 100%;padding-left: 0">
    <% ArrayList<File> files = (ArrayList<File>) request.getAttribute("files");
        for (int i = 0; i < files.size(); i++) {
            File file = files.get(i);
            if (file.isDirectory()) {
    %>
    <li>
        <a href="list?file=<%=request.getAttribute("path")+String.valueOf(i)+"&i="+request.getAttribute("i")%>"><%=file.getName()%>
            <%--<a href="list2?file=<%=request.getAttribute("path")+file.getName()+(request.getParameter("hidden")==null?"":("&hidden="+request.getParameter("hidden")))%>"><%=file.getName()%>--%>
        </a>
    </li>
    <%
    } else {
    %>
    <li>
        <%--<a href="show?path=<%=request.getAttribute("path")+String.valueOf(i)+(request.getParameter("hidden")==null?"":("&hidden="+request.getParameter("hidden")))%>"><%=file.getName()%>--%>
        <a href="show?path=<%=request.getAttribute("path")+String.valueOf(i)+"&i="+(request.getAttribute("i")==null?"0":request.getAttribute("i"))%>"><%=file.getName()%>
        </a>
    </li>
    <%
            }
        }%>
    <button onclick="shutdown()">关机</button>
</ul>
</body>
<script type="text/javascript">
    function shutdown() {
        if (confirm('确认关机？')) {
            location.href = "<%=request.getContextPath()%>/shutdown"
        }
    }
</script>
</html>
