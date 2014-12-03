<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%--
  Created by IntelliJ IDEA.
  User: lsh
  Date: 14/12/2
  Time: 下午1:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static org.lsh.helper.Constants.*" %>
<%@ page import="java.util.List" %>
<%@ page import="org.lsh.data.control.DataCenter" %>
<%@ page import="org.lsh.data.Course" %>
<%@ page import="org.lsh.data.Teacher" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="org.lsh.data.Student" %>
<%@ page import="org.lsh.data.StudentCourseView" %>
<%@ page import="org.lsh.data.control.User" %>
<%
    List<Course> courses = DataCenter.query("from Course c where c.activate = ?", true);
    HashMap<String, Teacher> teachers = new HashMap<>();

    for (Course c : courses) {
        teachers.put(c.getTeacher(), (Teacher) DataCenter.query("from Teacher t where t.teacherId = ?", c.getTeacher()).get(0));
    }

    pageContext.setAttribute("courses", courses);
    pageContext.setAttribute("teachers", teachers);

    User usr = (User) session.getAttribute("usr");
    Student student = (Student) (DataCenter.query("from Student s where s.studentId = ?", ((Student) usr.getUser()).getStudentId())).get(0);
    pageContext.setAttribute("student", student);
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
                        ${teachers[course.teacher].teacherName}
                </td>
                <td>
                    <a class="btn" href="view_course_detail.jsp?cid=${course.cid}" target="_blank">View Detail</a>
                    <%
                        boolean isIt = isSelected(student, (Course) pageContext.getAttribute("course"));
                    %>
                    <c:if test="<%=!isIt%>">
                        <form action="<%=root%>/stu/select_course" method="get">
                            <button class="btn" type="submit" formtarget="_blank">Select Course</button>
                            <input name="sid" type="hidden" value="${student.studentId}"/>
                            <input name="cid" type="hidden" value="${course.cid}"/>
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<%!
    private boolean isSelected(Student stu, Course course) {
        List<StudentCourseView> records = DataCenter.query("from StudentCourseView scv where scv.studentId = ? and scv.cid = ?", stu.getStudentId(), course.getCid());
        return !records.isEmpty();
    }
%>