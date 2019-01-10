<%--
  User: 墨盒
  Date: 2017/5/8
  Time: 15:11
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>
</head>
<body>
<form action="login" method="post">
    <input type="text" value="inkbox" name="account"><br>
    <input type="password" value="inkbox" name="password">
    <input type="submit" value="登陆">
</form>
<script src="${pageContext.request.contextPath}/resource/js/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">
    window.onload=function () {
        document.getElementsByTagName('input')[2].onsubmit=function () {
            if (window.location.href.indexOf("login")>-1){

            }
        }
    }
</script>
</body>
</html>
