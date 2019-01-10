<%@ page import="cn.inkroom.web.socket.config.KeyConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <% request.setAttribute("key", KeyConfig.KEY_LOGIN);%>
    <title>五子棋——${sessionScope.get(requestScope.get("key")).getAccount()}</title>
    <style type="text/css">
        canvas {
            display: block; /*设置展示样式和块级元素*/
            margin: 50px auto; /*上下50px的边距，自适应在此代表左右居中*/
            box-shadow: -2px -2px 2px #EFEFEF, 5px 5px 5px #B9B9B9; /*设置阴影*/
        }
        .show{
            text-align: center;
            /*position: fixed;*/
            /*left: 100px;*/
            /*top: 30px;*/
        }
        .show>span{
            /*display: block;*/
            margin: 8px;
        }
        .show>span>span{
            font-style: oblique;
        }
    </style>
</head>
<body>
<div class="show">
    <span>自己颜色：<span>黑色</span></span>
    <span>对方颜色：<span>白色</span></span>
    <span>当前落子：<span style="color: red">对方</span></span>
    <span>网络状况：<span style="color: red"></span></span>
</div>
<canvas id="chess" width="450px" height="450px"></canvas><!--整个画布-->

<script src="${pageContext.request.contextPath}/resource/js/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/script.js"></script><!--引进JS和路径-->
<script type="text/javascript">
    $(function () {

    });
</script>
</body>
</html>