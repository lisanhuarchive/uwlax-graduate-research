<%@ page import="org.lsh.data.Student" %>
<%@ page import="org.lsh.helper.Functions" %>
<%@ page import="org.lsh.data.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="org.lsh.data.StudentCourse" %>
<%@ page import="org.lsh.data.control.DataCenter" %>
<%--
  Created by IntelliJ IDEA.
  User: lsh
  Date: 14/12/2
  Time: 下午8:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String sid = request.getParameter("sid");
    String cid = request.getParameter("cid");

    if (sid == null || cid == null) {
        out.println("Parameter lost");
        return;
    }

    Student student = Functions.getStudentById(sid);
    Course course = Functions.getCourseById(Integer.parseInt(cid));
    if (!whetherHasTheCourse(student, course)) {
        out.println("The student does not have selected the course");
        return;
    }


%>

<%!
    private static boolean whetherHasTheCourse(Student student, Course course) {
        List<StudentCourse> scs = DataCenter.query("from StudentCourse sc where sc.studentId = ? and sc.courseId = ?", student.getStudentId(), course.getCid());
        return !scs.isEmpty();
    }
%>

<html>
<head>
    <title></title>
</head>
<body>

</body>
</html>
