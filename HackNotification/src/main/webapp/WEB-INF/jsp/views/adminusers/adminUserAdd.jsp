<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Users</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<!-- <div class="panel-heading">
				
			</div> -->
			<c:if test="${addError!=null}">
				<div class="alert alert-success text-center">${addError}</div>
			</c:if>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<c:url var="addCinema" value="/admin/saveuser" />
<form:form class="form-horizontal" role="form" method="post"
	action="${addCinema}" name="frm" modelAttribute="sysUserVO" enctype="multipart/form-data">
	
	<div class="form-group ">
		<label for="userName" class="col-lg-2 control-label">Name <span style="color: red;">*</span></label>
		<div class="has-error col-lg-6">
			<form:input type="text" class="form-control" id="userName"
				placeholder="Name" path="userName" maxlength="150" required="required"/>
			<%-- <form:input type="text" id="userName" path="userName" class="form-control" placeholder="Enter Name of User" maxlength="150" required="required"/> --%>			
		</div>
		<div class="has-error col-lg-4">
			<label for="inputError" class="control-label"><form:errors path="userName"/></label>						
		</div>
	</div>
	
	<div class="form-group ">
		<label for="userName" class="col-lg-2 control-label">Email Id <span style="color: red;">*</span></label>
		<div class="has-error col-lg-6">
			<form:input type="email" class="form-control" id="login"
				placeholder="Email Id" path="login" maxlength="150" required="required"/>
			<!-- <input type="email" id="inputError" class="form-control" name="login" placeholder="Enter Email" maxlength="150" required="required"> -->			
		</div>
		<div class="has-error col-lg-4">
			<label for="inputError" class="control-label"><form:errors path="login"/></label>						
		</div>
	</div>
	
	<div class="form-group ">
		<label for="userName" class="col-lg-2 control-label">Mobile Number</label>
		<div class="col-lg-6">
			<input type="text" class="form-control" name="mobileNumber" placeholder="Enter Mobile Number" maxlength="12">			
		</div>
	</div>
	
	
	<div class="form-group">
		<div class="checkbox col-lg-6 col-md-offset-2">
                                                <label>
                                                    <input type="checkbox" name="ifActive">Active
                                                </label>
                                            </div>
	</div>
	
	<div class="form-group">
		<div class="col-lg-6 col-md-offset-3">
			<button type="submit" class="btn btn-default">Submit</button>
			<button type="reset" class="btn btn-default">Reset</button>
		</div>
	</div>
</form:form>
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
