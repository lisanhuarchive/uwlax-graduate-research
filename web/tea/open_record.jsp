<%@ page import="org.lsh.data.Record" %>
<%@ page import="org.lsh.helper.Functions" %>
<%@ page import="org.lsh.data.Course" %>
<%@ page import="org.lsh.data.control.DataCenter" %>
<%@ page import="static org.lsh.helper.Constants.*" %>
<%--
  Created by IntelliJ IDEA.
  User: lsh
  Date: 14/12/3
  Time: 上午5:52
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

    Record record = Functions.getOpenRecordByCourse(course);
    if (record == null) {
        record = new Record();
        record.setCid(course.getCid());
        record.setIsOpen(1);
        DataCenter.save(record);
    }
    record = Functions.getOpenRecordByCourse(course);
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
<img src="https://api.qrserver.com/v1/create-qr-code/?&data=<%=root%>/stu/sign_up?rid=<%=record.getRid()%>"
     alt="qr code"/>
</body>
</html>
