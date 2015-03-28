<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
function disableCntrls(e){               
    if(e.keyCode==17 || e.keyCode==93){                  
           alert('Copy Paste not allowed.');
           return false;                   
    }
}
 
function disContextMenu(e){
     $(e).bind("contextmenu",function(e){
           return false;
      });
}
</script>
<div class="text-center">
	<h1>Change Password</h1>
</div>
<c:if test="${addError!=null}">
	<div class="alert alert-success text-center">${addError}</div>
</c:if>
<c:if test="${addMsg!=null}">
	<div class="alert alert-success text-center">${addMsg}</div>
</c:if>
<c:url var="addCinema" value="/admin/cpassword" />
<form:form class="form-horizontal" role="form" method="post"
	action="${addCinema}" name="frm" modelAttribute="userForm" enctype="multipart/form-data">
	<div class="form-group">
	<div class="col-lg-6">
	<form:hidden path="login"/>
	<form:hidden path="id"/>
	<form:hidden path="userName"/>
	<form:hidden path="mobileNumber"/>
	<form:hidden path="password"/>
	
	
	
	<%-- <form:errors path="*" cssClass="errorblock" element="div" /> --%>
	</div></div>
	<div class="form-group">
		<label for="uPassword" class="col-lg-2 control-label">Old Password <span style="color: red;">*</span></label>
		<div class="col-lg-6">
			<font color="red"><form:errors path="uPassword"/></font>
			<form:input type="password" class="form-control" id="uPassword"
				placeholder="Old Password" path="uPassword" maxlength="20" onkeydown="return disableCntrls(event);" onmousedown="disContextMenu(this);"/>
		</div>
	</div>
	<div class="form-group">
		<label for="newPassword" class="col-lg-2 control-label">New Password <span style="color: red;">*</span></label>
		<div class="col-lg-6">
			<font color="red"><form:errors path="newPassword" cssClass="error"/></font>
			<form:input type="password" class="form-control" id="newPassword"
				placeholder="New Password" path="newPassword" maxlength="20" onkeydown="return disableCntrls(event);" onmousedown="disContextMenu(this);"/>
		</div>
	</div>
	
	<div class="form-group">
		<label for="confirmPassword" class="col-lg-2 control-label">Confirm Password <span style="color: red;">*</span></label>
		<div class="col-lg-6">
			<font color="red"><form:errors path="confirmPassword" /></font>
			<form:input type="password" class="form-control" id="confirmPassword"
				placeholder="Confirm Password" path="confirmPassword" maxlength="20" onkeydown="return disableCntrls(event);" onmousedown="disContextMenu(this);"/>
		</div>
	</div>
		
	<div class="form-group">
		<div class="col-lg-offset-2 col-lg-10">
			<button type="submit" class="btn btn-default">Change Password</button>
		</div>
	</div>
</form:form>
