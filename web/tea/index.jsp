<%@ page import="org.lsh.helper.Functions" %>
<%@ page import="static org.lsh.helper.Constants.*" %>
<%@ page import="org.lsh.data.control.User" %>
<%@ page import="org.lsh.data.Teacher" %>
<%@ page import="org.lsh.data.Record" %>
<%@ page import="org.lsh.data.Course" %>
<%--
  Created by IntelliJ IDEA.
  User: lsh
  Date: 14/11/13
  Time: 下午1:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (!Functions.checkUser(request, response, TEA)) {
        return;
    }

    User usr = (User) session.getAttribute("usr");

    pageContext.setAttribute("courses", Functions.getCoursesByTeacher((Teacher) usr.getUser()));
%>
<html>
<head>
    <title></title>

    <link rel="stylesheet" href="<%=root%>/refs/bootstrap.css"/>
    <link rel="stylesheet" href="<%=root%>/refs/bootstrap-theme.css"/>

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
<div>My Courses</div>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<div class="col-lg-8 col-lg-offset-2">
    <table class="table table-bordered">
        <tr>
            <th>course id</th>
            <th>course number - section</th>
            <th>course title</th>
            <th colspan="3">actions</th>
        </tr>
        <c:forEach items="${courses}" var="course">
            <tr>
                <td>${course.cid}</td>
                <td>${course.cNo} - ${course.section}</td>
                <td>${course.cTitle}</td>
                <td><a href="view_students.jsp?cid=${course.cid}" target="_blank">View students</a></td>
                <td><a href="edit_grade_items.jsp?cid=${course.cid}" target="_blank">Edit grade items</a></td>
                <td>
                    <%
                        Course course = (Course) pageContext.getAttribute("course");
                        Record record = Functions.getOpenRecordByCourse(course);
                        if (record == null) {
                    %>
                    <a href="open_record.jsp?cid=${course.cid}" target="_blank">Open a record</a>
                    <%
                    } else {
                    %>
                    <a href="close_record.jsp?rid=<%=record.getRid()%>" target="_blank">Close the record</a>
                    <%
                        }
                    %>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
