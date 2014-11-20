<%@ page import="org.lsh.data.Teacher" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="org.lsh.data.control.DataCenter" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="static org.lsh.helper.Constants.*" %>
<%--
  Created by IntelliJ IDEA.
  User: lsh
  Date: 14/11/20
  Time: 上午4:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	List<Teacher> teas = DataCenter.query("from Teacher ");
	ArrayList<String> teachers = new ArrayList<>();
	for (Teacher t : teas) {
		teachers.add(t.getTeacherName() + " -- " + t.getTeacherId());
	}
	Gson gson = new Gson();
	gson.toJson(teachers);
%>
<%--<%=Arrays.toString(teachers.toArray(new String[teachers.size()]))%>--%>
<%--<%=gson.toJson(teachers)%>--%>
<!doctype html>
<html lang="en">
<head>
	<%--<link rel="stylesheet" href="<%=root%>/refs/bootstrap.css"/>--%>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
	<%--<script src="<%=root%>/refs/bootstrap.js"></script>--%>
	<script>
		$(function () {
			$("#weekdays").buttonset()
		});
	</script>
</head>
<body>
<div id="weekdays">
	<input type="checkbox" name="weekdays" id="wd1"/><label for="wd1">SUN</label>
	<input type="checkbox" name="weekdays" id="wd2"/><label for="wd2">MON</label>
	<input type="checkbox" name="weekdays" id="wd3"/><label for="wd3">TUE</label>
	<input type="checkbox" name="weekdays" id="wd4"/><label for="wd4">WED</label>
	<input type="checkbox" name="weekdays" id="wd5"/><label for="wd5">THU</label>
	<input type="checkbox" name="weekdays" id="wd6"/><label for="wd6">FRI</label>
	<input type="checkbox" name="weekdays" id="wd7"/><label for="wd7">SAT</label>
</div>
</body>
</html>