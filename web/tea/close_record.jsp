<%@ page import="org.lsh.data.Record" %>
<%@ page import="org.lsh.helper.Functions" %>
<%@ page import="org.lsh.data.control.DataCenter" %>
<%--
  Created by IntelliJ IDEA.
  User: lsh
  Date: 14/12/3
  Time: 上午6:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String rid = request.getParameter("rid");
    if (rid == null) {
        out.println("Parameter lost");
        return;
    }

    Record record = Functions.getRecordByRid(Integer.parseInt(rid));

    if (record == null) {
        out.println("Record does not exist.");
        return;
    }

    if (record.getIsOpen() == 1) {
        record.setIsOpen(0);
        DataCenter.update(record);
    }
%>
<html>
<head>
    <title></title>
</head>
<body>
success
</body>
</html>
