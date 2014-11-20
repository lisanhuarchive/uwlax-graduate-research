<%--
  Created by IntelliJ IDEA.
  User: lsh
  Date: 14/11/19
  Time: 上午3:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static org.lsh.helper.Constants.*" %>
<%@ page import="java.util.List" %>
<%@ page import="org.lsh.data.control.DataCenter" %>
<%@ page import="org.lsh.data.Term" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
	List<Term> terms = DataCenter.query("from Term t where t.activated = ?", true);
	pageContext.setAttribute("terms", terms);
%>
<link rel="stylesheet" href="<%=root%>/refs/jquery-ui.css"/>
<script src="<%=root%>/refs/jquery-ui.js"></script>
<style type="text/css">
	.datepicker {
	}
</style>
<div class="col-lg-12">
	<table class="table table-bordered table-striped">
		<colgroup width="10%"></colgroup>
		<colgroup width="20%"></colgroup>
		<colgroup width="20%"></colgroup>
		<colgroup width="20%"></colgroup>
		<colgroup width="15%"></colgroup>
		<colgroup width="15%"></colgroup>
		<tr>
			<th>id</th>
			<th>name</th>
			<th>start date</th>
			<th>end date</th>
			<th colspan="2">action</th>
		</tr>
		<c:forEach items="${terms}" var="term">
			<tr>
				<form class="form-inline" action="<%=root%>/reg/update_term" method="post">
					<td>${term.termId}</td>
					<td>
						<input class="form-control" type="text" name="name" value="${term.termName}"/>
					</td>
					<td>
						<input class="form-control datepicker" type="text" name="start" value="${term.termStart}"
						       readonly/>
					</td>
					<td>
						<input class="form-control datepicker" type="text" name="end" value="${term.termEnd}" readonly/>
					</td>
					<td>
						<input name="id" type="hidden" value="${term.termId}"/>
						<button class="btn" type="submit">Update</button>
					</td>
				</form>
				<form action="<%=root%>/reg/disable_term" method="post">
					<td>
						<button class="btn" type="submit">Disable</button>
						<input name="id" type="hidden" value="${term.termId}"/>
					</td>
				</form>

			</tr>
		</c:forEach>
	</table>
</div>
<div class="col-lg-12">
	<form class="form-inline" action="<%=root%>/reg/add_term" method="post">
		<input class="form-control" type="text" name="name" placeholder="name *" required/>
		<input class="form-control datepicker" type="text" name="start" placeholder="start date *" required readonly/>
		<input class="form-control datepicker" type="text" name="end" placeholder="end date *" required readonly/>
		<button class="btn" type="submit">Add</button>
	</form>
</div>
<script type="text/javascript">
	jQuery(function () {
		jQuery('.datepicker').datepicker({
			changeMonth: true,
			changeYear: true
		});
	});

	disable = function (obj) {
		var f = jQuery(obj).parents('form');
		f.prop('action', '<%=root%>/reg/disable_term');
		console.log(f.prop('action'));
	}
</script>