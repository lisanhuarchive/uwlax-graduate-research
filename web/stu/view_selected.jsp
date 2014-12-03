<%@ page import="org.lsh.helper.Functions" %>
<%@ page import="org.lsh.data.Teacher" %>
<%@ page import="org.lsh.data.Course" %>
<%@ page import="org.lsh.data.Student" %>
<%@ page import="org.lsh.data.control.User" %>
<%@ page import="static org.lsh.helper.Constants.*" %>
<%--
  Created by IntelliJ IDEA.
  User: lsh
  Date: 14/12/2
  Time: 下午5:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
    User usr = (User) session.getAttribute("usr");
    Student student = (Student) usr.getUser();
    pageContext.setAttribute("courses", Functions.getValidCoursesByStudent(student));
%>
<div class="col-lg-12">
    <table class="table table-bordered table-striped">
        <colgroup width="20%"></colgroup>
        <colgroup width="25%"></colgroup>
        <colgroup width="25%"></colgroup>
        <colgroup width="15%"></colgroup>
        <colgroup width="15%"></colgroup>
        <tr>
            <th>course id</th>
            <th>course no - section</th>
            <th>course title</th>
            <th>course teacher</th>
            <th>action</th>
        </tr>
        <c:forEach items="${courses}" var="course">
            <tr>
                <td>
                        ${course.cid}

                </td>
                <td>
                        ${course.cNo}-${course.section}
                </td>
                <td>
                        ${course.cTitle}
                </td>
                <td>
                    <%
                        Course c = (Course) pageContext.getAttribute("course");
                        Teacher t = Functions.getTeacherByCourse(c);
                        pageContext.setAttribute("teacher", t.getTeacherName());
                    %>
                        ${teacher}
                </td>
                <td>
                    <a class="btn" target="_blank"
                       href="view_course_grades.jsp?cid=<%=c.getCid()%>&sid=<%=student.getStudentId()%>">View Grades</a>
                    <a class="btn" target="_blank"
                       href="<%=root%>/stu/drop_course?cid=<%=c.getCid()%>&sid=<%=student.getStudentId()%>">Drop
                        Course</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
