<%--
  Created by IntelliJ IDEA.
  User: lsh
  Date: 14/12/3
  Time: 上午11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static org.lsh.helper.Constants.*" %>
<%@ page import="org.lsh.helper.Functions" %>
<%@ page import="java.util.List" %>
<%@ page import="org.lsh.data.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
<div>
    <%
        String sid = request.getParameter("sid");
        String cid = request.getParameter("cid");
        if (sid == null || cid == null) {
            out.println("Parameter lost");
            return;
        }

        Student student = Functions.getStudentById(sid);
        Course course = Functions.getCourseById(Integer.parseInt(cid));
        List<GradeItem> items = Functions.getValidGradeItemsByCourse(course);
        pageContext.setAttribute("items", items);
    %>
    <table class="table table-bordered">
        <tr>
            <th>Item name</th>
            <th>Grades</th>
        </tr>
        <c:forEach items="${items}" var="item">
            <tr>
                <td>${item.itemName}</td>
                <%
                    GradeItem item = (GradeItem) pageContext.getAttribute("item");
                    StudentCourse sc = Functions.getSCByStudentAndCourse(student, course);
                    Grade grade = Functions.getGradeByGradeItemAndSCID(item, sc.getScid());
                    int value = 0;
                    if (grade != null) {
                        value = grade.getGrade();
                    }
                %>
                <td>
                    <%=value%>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td>Attendance</td>
            <td>
                <%
                    List<StudentRecord> studentRecords = Functions.getStudentRecordsByStudentAndCourse(student, course);
                    List<Record> records = Functions.getRecordsByCourse(course);
                %>
                <%=studentRecords.size()%>/<%=records.size()%>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
