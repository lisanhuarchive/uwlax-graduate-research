<%@ page import="org.lsh.data.Course" %>
<%@ page import="org.lsh.helper.Functions" %>
<%@ page import="org.lsh.data.Teacher" %>
<%@ page import="static org.lsh.helper.Constants.*" %>
<%@ page import="org.lsh.data.Term" %>
<%--
  Created by IntelliJ IDEA.
  User: lsh
  Date: 14/12/2
  Time: 下午5:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String cid = request.getParameter("cid");

    if (cid == null) {
        out.println("Parameter lost");
        return;
    }
    Course course = Functions.getCourseById(Integer.parseInt(cid));
    pageContext.setAttribute("course", course);

    Teacher teacher = Functions.getTeacherByCourse(course);
    pageContext.setAttribute("teacher", teacher);

    Term term = Functions.getTermByCourse(course);
    pageContext.setAttribute("term", term);

    int weekdays = course.getWeekdays();
%>
<html>
<head>
    <title>Information for ${course.cNo} - ${course.section}</title>
    <link rel="stylesheet" href="<%=root%>/refs/bootstrap.css"/>

    <style type="text/css">
        body {
            padding-top: 6em;
            padding-bottom: 40px;
            background-color: #eee;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="col-lg-4 col-lg-offset-4">
        <table class="table table-bordered">
            <tr>
                <td>Course id:</td>
                <td>${course.cid}</td>
            </tr>
            <tr>
                <td>Course number:</td>
                <td>${course.cNo}</td>
            </tr>
            <tr>
                <td>Course section:</td>
                <td>${course.section}</td>
            </tr>
            <tr>
                <td>Course title:</td>
                <td>${course.cTitle}</td>
            </tr>
            <tr>
                <td>Course teacher:</td>
                <td>${teacher.teacherName} (${teacher.teacherId})</td>
            </tr>
            <tr>
                <td>Course time:</td>
                <td>${course.cTime}</td>
            </tr>
            <tr>
                <td>Course capacity:</td>
                <td><%=Functions.getStudentNumberByCourse(course)%>/${course.capacity}</td>
            </tr>
            <tr>
                <td>Course weekdays:</td>
                <td>
                    <%=((weekdays & SUN) != 0) ? "SUN" : ""%>
                    <%=((weekdays & MON) != 0) ? "MON" : ""%>
                    <%=((weekdays & TUE) != 0) ? "TUE" : ""%>
                    <%=((weekdays & WED) != 0) ? "WED" : ""%>
                    <%=((weekdays & THU) != 0) ? "THU" : ""%>
                    <%=((weekdays & FRI) != 0) ? "FRI" : ""%>
                    <%=((weekdays & SAT) != 0) ? "SAT" : ""%>
                </td>
            </tr>
            <tr>
                <td>Course term:</td>
                <td>${term.termName}</td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
