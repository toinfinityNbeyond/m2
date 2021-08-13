<%--
  Created by IntelliJ IDEA.
  User: 82102
  Date: 2021-08-13
  Time: 오후 12:39
  To change this template use File | Settings | File Templates.
--%>

<%
    //HttpSession session = request.getSession();

    session.removeAttribute("name");
    session.invalidate();
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
