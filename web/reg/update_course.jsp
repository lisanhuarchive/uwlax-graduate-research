<%@ page import="org.lsh.data.control.User" %>
<%--
  Created by IntelliJ IDEA.
  User: lsh
  Date: 14/11/20
  Time: 上午4:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static org.lsh.helper.Constants.*" %>
<%@ page import="java.util.List" %>
<%@ page import="org.lsh.data.Teacher" %>
<%@ page import="org.lsh.data.control.DataCenter" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="org.lsh.data.Term" %>
<%
	User usr = (User) session.getAttribute("usr");
	if (null == usr) {
		session.setAttribute("error", "Please login first.");
		response.sendRedirect(root);
	} else if (usr.getType() != ADM) {
		session.setAttribute("error", "You can not access this page");
		response.sendRedirect(root);
	}
%>

<%
	List<Teacher> teas = DataCenter.query("from Teacher ");
	ArrayList<String> teachers = new ArrayList<>();
	ArrayList<String> teacher_ids = new ArrayList<>();
	for (Teacher t : teas) {
		teachers.add(t.getTeacherName());
		teacher_ids.add(t.getTeacherName() + "--" + t.getTeacherId());
	}
	Gson gson = new Gson();
%>

<%
	List<Term> terms = DataCenter.query("from Term t where t.activated = ?", true);
	ArrayList<String> term_strs = new ArrayList<>();
	for (Term t : terms) {
		term_strs.add(t.getTermName());
	}
%>
<html>
<head>
	<title>Add Course</title>
	<link rel="stylesheet" href="<%=root%>/refs/bootstrap.css"/>
	<link rel="stylesheet" href="<%=root%>/refs/jquery-ui.css"/>
	<script src="<%=root%>/refs/jquery-2.1.1.js"></script>
	<script src="<%=root%>/refs/jquery-ui.js"></script>
	<script src="<%=root%>/refs/bootstrap.js"></script>
	<script type="text/javascript">
		$(function () {
			var teachers = <%=gson.toJson(teachers)%>;
			$('#teacher').autocomplete({
				source: teachers
			});

			var terms = <%=gson.toJson(term_strs)%>;
			$('#term').autocomplete({source: terms});
		});
	</script>
</head>
<body>
<form action="<%=root%>/reg/add_course" method="post">
	<div>
		<div>
			<label>
				course number:
				<input type="text" name="cno" required/>
			</label>
		</div>
		<div>
			<label>
				section:
				<input type="text" name="section" required/>
			</label>
		</div>
		<div>
			<label>
				course title:
				<input type="text" name="ctitle" required/>
			</label>
		</div>
		<div>
			<label>
				teacher:
				<input type="text" name="teacher" id="teacher"/>
			</label>
		</div>
		<div>
			<label>
				time:
				<input type="text" name="time" required/>
			</label>
		</div>
		<div>
			<label>
				capacity:
				<input type="text" name="capacity" required/>
			</label>
		</div>
		<div>
			<label>
				weekdays:
				<div id="weekdays">
					<input type="checkbox" name="weekdays" id="wd0" value="<%=SUN%>"/><label for="wd0">SUN</label>
					<input type="checkbox" name="weekdays" id="wd1" value="<%=MON%>"/><label for="wd1">MON</label>
					<input type="checkbox" name="weekdays" id="wd2" value="<%=TUE%>"/><label for="wd2">TUE</label>
					<input type="checkbox" name="weekdays" id="wd3" value="<%=WED%>"/><label for="wd3">WED</label>
					<input type="checkbox" name="weekdays" id="wd4" value="<%=THU%>"/><label for="wd4">THU</label>
					<input type="checkbox" name="weekdays" id="wd5" value="<%=FRI%>"/><label for="wd5">FRI</label>
					<input type="checkbox" name="weekdays" id="wd6" value="<%=SAT%>"/><label for="wd6">SAT</label>
				</div>
			</label>
		</div>
		<div>
			<label>
				term:
				<input type="text" name="term" id="term"/>
			</label>
		</div>
	</div>
	<div>
		<button type="submit">Submit</button>
	</div>
</form>
</body>
</html>
