<%--
  Created by IntelliJ IDEA.
  User: 82102
  Date: 2021-08-13
  Time: 오후 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Login</h1>

<style>
    .warnDiv {
        background-color: #c46d4c;
    }
</style>

<c:if test="${param.result !=null }">   <!--if에는 테스트라는 방식이 있음. EL에는 param 이라는 기능이 있다.-->
<div class="warnDiv">
    <h1>Login Failed</h1>
</div>
</c:if>


<form action="/login" method="post">  <!--로그인 방식은 post 방식-->
    <input type="text" name="mid" >
    <input type="text" name="mpw" >
    <button type="submit">LOGIN</button>
</form>
</body>
</html>
