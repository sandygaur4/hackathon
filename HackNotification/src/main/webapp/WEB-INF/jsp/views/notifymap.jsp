    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script> 
<script type="text/javascript" src="scripts/downloadxml.js"></script>

<script type="text/javascript"> 
     // global "map" variable
      var map1 = null;
      var marker = null;
	  var latlng1 = null;

	  var infowindow = new google.maps.InfoWindow(
  { 
    size: new google.maps.Size(150,50)
  });


// A function to create the marker and set up the event window function 
function createMarker(latlng, name, html) {
    var contentString = html;
   var pinColor = "77cbbc";
    var pinImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pinColor,
        new google.maps.Size(21, 34),
        new google.maps.Point(0,0),
        new google.maps.Point(10, 34));

      /* var marker=new google.maps.Marker({
		  position: new google.maps.LatLng(28.63689, 77.24092),
		  });*/
		  
		 
    var marker = new google.maps.Marker({
        position: latlng,
        map: map1,
		icon: pinImage,
        zIndex: Math.round(latlng.lat()*-100000)<<5
        });

       latlng1 = latlng; 
	   google.maps.event.addListener(marker, 'click', function() {
        infowindow.setContent(contentString); 
        infowindow.open(map1,marker);
        });
    google.maps.event.trigger(marker, 'click'); 
    document.forms["notifyform"]["latlng"].value = latlng;
    return marker;

}

function initialize() {
  // create the map
  var myOptions = {
    zoom: 4,
    center: new google.maps.LatLng(28.63689, 77.24092),
    mapTypeControl: true,
    mapTypeControlOptions: {style: google.maps.MapTypeControlStyle.DROPDOWN_MENU},
    navigationControl: true,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  }
  map1 = new google.maps.Map(document.getElementById("map_canvas"),
                                myOptions);

google.maps.event.addListener(map1, 'click', function() {
        infowindow.close();
        });

  google.maps.event.addListener(map1, 'click', function(event) {
	//call function to create marker
         if (marker) {
            marker.setMap(null);
            marker = null;
         }
	 marker = createMarker(event.latLng,"name", "<b>Location</b><br>"+event.latLng);
	//alert(latlng1);
  });

}
google.maps.event.addDomListener(window, 'load', initialize);
</script> 
 
    <!-- you can use tables or divs for the overall layout --> 
    <table border="1"> 
      <tr> 
        <td> 
           <div id="map_canvas" style="width: 550px; height: 450px"></div> 
        </td> 
        <td valign="top" style="width:150px; text-decoration: underline; color: #4444ff;"> 
           <div id="side_bar"></div> 
        </td> 
      </tr> 
    </table> 
    <form action="${pageContext.request.contextPath}/admin/notify" method="POST" name="notifyform" enctype="multipart/form-data">
         	
        	 lat/long ::<input type="text" name="latlng" value="" />
			<br>
			
			radius ::<input type="text" name="radius" value="5" />
			<br>
		    <input type="submit" value="Submit" />
        </form>  
 
    <noscript><p><b>JavaScript must be enabled in order for you to use Google Maps.</b> 
      However, it seems JavaScript is either disabled or not supported by your browser. 
      To view Google Maps, enable JavaScript by changing your browser options, and then 
      try again.</p>
    </noscript> 
