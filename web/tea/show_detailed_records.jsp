<%@ page import="java.util.List" %>
<%@ page import="org.lsh.data.StudentRecord" %>
<%@ page import="org.lsh.helper.Functions" %>
<%--
  Created by IntelliJ IDEA.
  User: lsh
  Date: 15/2/2
  Time: 上午3:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Integer scid = Integer.parseInt(request.getParameter("scid"));
    List<StudentRecord> srs = Functions.getStudentRecordsBySCId(scid);
    request.setAttribute("srs", srs);
%>
<html>
<head>
    <title></title>
</head>
<body>
<table>
    <tr>
        <th>Record Id</th>
        <th>Record Date</th>
    </tr>
    <c:forEach items="${srs}" var="sr">
        <tr>
            <td>${sr.rid}</td>
            <td>${sr.recordTime}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
