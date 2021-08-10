<%--
  Created by IntelliJ IDEA.
  User: 82102
  Date: 2021-08-10
  Time: 오전 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  <!--JSP에서 JSTL을 사용하고 싶으면 사용-->
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello2 JSP Page</h1>

<h2>${msg}</h2>  <!-- EL에서는 값만 만들어내면 된다. sysout이랑 비슷한 기능 -->

<ul>
    <c:forEach items="${arr}" var="str">
        <li>${str}</li>
    </c:forEach>  <!--items은 배열,컬렉션  var는 변수-->
</ul>

</body>
</html>
