<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript">
	//-->

	function fnRoleClicked(userClicked) {
		/* alert("..");
		alert(userClicked);
		 */
		document.getElementById('userChosen').value = document
				.getElementById(userClicked).value;
		//alert(".."+document.getElementById('userChosen').value);
		document.getElementById('frm').submit();
	}
</script>

<style>
.custom-table-swati ul {
	margin: 10px 0 0 0;
	padding: 0;
}

.custom-table-swati li {
	list-style-type: none;
	height: 25px;
	border-bottom: solid 1px #cccccc;
}

.custom-table-swati tr td {
	padding: 0;
}

.custom-table-swati tr td:first-child li input {
	float: right;
}

/*  .custom-table-swati tr td:first-child li label {
	float: right;
	 text-overflow: ellipsis;
} 
 */
.custom-table-swati tr td li input {
	float: left;
}
</style>

<div align="center">
	<h1>Users Role</h1>
</div>
<c:if test="${addError!=null}">
	<div class="alert alert-success text-center">${addError}</div>
</c:if>
<c:if test="${addMsg!=null}">
	<div class="alert alert-success text-center">${addMsg}</div>
</c:if>
<c:url var="addCinema" value="/admin/saveuserroles" />
<form:form class="form-horizontal" role="form" method="post"
	action="${addCinema}" name="frm" id="frm" modelAttribute="userForm"
	enctype="multipart/form-data">
	<%-- <form:hidden path="userChosen" id="userChosen" /> --%>

	<div class="table-responsive">
		<table
			class="table table-bordered table-hover table-condensed  custom-table-swati">


			<tr>
				<td>User: <form:input readonly="true" path="userName"
						cssStyle="border: none;background : none;" /> <form:hidden
						path="login" /></td>
			</tr>
			<tr>
				<td>


					<ul>
						<form:checkboxes items="${roleList}" element="li"
							path="lstUserRoles" />

						<!-- roleListNoLabel -->
					</ul> </td></tr><!-- lstUserRoles --> 
					<tr><td>
					<c:choose>
						<c:when test="${username == login}"></c:when>
						<c:otherwise>

							<button type="submit" class="btn btn-primary">Save</button>


						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
	</div>

</form:form>