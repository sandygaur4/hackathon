<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Bootstrap -->
<link href='${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css' rel="stylesheet"
	media="screen">
	<link href='${pageContext.request.contextPath}/kendo/css/kendo.common.css' rel="stylesheet"
	media="screen">
	<link href='${pageContext.request.contextPath}/kendo/css/kendo.default.css' rel="stylesheet"
	media="screen">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/autocomp/css/jquery-ui.css" />
	
<%-- 	 <link rel="stylesheet" media="screen" href='${pageContext.request.contextPath}/css/bootstrap-wysihtml5.css'>

<script src='${pageContext.request.contextPath}/jquery/wysihtml5-0.3.0_rc2.js'></script>
<script src='${pageContext.request.contextPath}/jquery/jquery-1.7.1.min.js'></script>
<script src='${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js'></script>
<script src='${pageContext.request.contextPath}/jquery/bootstrap-wysihtml5-0.0.2.js'></script> 
	
 --%>	
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<!-- <style>

.error {
	color: #ff0000;
}
 
.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}


</style>
 --></head>
<body>

	<div class="container">
		<div class="row">
			<tiles:insertAttribute name="header" />
		</div>
		<div class="row" style="padding-top: 10px; padding-bottom: 10px"></div>
		<div class="row">
		<%-- 	<sec:authorize ifAnyGranted="ROLE_ADMIN" > --%>

				<tiles:insertAttribute name="menu" />

			
		</div>
		<div class="row">
			<div>
				<tiles:insertAttribute name="body" />
			</div>
		</div>
		<div class="row">
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
	


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<!-- <script src="//code.jquery.com/jquery.js"></script> -->
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	 <script src="${pageContext.request.contextPath}/kendo/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/kendo/js/kendo.web.min.js"></script>
	
	<script type="text/javascript">
     function isNumberKey(evt)
    {
    	//alert("hello");
       var charCode = (evt.which) ? evt.which : event.keyCode;
       if (charCode != 46 && charCode > 31 
         && (charCode < 48 || charCode > 57))
          return false;

       return true;
    }

    
 	 $(document).ready(function() {
	        // create DateTimePicker from input HTML element
	        $("#timeofevent1").kendoDatePicker({
	        	value:new Date()
	        }
	        );
	        $("#date1").kendoDatePicker({
	        	 format: "yyyy-MM-dd"
	        }
	        );
	        $("#date2").kendoDatePicker({
	        	 format: "yyyy-MM-dd"
	        }
	        );
	        $("#date3").kendoDatePicker({
	        	 format: "yyyy-MM-dd"
	        }
	        );
	        $("#date4").kendoDatePicker({
	        	 format: "yyyy-MM-dd"
	        }
	        );
	        $("#date5").kendoDatePicker({
	        	 format: "yyyy-MM-dd"
	        }
	        );
	        $("#date6").kendoDatePicker({
	        	 format: "yyyy-MM-dd"
	        }
	        );
	        $("#date7").kendoDatePicker({
	        	 format: "yyyy-MM-dd"
	        }
	        );
	        $("#validFrom").kendoDatePicker({
	        	 format: "yyyy-MM-dd"
	        }
	        );
	        $("#validTo").kendoDatePicker({
	        	 format: "yyyy-MM-dd"
	        }
	        );
	        $("#foodDiscValidFrom").kendoDatePicker({
	        	 format: "yyyy-MM-dd"
	        }
	        ); $("#foodDiscValidTo").kendoDatePicker({
	        	 format: "yyyy-MM-dd"
	        }
	        );
	        
	    });
	    
	    
function Validate() {
		var thumbsImageUrl = document.getElementById("thumbsImageUrl").value;
		var enlargeImageUrl = document.getElementById("enlargeImageUrl").value;
		alert("Please enter ThumbsImageUrl  File Extensions .jpg,.png,.jpeg"+thumbsImageUrl);
	
		if (thumbsImageUrl != '') {
			var checkimg = thumbsImageUrl.toLowerCase();
			if (!checkimg.match(/(\.jpg|\.png|\.JPG|\.PNG|\.jpeg|\.JPEG)$/)) {
				alert("Please enter ThumbsImageUrl  File Extensions .jpg,.png,.jpeg");
				document.getElementById("thumbsImageUrl").focus();
				return false;
			}
		}
		if (enlargeImageUrl != '') {
			var checkimg = enlargeImageUrl.toLowerCase();
			if (!checkimg.match(/(\.jpg|\.png|\.JPG|\.PNG|\.jpeg|\.JPEG)$/)) {
				alert("Please enter EnlargeImageUrl  File Extensions .jpg,.png,.jpeg");
				document.getElementById("enlargeImageUrl").focus();
				return false;
			}
		}

		
		return true;
	}	    
    

</script>
</body>
</html>