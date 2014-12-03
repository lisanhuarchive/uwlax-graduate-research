<%--
  Created by IntelliJ IDEA.
  User: lsh
  Date: 14/11/20
  Time: 上午4:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static org.lsh.helper.Constants.*" %>
<%@ page import="java.util.List" %>
<%@ page import="org.lsh.data.control.DataCenter" %>
<%@ page import="org.lsh.data.Course" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
	List<Course> courses = DataCenter.query("from Course c where c.activate = ?", true);
	pageContext.setAttribute("courses", courses);
%>
<div class="col-lg-12">
	<table class="table table-bordered table-striped">
		<colgroup width="20%"></colgroup>
		<colgroup width="25%"></colgroup>
		<colgroup width="25%"></colgroup>
		<colgroup width="15%"></colgroup>
		<colgroup width="15%"></colgroup>
		<tr>
			<th>course id</th>
			<th>course no - section</th>
			<th>course title</th>
			<th colspan="2">action</th>
		</tr>
		<c:forEach items="${courses}" var="course">
			<tr>
				<td>
						${course.cid}
				</td>
				<td>
						${course.cNo}-${course.section}
				</td>
				<td>
						${course.cTitle}
				</td>
				<td>
					<form action="<%=root%>/reg/update_course.jsp" method="get">
						<input name="id" type="hidden" value="${course.cid}"/>
						<button class="btn" type="submit" formtarget="_blank">Update</button>
					</form>
				</td>
				<form action="<%=root%>/reg/disable_course" method="post">
					<td>
						<button class="btn" type="submit">Disable</button>
						<input name="id" type="hidden" value="${course.cid}"/>
					</td>
				</form>

			</tr>
		</c:forEach>
	</table>
</div>
<div class="col-lg-12">
	<div>
		<a href="<%=root%>/reg/add_course.jsp" class="btn" target="_blank">Add Course</a>
	</div>
</div>
