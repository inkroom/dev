<%@ page import="java.util.Enumeration" %>
<%@ page import="org.springframework.validation.BindingResult" %><%--
  User: 墨盒
  Date: 2017/6/9
  Time: 17:15
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>第一个Mvc项目</title>
</head>
<body>
目前在线人数：${number}
测试项目是否成功<br>
one = ${one}<br>
two = ${two}<br>
<fmt:message key="main.title"/><br>
<fmt:message key="main.show"/><br>
<fmt:message key="test.show"/><br>
<fmt:message key="test.title.one"/><br>
<fmt:message key="test.title"/><br>

<%

    BindingResult result = (BindingResult) request.getAttribute("result");
    System.out.println(result.getErrorCount());


    System.out.println(result.getFieldError().getField()+"  "+result.getFieldError().getDefaultMessage());

//    Enumeration<String> en = request.getAttributeNames();
//    while (en.hasMoreElements()){
//        String key = en.nextElement();
//        System.out.println("key = "+key+"  value = "+request.getAttribute(key));
//    }

%>

</body>
</html>
