<%@ page import="org.lsh.data.StudentCourse" %>
<%@ page import="org.lsh.helper.Functions" %>
<%@ page import="org.lsh.data.GradeItem" %>
<%@ page import="java.util.List" %>
<%@ page import="org.lsh.data.Course" %>
<%@ page import="org.lsh.data.Grade" %>
<%@ page import="static org.lsh.helper.Constants.*" %>
<%--
  Created by IntelliJ IDEA.
  User: lsh
  Date: 14/12/3
  Time: 上午10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String scid = request.getParameter("scid");
    if (scid == null) {
        out.println("Parameter lost");
        return;
    }

    StudentCourse sc = Functions.getSCById(Integer.parseInt(scid));
    Course course = Functions.getCourseById(sc.getCourseId());
    List<GradeItem> items = Functions.getValidGradeItemsByCourse(course);

    pageContext.setAttribute("items", items);
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
<div>
    <table class="table table-bordered">
        <tr>
            <th>Item name</th>
            <th>Grade earned</th>
            <th>Action</th>
        </tr>
        <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
        <c:forEach items="${items}" var="item">
            <form action="<%=root%>/tea/edit_student_grade" method="post" target="_blank">
                <tr>
                    <td>${item.itemName}</td>
                    <%
                        GradeItem gi = (GradeItem) pageContext.getAttribute("item");
                        Grade g = Functions.getGradeByGradeItemAndSCID(gi, sc.getScid());
                        int grade = g == null ? 0 : g.getGrade();
                        pageContext.setAttribute("grade", grade);
                    %>
                    <td>
                        <input type="text" name="grade" required value="${grade}"/>
                    </td>
                    <td>
                        <input name="itemid" type="hidden" value="${item.itemId}"/>
                        <input name="scid" type="hidden" value="<%=sc.getScid()%>"/>
                        <c:if test="<%=g!=null%>">
                            <input name="gid" type="hidden" value="<%=g.getGid()%>"/>
                            <button name="action" type="submit" value="update">Update</button>
                        </c:if>
                        <c:if test="<%=g==null%>">
                            <button name="action" type="submit" value="add">Update</button>
                        </c:if>
                    </td>
                </tr>
            </form>
        </c:forEach>
    </table>
</div>
</body>
</html>
