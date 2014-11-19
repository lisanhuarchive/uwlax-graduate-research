<%@ page import="static org.lsh.helper.Constants.*" %>
<%--
  Created by IntelliJ IDEA.
  User: lsh
  Date: 14/11/12
  Time: 上午7:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
	Object error = session.getAttribute("error");
	pageContext.setAttribute("error", error);
	session.removeAttribute("error");
%>
<html>
<head>
	<title>Test page</title>
	<link rel="stylesheet" href="<%=root%>/refs/bootstrap.css"/>

	<style>

		body {
			padding-top: 6em;
			padding-bottom: 40px;
			background-color: #eee;
		}

	</style>
</head>
<body>

<div class="well container col-lg-4 col-lg-offset-4">
	<form action="<%=root%>/login" method="post">
		<h2>Please sign in</h2>

		<div class="form-group">
			<input class="form-control" type="text" name="uname" id="uname" placeholder="user name*" required/>
		</div>
		<div class="form-group">
			<input class="form-control" type="password" name="pswd" id="pswd" placeholder="password*" required/>
		</div>
		<div class="form-group">
			<div class="radio">
				<div class="radio-inline">
					<label for="stu">
						<input type="radio" name="id" id="stu" value="<%=STU%>" checked/>
						Student
					</label>
				</div>
				<div class="radio-inline">
					<label for="tea">
						<input type="radio" name="id" id="tea" value="<%=TEA%>"/>
						Teacher
					</label>
				</div>
				<div class="radio-inline">
					<label for="adm">
						<input type="radio" name="id" id="adm" value="<%=ADM%>"/>
						Admin
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<c:if test="${error != null}">
				<div class="alert alert-danger">
						${error}
				</div>
			</c:if>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
		</div>
	</form>
</div>

</body>
<script src="refs/jquery-2.1.1.js"></script>
<script src="refs/bootstrap.js"></script>
</html>
