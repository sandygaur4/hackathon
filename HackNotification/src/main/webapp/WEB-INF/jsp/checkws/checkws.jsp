<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CheckWS</title>
<!-- Bootstrap -->
<link href='${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css' rel="stylesheet"
	media="screen">
	<script src='${pageContext.request.contextPath}/jquery/jquery.min.js'></script>
<script src='${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js'></script>

</head>
<body>

<h3>'${pageContext.request.contextPath}/userdata'</h3>
        <form action="${pageContext.request.contextPath}/userdata" method="POST" enctype="multipart/form-data">
         	 deviceid ::<input type="text" name="deviceid" value="sdkahkhkjjkjka" />
			<br>
        	 lat ::<input type="text" name="lat" value="76.1092" />
			<br>
			 lng ::<input type="text" name="lng" value="38.232" />
			<br>
			 notifyid ::<input type="text" name="notifyid" value="jbwjhhj2k3h4k" />
			<br>
			 platform(ANDROID,IOS,WINDOWS) ::<input type="text" name="platform" value="ANDROID" />
			<br>			
			 param1(Not Required - used for later) ::<input type="text" name="param1" value="" />
			<br>
			 param2(Not Required - used for later) ::<input type="text" name="param2" value="" />
			<br>
			 param3(Not Required - used for later) ::<input type="text" name="param3" value="" />
			<br>
		    <input type="submit" value="Submit" />
        </form>     
	<hr>
</body>
</html>