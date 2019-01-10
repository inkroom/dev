<%--
  User: 墨盒
  Date: 2017/9/16
  Time: 18:01
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试</title>
</head>
<body>
<video id="video" onload="this.currentTime=300000" controls="controls" src="video/电影/temp.mp4" currentTime="30000000">
    不支持
</video>
<script type="text/javascript">
    var d = document.getElementById('video');
    d.onloadstart=function () {
        var temp = document.createElement('p');
//        alert(this.duration);
        temp.innerHTML="onloadstart  "+this.duration;
        document.body.appendChild(temp);
    }
    d.onloadeddata=function () {
        var temp = document.createElement('p');
        alert(this.duration);
        temp.innerHTML="onloadeddata  "+this.duration;
        document.body.appendChild(temp);
    }
</script>
</body>
</html>
