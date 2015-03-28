<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_USERS">
						<li><a href="${pageContext.request.contextPath}/admin/userlist"><i class="fa fa-users fa-fw"></i>
								Users</a></li>
						
						<li><a href="${pageContext.request.contextPath}/admin/getusercities"><i class="fa fa-dashboard fa-fw"></i>
								Cities</a></li>
								</sec:authorize>
						
					</ul>
				</div>
				<!-- /.sidebar-collapse -->
			</div>