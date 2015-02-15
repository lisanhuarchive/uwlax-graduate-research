<%@ page import="org.lsh.helper.Functions" %>
<%@ page import="java.util.List" %>
<%@ page import="static org.lsh.helper.Constants.*" %>
<%@ page import="org.lsh.data.*" %>
<%@ page import="static org.lsh.helper.Functions.getSCByStudentAndCourse" %>
<%--
  Created by IntelliJ IDEA.
  User: lsh
  Date: 14/12/3
  Time: 上午5:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
    String cid = request.getParameter("cid");
    if (cid == null) {
        out.println("Parameter lost");
        return;
    }
    Course course = Functions.getCourseById(Integer.parseInt(cid));
    pageContext.setAttribute("students", Functions.getValidStudentsByCourse(course));

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
<table class="table table-bordered">
    <tr>
        <th>Student Id</th>
        <th>Student name</th>
        <th>Student attendance</th>
        <th>Student grades</th>
        <th>Actions</th>
    </tr>
    <c:forEach items="${students}" var="student">
        <tr>
            <td>${student.studentId}</td>
            <td>${student.studentName}</td>
            <td>
                <%
                    Student student = (Student) pageContext.getAttribute("student");
                    List<StudentRecord> studentRecords = Functions.getStudentRecordsByStudentAndCourse(student, course);
                    List<Record> records = Functions.getRecordsByCourse(course);
                %>
                <a href="show_detailed_records.jsp?scid=<%=getSCByStudentAndCourse(student, course).getScid()%>"><%=studentRecords.size()%>/<%=records.size()%></a>
            </td>
            <%
                List<GradeItem> items = Functions.getValidGradeItemsByCourse(course);
                int sum = 0;
                String total = "";
                for (GradeItem gi : items) {
                    sum += gi.getTotal();
                }
                if (items.size() == 0) {
                    total = "-";
                } else {
                    total += sum;
                }

                List<Grade> grades = Functions.getValidGradesByStudentAndCourse(student, course);
                sum = 0;
                String earned = "";
                for (Grade grade : grades) {
                    sum += grade.getGrade();
                }
                if (grades.size() == 0) {
                    earned = "-";
                } else {
                    earned += sum;
                }

                StudentCourse sc = Functions.getValidSCByStudentAndCourse(student, course);
                pageContext.setAttribute("sc", sc);
            %>
            <td><%=earned%>/<%=total%>
            </td>
            <td><a href="give_marks.jsp?scid=${sc.scid}" target="_blank">Give marks</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
