<%@ page import="org.lsh.data.Course" %>
<%@ page import="org.lsh.helper.Functions" %>
<%@ page import="org.lsh.data.Student" %>
<%@ page import="org.lsh.data.StudentCourse" %>
<%@ page import="java.util.List" %>
<%@ page import="org.lsh.data.GradeItem" %>
<%@ page import="static org.lsh.helper.Constants.*" %>
<%--
  Created by IntelliJ IDEA.
  User: lsh
  Date: 14/12/3
  Time: 上午7:33
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
    if (course == null) {
        out.println("No such course");
        return;
    }

    List<GradeItem> items = Functions.getGradeItemsByCourse(course);
    pageContext.setAttribute("items", items);
    pageContext.setAttribute("course", course);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
            <th>Total</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${items}" var="item">
            <form action="<%=root%>/tea/edit_grade_item" method="post" target="_blank">
                <tr>
                    <td>
                        <input type="text" name="name" value="${item.itemName}"/>
                    </td>
                    <td>
                        <input type="text" name="value" value="${item.total}"/>
                    </td>
                    <%
                        GradeItem gi = (GradeItem) pageContext.getAttribute("item");
                        boolean valid = true;
                        if (gi.getIsValid() == 0) {
                            valid = false;
                        }
                    %>
                    <td>
                        <input name="id" type="hidden" value="${item.itemId}"/>
                        <input name="action" value="update" type="submit"/>
                        <c:if test="<%=valid%>">
                            <input name="action" value="delete" type="submit"/>
                        </c:if>
                        <c:if test="<%=!valid%>">
                            <input name="action" value="activate" type="submit"/>
                        </c:if>
                    </td>
                </tr>
            </form>
        </c:forEach>
        <form action="<%=root%>/tea/add_grade_item" method="post" target="_blank">
            <tr>
                <td>
                    <input type="text" name="name" placeholder="item name *" required/>
                </td>
                <td>
                    <input type="text" name="value" placeholder="total points *" required/>
                </td>
                <td>
                    <input name="cid" type="hidden" value="${course.cid}"/>
                    <button type="submit">Add</button>
                </td>
            </tr>
        </form>
    </table>
</div>
</body>
</html>
