<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="text-center">
	<h1>Edit Admin User</h1>
</div>
<c:if test="${addError!=null}">
	<div class="alert alert-success text-center">${addError}</div>
</c:if>
<c:url var="addCinema" value="/admin/updateuser" />
<form:form class="form-horizontal" role="form" method="post"
	action="${addCinema}" name="frm" modelAttribute="userForm" enctype="multipart/form-data">
	<div class="form-group">
	<div class="col-lg-6">
	<form:hidden path="login"/>
	<form:hidden path="password"/>
	<form:hidden path="id"/>
	
	<%-- <form:errors path="*" cssClass="errorblock" element="div" /> --%>
	</div></div>
	<div class="form-group">
		<label for="userName" class="col-lg-2 control-label">Name <span style="color: red;">*</span></label>
		<div class="col-lg-6">
			<font color="red"><form:errors path="userName"/></font>
			<form:input type="text" class="form-control" id="userName"
				placeholder="Name" path="userName" maxlength="150"/>
		</div>
	</div>
	<div class="form-group">
		<label for="login" class="col-lg-2 control-label">Email Id <span style="color: red;">*</span></label>
		<div class="col-lg-6">
			<font color="red"><form:errors path="login" cssClass="error"/></font>
			<form:input type="email" class="form-control" id="login"
				placeholder="Email Id" path="login" maxlength="150" disabled="true"/>
		</div>
	</div>
	
	<div class="form-group">
		<label for="mobileNumber" class="col-lg-2 control-label">Mobile Number </label>
		<div class="col-lg-6">
			<font color="red"><form:errors path="mobileNumber" /></font>
			<form:textarea type="text" rows="3"  class="form-control" id="mobileNumber"
				placeholder="Mobile Number" path="mobileNumber" maxlength="12" onkeypress="return isNumberKey(event)" />
		</div>
	</div>
		<div class="form-group">
		
		<div class="col-lg-offset-2 col-lg-10">
			<div class="checkbox">
				<label> <form:checkbox  path="ifActive"/> Active
				</label>
			</div>
		</div>
		
	</div>
	
	<div class="form-group">
		
		<div class="col-lg-offset-2 col-lg-10">
			<div class="checkbox">
				<label> <form:checkbox  path="ifDeleted"/> Deleted
				</label>
			</div>
		</div>
		
	</div>
	<div class="form-group">
		<div class="col-lg-offset-2 col-lg-10">
			<button type="submit" class="btn btn-default">Edit User</button>
		</div>
	</div>
</form:form>
