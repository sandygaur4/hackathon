<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Cities</h1>
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
			<!-- /.panel-heading -->
			<div class="panel-body">
				<c:if test="${fn:length(citylist) gt 0}">
					<div class="table-responsive">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<thead>
								<tr>
									<th>Id</th>
									<th>Name</th>									
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${citylist}" var="city">
									<tr>
										<td><c:out value="${city.id}"/></td>
										<td><c:out value="${city.city}"/></td>
										<td><a
											href="${pageContext.request.contextPath}/admin/notifycity?id=${city.id}"
											class="btn btn-primary btn-xs"><span
												class="glyphicon glyphicon-pencil"></span> proceed </a></td>
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


