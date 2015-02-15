<%@ page import="org.lsh.data.Record" %>
<%@ page import="org.lsh.helper.Functions" %>
<%@ page import="static org.lsh.helper.Constants.*" %>
<%@ page import="org.lsh.data.control.DataCenter" %>
<%--
  Created by IntelliJ IDEA.
  User: lsh
  Date: 15/2/2
  Time: 上午3:08
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


%>
<html>
<head>
    <title></title>
</head>
<body>
the rid is:
<br/>
<%=record.getRid()%>
<br/>
<a href="<%=root%>/stu/sign_up?rid=<%=record.getRid()%>&sid=957427426">Sign Up for 957427426</a>
<img src="https://api.qrserver.com/v1/create-qr-code/?&data=<%=record.getRid()%>"
     alt="qr code"/>
</body>
</html>