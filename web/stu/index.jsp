<%--
  Created by IntelliJ IDEA.
  User: lsh
  Date: 14/11/13
  Time: 下午1:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="static org.lsh.helper.Constants.*" %>
<%@ page import="org.lsh.helper.Functions" %>

<%
    if (!Functions.checkUser(request, response, STU)) {
        return;
    }
%>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="<%=root%>/refs/bootstrap.css"/>

    <style type="text/css">
        body {
            padding-top: 6em;
            padding-bottom: 40px;
            background-color: #eee;
        }
    </style>
    <script src="<%=root%>/refs/jquery-2.1.1.js"></script>
    <script src="<%=root%>/refs/bootstrap.js"></script>
</head>
<body>
<div><a href="<%=root%>/logout">Logout</a></div>

<div>View Courses</div>

<jsp:include page="view_courses.jsp" flush="true"></jsp:include>

<div>View Selected Courses</div>
<jsp:include page="view_selected.jsp" flush="true"/>
</body>
</html>