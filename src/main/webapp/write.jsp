<%--
  Created by IntelliJ IDEA.
  User: 82102
  Date: 2021-08-13
  Time: 오후 12:12
  To change this template use File | Settings | File Templates.
--%>
<%
    //HttpSession session = request.getSession();

    Object obj = session.getAttribute("member");   //조회.  object 모든것을 저장하려고.
    //obj가 null 로그인페이지로 돌아가게 된다.

    if(obj == null) {
        response.sendRedirect("/login?result=fail");  //get
        return;
    } //만약 obj 가 null 이면 로그인 페이지로 튕겨내야함.



%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Write Page</h1>
    <h2><%=obj%></h2>

<form action="logout.jsp" method="post">
    <button type="submit">LOGOUT</button>
</form>
</body>
</html>
