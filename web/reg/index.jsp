<%@ page import="java.util.List" %>
<%@ page import="org.lsh.data.Student" %>
<%@ page import="org.hibernate.SessionFactory" %>
<%@ page import="org.lsh.data.control.DataCenter" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="org.hibernate.Query" %>
<%@ page import="org.lsh.data.Teacher" %>
<%@ page import="static org.lsh.helper.Constants.*" %>
<%@ page import="org.lsh.data.control.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lsh
  Date: 14/11/12
  Time: 上午10:15
  To change this template use File | Settings | File Templates.
--%>
<%
	User usr = (User) session.getAttribute("usr");
	if (null == usr) {
		session.setAttribute("error", "Please login first.");
		response.sendRedirect(root);
	} else if (usr.getType() != ADM) {
		session.setAttribute("error", "You can not access this page");
		response.sendRedirect(root);
	}

	SessionFactory sf = DataCenter.getSf();
	Session s = sf.openSession();
	Query q = s.createQuery("from Student");
	List<Student> stus = q.list();
	session.setAttribute("stus", stus);

	q = s.createQuery("from Teacher");
	List<Teacher> teas = q.list();
	session.setAttribute("teas", teas);
	s.close();
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Registration page</title>
	<link rel="stylesheet" href="<%=root%>/refs/bootstrap.css"/>

	<style type="text/css">
		body {
			padding-top: 6em;
			padding-bottom: 40px;
			background-color: #eee;
		}

		#tabs {
			margin-top: 2em;
		}
	</style>

</head>
<body>

<div class="well container col-lg-6 col-lg-offset-3">
	<div>
		<ul class="nav nav-pills" id="bar">
			<li class="active"><a href="#stus">Students</a></li>
			<li><a href="#teas">Teachers</a></li>
			<li class="pull-right">
				<form action="<%=root%>">
					<a href="<%=root%>/logout">Logout</a>
				</form>
			</li>
		</ul>

		<div class="tab-content" id="tabs">
			<div id="stus" class="tab-pane active">
				<div class="col-lg-12">
					<table class="table table-bordered table-striped">
						<colgroup width="20%"></colgroup>
						<colgroup width="30%"></colgroup>
						<colgroup width="30%"></colgroup>
						<colgroup width="20%"></colgroup>
						<tr>
							<th>id</th>
							<th>name</th>
							<th>new password</th>
							<th>action</th>
						</tr>
						<c:forEach items="${stus}" var="stu">
							<tr>
								<form class="form-inline" action="<%=root%>/reg/upstu" method="post">
									<td>${stu.studentId}</td>
									<td>
										<input class="form-control" type="text" name="name" value="${stu.studentName}"/>
									</td>
									<td><input class="form-control" type="password" name="pswd"/></td>
									<td>
										<button class="btn" type="submit">Update</button>
										<input name="id" type="hidden" value="${stu.studentId}"/>
									</td>
								</form>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div class="col-lg-12">
					<form class="form-inline col-lg-12" action="<%=root%>/reg/addstu" method="post">
						<input class="form-control" type="text" name="id" id="sid" required placeholder="student id*"/>
						<input class="form-control" type="text" name="name" id="sname" required
						       placeholder="student name*"/>
						<input class="form-control" type="password" name="pswd" id="spswd" required
						       placeholder="student password*"/>
						<button class="btn" type="submit">Add</button>
					</form>
				</div>
			</div>

			<div id="teas" class="tab-pane">
				<div class="col-lg-12">
					<table class="table table-bordered">
						<colgroup width="20%"></colgroup>
						<colgroup width="30%"></colgroup>
						<colgroup width="30%"></colgroup>
						<colgroup width="20%"></colgroup>
						<tr>
							<th>id</th>
							<th>name</th>
							<th>new password</th>
							<th>action</th>
						</tr>
						<c:forEach items="${teas}" var="tea">
							<tr>
								<form action="<%=root%>/reg/uptea" method="post">
									<td>${tea.teacherId}</td>
									<td><input class="form-control" type="text" name="name" value="${tea.teacherName}"/>
									</td>
									<td>
										<input class="form-control" type="password" name="pswd"/>
									</td>
									<td>
										<input name="id" type="hidden" value="${tea.teacherId}"/>
										<button class="btn" type="submit">Update</button>
									</td>
								</form>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div class="col-lg-12">
					<form class="form-inline" action="<%=root%>/reg/addtea" method="post">
						<input class="form-control" type="text" name="id" id="tid" required placeholder="teacher id*"/>
						<input class="form-control" type="text" name="name" id="tname" required
						       placeholder="teacher name*"/>
						<input class="form-control" type="password" name="pswd" id="tpswd" required
						       placeholder="teacher password*"/>
						<button class="btn" type="submit">Add</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>


</body>
<script src="<%=root%>/refs/jquery-2.1.1.js"></script>
<script src="<%=root%>/refs/bootstrap.js"></script>
<script type="text/javascript">
	$('#bar > li > a').click(function (e) {
		$(this).tab('show');
	})

</script>
</html>
