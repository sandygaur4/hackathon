<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Users</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<c:if test="${addError!=null}">
	<div class="alert alert-success text-center">${addError}</div>
</c:if>
<c:if test="${addMsg!=null}">
	<div class="alert alert-success text-center">${addMsg}</div>
</c:if>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/adduser"><i class="fa fa-plus"></i> Add User</a>
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<c:if test="${fn:length(userList) gt 0}">
					<div class="table-responsive">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<thead>
								<tr>
									<th>Name</th>
									<th>Email Id</th>
									<th>Mobile Number</th>
									<th>Active</th>
									<th colspan="3">Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${userList}" var="auser">
									<tr>
										<td><c:out value="${auser.userName}" /></td>
										<td><c:out value="${auser.login}" /></td>
										<td><c:out value="${auser.mobileNumber}" /></td>
										<td><c:out value="${auser.ifActive}" /></td>
										<c:choose>
											<c:when test="${username == auser.login}"></c:when>
											<c:otherwise>
												<c:if test="${auser.ifDisplayed == true}">
													<td><a
														href="${pageContext.request.contextPath}/admin/editauser/${auser.id}"
														class="btn btn-primary btn-xs"><span
															class="glyphicon glyphicon-pencil"></span> Edit</a></td>
													<td><a
														href="${pageContext.request.contextPath}/admin/changepermissionauser/${auser.id}"
														class="btn btn-primary btn-xs"><span
															class="glyphicon glyphicon-pencil"></span> Change Role</a></td>
												</c:if>
											</c:otherwise>
										</c:choose>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:if>
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->