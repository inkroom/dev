<%@ page import="cn.inkroom.web.socket.config.KeyConfig" %><%--
  User: 墨盒
  Date: 2017/5/8
  Time: 14:35
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
<ul>
    <c:forEach var="item" items="${requestScope.get('boards')}">
        <li>${item.toString()} <input value="加入" type="button" title="加入棋局" onclick="join(${item.getId()})"/></li>
    </c:forEach>
</ul>
<input value="创建" type="button" title="创建棋局" onclick="create()"/>
<% request.setAttribute("key", KeyConfig.KEY_LOGIN); %>
<c:if test="${sessionScope.get(requestScope.get('key')).getBoardId()!=-1}">
    <input value="去下棋" type="button"
           title="去下棋" onclick="javascript:window.location.href='board';"/>
</c:if>


<script src="${pageContext.request.contextPath}/resource/js/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">
    function join(boardId) {
        $.ajax({
            url: boardId + "/join",
            type: 'get',
            dataType: 'json',
            error: function () {
                alert('error');
            },
            success: function (status) {
//                console.log(status);
                if (status.status) {
           //         window.location.href = "board";
                } else {
                    alert("加入棋局失败！");
                }
            }
        });
    }
    function create() {
        $.ajax({
            url: "create",
            type: 'get',
            dataType: 'json',
            success: function (status) {
                alert(status.message)
            },
            error: function () {
                alert("error");
            }
        });
    }
    //    console.log($('input:eq(0)'));
</script>
</body>
</html>
