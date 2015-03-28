<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
	
<nav class="navbar navbar-inverse">
	<!-- Brand and toggle get grouped for better mobile display -->
	
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-ex1-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="#">PVR Cinemas</a>
	</div>
	
	
	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse navbar-ex1-collapse">

	<ul class="nav navbar-nav">
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"><i class="glyphicon glyphicon-user white"></i><b
					class="caret"></b> Reports </a>
				<ul class="dropdown-menu">
					<li><a href="${pageContext.request.contextPath}/admin/preusersreport"
						title="Today's Lead List"><i
							class="glyphicon glyphicon-plus-sign white"></i> Pre Bookings </a></li>
							<li><a href="${pageContext.request.contextPath}/admin/bulkreport"
						title="Today's Lead List"><i
							class="glyphicon glyphicon-plus-sign white"></i> Bulk Bookings </a></li>
									<li><a href="${pageContext.request.contextPath}/admin/gcpurchasereport"
						title="Today's Lead List"><i
							class="glyphicon glyphicon-plus-sign white"></i> Gift Card Purchase </a></li>
							
								<li><a href="${pageContext.request.contextPath}/admin/fandbpurchasereport"
						title="Today's Lead List"><i
							class="glyphicon glyphicon-plus-sign white"></i> F&B Purchase </a></li>
					
		</ul></li>
		
		</ul>
	
	
	<ul class="nav navbar-nav navbar-right">
			<li><a href="<c:url value="/j_spring_security_logout" />"
				title="logout"><i class="glyphicon glyphicon-log-out white"></i>
					Logout</a></li>
		</ul>
	
	</div>
	
	<!-- /.navbar-collapse -->
</nav>