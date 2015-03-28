<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<!-- <a class="navbar-brand" href="#">PVR Cinemas</a> -->
	</div>

	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse navbar-ex1-collapse">

		<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_USERS">

			<ul class="nav navbar-nav">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><i
						class="glyphicon glyphicon-user white"></i> Role Management <b
						class="caret"></b> </a>
					<ul class="dropdown-menu">
						<li><a
							href="${pageContext.request.contextPath}/admin/adduser"
							title="Today's Lead List"><i
								class="glyphicon glyphicon-plus-sign white"></i> Add User </a></li>
						<li><a
							href="${pageContext.request.contextPath}/admin/userlist"
							title="Pending Leads"><i
								class="glyphicon glyphicon-pencil white"></i> Edit User/ Roles </a></li>

						<%-- 	<li><a
					href="${pageContext.request.contextPath}/admin/userroles"
					title="Pending Leads"><i
						class="glyphicon glyphicon-pencil white"></i> User Roles </a></li> --%>
					</ul></li>
			</ul>

		</sec:authorize>


		<sec:authorize
			ifAnyGranted="ROLE_READ_CINEMA,ROLE_EDIT_CINEMA,ROLE_ADMIN,ROLE_READ_CITY,ROLE_EDIT_CITY,ROLE_READ_CINEMA_DETAIL,ROLE_EDIT_CINEMA_DETAIL,ROLE_READ_LICENSE,ROLE_EDIT_LICENSE,ROLE_EDIT_MOVIE,ROLE_READ_MOVIE">
			<ul class="nav navbar-nav">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><i
						class="glyphicon glyphicon-screenshot white"></i> Cinema <b
						class="caret"></b></a>
					<ul class="dropdown-menu">
						<sec:authorize
							ifAnyGranted="ROLE_READ_MOVIE,ROLE_EDIT_MOVIE,ROLE_ADMIN">
							<li><a
								href="${pageContext.request.contextPath}/admin/movieList"
								title="Pending Leads"><i
									class="glyphicon glyphicon-pencil white"></i> View/Edit Movie
									Details </a></li>
									
<%-- 									<li><a
								href="${pageContext.request.contextPath}/admin/movieFileList"
								title="Pending Leads"><i
									class="glyphicon glyphicon-pencil white"></i> Add/View Movie Files </a></li>
 --%>						</sec:authorize>
						<sec:authorize ifAnyGranted="ROLE_EDIT_CITY,ROLE_ADMIN">
							<li><a
								href="${pageContext.request.contextPath}/admin/addCity"
								title="Today's Lead List"><i
									class="glyphicon glyphicon-plus-sign white"></i> Add City </a></li>
						</sec:authorize>
						<sec:authorize
							ifAnyGranted="ROLE_EDIT_CITY,ROLE_ADMIN,ROLE_READ_CITY">
							<li><a
								href="${pageContext.request.contextPath}/admin/cityList"
								title="Pending Leads"><i
									class="glyphicon glyphicon-pencil white"></i> View/Edit City </a></li>
						</sec:authorize>
						<sec:authorize ifAnyGranted="ROLE_EDIT_CINEMA_DETAIL,ROLE_ADMIN">

							<li><a
								href="${pageContext.request.contextPath}/admin/addcinemadetail"
								title="Today's Lead List"><i
									class="glyphicon glyphicon-plus-sign white"></i> Add Cinema
									Detail</a></li>
						</sec:authorize>
						<sec:authorize
							ifAnyGranted="ROLE_EDIT_CINEMA_DETAIL,ROLE_ADMIN,ROLE_READ_CINEMA_DETAIL">
							<li><a
								href="${pageContext.request.contextPath}/admin/cinedetaillist"
								title="Pending Leads"><i
									class="glyphicon glyphicon-pencil white"></i> View/Edit Cinema
									Detail</a></li>
						</sec:authorize>
						<sec:authorize ifAnyGranted="ROLE_EDIT_CINEMA,ROLE_ADMIN">
							<li><a
								href="${pageContext.request.contextPath}/admin/addcinema"
								title="Today's Lead List"><i
									class="glyphicon glyphicon-plus-sign white"></i> Add Cinema</a></li>
						</sec:authorize>
						<sec:authorize
							ifAnyGranted="ROLE_EDIT_CINEMA,ROLE_ADMIN,ROLE_READ_CINEMA">
							<li><a
								href="${pageContext.request.contextPath}/admin/cinemalist"
								title="Pending Leads"><i
									class="glyphicon glyphicon-pencil white"></i> View/Edit Cinema</a></li>
						</sec:authorize>
						<sec:authorize ifAnyGranted="ROLE_EDIT_LICENSE,ROLE_ADMIN">

							<li><a
								href="${pageContext.request.contextPath}/admin/addlicense"
								title="Today's Lead List"><i
									class="glyphicon glyphicon-plus-sign white"></i>Add License</a></li>
						</sec:authorize>
						<sec:authorize
							ifAnyGranted="ROLE_EDIT_LICENSE,ROLE_ADMIN,ROLE_READ_LICENSE">
							<li><a
								href="${pageContext.request.contextPath}/admin/licenselist"
								title="Pending Leads"><i
									class="glyphicon glyphicon-pencil white"></i> View/Edit License</a></li>
						</sec:authorize>
						
						<sec:authorize ifAnyGranted="ROLE_EDIT_UPCOMING_CINEMA,ROLE_ADMIN">

							<li><a
								href="${pageContext.request.contextPath}/admin/addupcomingcinema"
								title="Upcoming Cinema"><i
									class="glyphicon glyphicon-plus-sign white"></i>Add Upcoming Cinema</a></li>
						</sec:authorize>
						<sec:authorize
							ifAnyGranted="ROLE_EDIT_UPCOMING_CINEMA,ROLE_ADMIN,ROLE_READ_UPCOMING_CINEMA">
							<li><a
								href="${pageContext.request.contextPath}/admin/upcomingcinemalist"
								title="Upcoming Cinema"><i
									class="glyphicon glyphicon-pencil white"></i> View/Edit Upcoming Cinema</a></li>
						</sec:authorize>
					</ul></li>
			</ul>
		</sec:authorize>

		<sec:authorize ifAnyGranted="ROLE_READ_DEAL,ROLE_EDIT_DEAL,ROLE_ADMIN">

			<ul class="nav navbar-nav">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><i
						class="glyphicon glyphicon-thumbs-up white"></i> Deal <b
						class="caret"></b> </a>
					<ul class="dropdown-menu">
						<sec:authorize ifAnyGranted="ROLE_EDIT_DEAL,ROLE_ADMIN">
							<li><a
								href="${pageContext.request.contextPath}/admin/addDeal"
								title="Today's Lead List"><i
									class="glyphicon glyphicon-plus-sign white"></i> Add Deal </a></li>
						</sec:authorize>
						
						<li><a
							href="${pageContext.request.contextPath}/admin/dealList"
							title="Pending Leads"><i
								class="glyphicon glyphicon-pencil white"></i> View/Edit Deal </a></li>
					</ul></li>
			</ul>
		</sec:authorize>

		
		<sec:authorize
			ifAnyGranted="ROLE_READ_PREBOOK,ROLE_EDIT_PREBOOK,ROLE_ADMIN">
			<ul class="nav navbar-nav">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><i
						class="glyphicon glyphicon-tower white"></i> Prebook Details <b
						class="caret"></b></a>
					<ul class="dropdown-menu">
						<sec:authorize ifAnyGranted="ROLE_EDIT_PREBOOK,ROLE_ADMIN">
							<li><a
								href="${pageContext.request.contextPath}/admin/addprebook"
								title="Today's Lead List"><i
									class="glyphicon glyphicon-plus-sign white"></i> Mark Prebook</a></li>
						</sec:authorize>
						<sec:authorize
							ifAnyGranted="ROLE_READ_PREBOOK,ROLE_EDIT_PREBOOK,ROLE_ADMIN">
							<li><a
								href="${pageContext.request.contextPath}/admin/prelist"
								title="Pending Leads"><i
									class="glyphicon glyphicon-pencil white"></i> View/Edit
									Prebooked Movies</a></li>


							<li><a
								href="${pageContext.request.contextPath}/admin/prebookedcinemas"
								title="Pending Leads"><i
									class="glyphicon glyphicon-pencil white"></i> View/Send SMS
									Release Date Change - Movie</a></li>

							<li><a
								href="${pageContext.request.contextPath}/admin/prebookedusers"
								title="Pending Leads"><i
									class="glyphicon glyphicon-pencil white"></i> Prebooked Users -
									Bookings & Refunds</a></li>
						</sec:authorize>
					</ul></li>

			</ul>
		</sec:authorize>


		<sec:authorize
			ifAnyGranted="ROLE_PRE_REPORT,ROLE_BULK_REPORT,ROLE_GIFT_REPORT,ROLE_FNB_REPORT,ROLE_BOOKING_REPORT,ROLE_CITRUS_REPORT,ROLE_ADMIN,ROLE_GIFTCARD_REDEEM_REPORT,ROLE_DC_REPORT,ROLE_EWALLET_REPORT,ROLE_PROMO_REPORT,ROLE_MCOUPON_REPORT,USER_PROFILE_REPORT">


			<ul class="nav navbar-nav">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><i
						class="glyphicon glyphicon-list-alt white"></i> Reports <b
						class="caret"></b> </a>
					<ul class="dropdown-menu">
						<sec:authorize ifAnyGranted="USER_PROFILE_REPORT,ROLE_ADMIN">

							<li><a
								href="${pageContext.request.contextPath}/admin/actualuserreport"
								title="Today's Lead List"><i
									class="glyphicon glyphicon-list-alt white"></i> User Profile
									Details </a></li>
						</sec:authorize>

						<sec:authorize ifAnyGranted="ROLE_BOOKING_REPORT,ROLE_ADMIN">

							<li><a
								href="${pageContext.request.contextPath}/admin/bookingreport"
								title="Today's Lead List"><i
									class="glyphicon glyphicon-list-alt white"></i> Booking Details
							</a></li>
						</sec:authorize>

						<sec:authorize ifAnyGranted="ROLE_CITRUS_REPORT,ROLE_ADMIN">

							<li><a
								href="${pageContext.request.contextPath}/admin/citrusreport"
								title="Today's Lead List"><i
									class="glyphicon glyphicon-list-alt white"></i> Payment -
									Citrus </a></li>
						</sec:authorize>

						<sec:authorize
							ifAnyGranted="ROLE_GIFTCARD_REDEEM_REPORT,ROLE_ADMIN">

							<li><a
								href="${pageContext.request.contextPath}/admin/gcredeemreport"
								title="Today's Lead List"><i
									class="glyphicon glyphicon-list-alt white"></i> Payment - Gift
									Card Redemption </a></li>
						</sec:authorize>

						<sec:authorize ifAnyGranted="ROLE_MCOUPON_REPORT,ROLE_ADMIN">

							<li><a
								href="${pageContext.request.contextPath}/admin/mcouponreport"
								title="Today's Lead List"><i
									class="glyphicon glyphicon-list-alt white"></i> Payment -
									MCoupon </a></li>
						</sec:authorize>

						<sec:authorize ifAnyGranted="ROLE_PROMO_REPORT,ROLE_ADMIN">

							<li><a
								href="${pageContext.request.contextPath}/admin/promoreport"
								title="Today's Lead List"><i
									class="glyphicon glyphicon-list-alt white"></i> Payment - Promo
									Code </a></li>
						</sec:authorize>

						<sec:authorize ifAnyGranted="ROLE_EWALLET_REPORT,ROLE_ADMIN">

							<li><a
								href="${pageContext.request.contextPath}/admin/ewalletreport"
								title="Today's Lead List"><i
									class="glyphicon glyphicon-list-alt white"></i> Payment -
									EWallet </a></li>
						</sec:authorize>

						<sec:authorize ifAnyGranted="ROLE_DC_REPORT,ROLE_ADMIN">

							<li><a
								href="${pageContext.request.contextPath}/admin/dcreport"
								title="Today's Lead List"><i
									class="glyphicon glyphicon-list-alt white"></i> Payment -
									Direct Cut</a></li>
						</sec:authorize>
						<sec:authorize ifAnyGranted="ROLE_PRE_REPORT,ROLE_ADMIN">
							<li><a
								href="${pageContext.request.contextPath}/admin/preusersreport"
								title="Today's Lead List"><i
									class="glyphicon glyphicon-list-alt white"></i> Pre Bookings </a></li>
						</sec:authorize>
						<sec:authorize ifAnyGranted="ROLE_BULK_REPORT,ROLE_ADMIN">
							<li><a
								href="${pageContext.request.contextPath}/admin/bulkreport"
								title="Today's Lead List"><i
									class="glyphicon glyphicon-list-alt white"></i> Bulk Bookings </a></li>
						</sec:authorize>
						<sec:authorize
							ifAnyGranted="ROLE_GIFT_REPORT,ROLE_EDIT_GIFT,ROLE_ADMIN">
							<li><a
								href="${pageContext.request.contextPath}/admin/gcpurchasereport"
								title="Today's Lead List"><i
									class="glyphicon glyphicon-list-alt white"></i> Gift Card
									Purchase </a></li>
						</sec:authorize>
						<sec:authorize ifAnyGranted="ROLE_FNB_REPORT,ROLE_ADMIN">

							<li><a
								href="${pageContext.request.contextPath}/admin/fandbpurchasereport"
								title="Today's Lead List"><i
									class="glyphicon glyphicon-list-alt white"></i> F&B Purchase </a></li>
						</sec:authorize>

						<sec:authorize ifAnyGranted="ROLE_FEEDBACK_REPORT,ROLE_ADMIN">

							<li><a
								href="${pageContext.request.contextPath}/admin/feedbackreport"
								title="Today's Lead List"><i
									class="glyphicon glyphicon-list-alt white"></i> Feedback </a></li>
						</sec:authorize>

						<sec:authorize ifAnyGranted="ROLE_NOTIFICATION_REPORT,ROLE_ADMIN">

							<li><a
								href="${pageContext.request.contextPath}/admin/notificationreport"
								title="Today's Lead List"><i
									class="glyphicon glyphicon-list-alt white"></i> Push
									Notification </a></li>
						</sec:authorize>





					</ul></li>

			</ul>
		</sec:authorize>
		<sec:authorize ifAnyGranted="ROLE_READ_GIFT,ROLE_EDIT_GIFT,ROLE_ADMIN">


			<ul class="nav navbar-nav">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><i
						class="glyphicon glyphicon-gift white"></i> Gift Card <b
						class="caret"></b> </a>

					<ul class="dropdown-menu">
						<sec:authorize ifAnyGranted="ROLE_EDIT_GIFT,ROLE_ADMIN">
							<li><a
								href="${pageContext.request.contextPath}/admin/addgiftcard"
								title="Today's Lead List"><i
									class="glyphicon glyphicon-plus-sign white"></i> Add Gift Card
							</a></li>
						</sec:authorize>
						<li><a
							href="${pageContext.request.contextPath}/admin/giftcardlist"
							title="Today's Lead List"><i
								class="glyphicon glyphicon-pencil white"></i> View/Edit Gift
								Card </a></li>


					</ul></li>

			</ul>
		</sec:authorize>
		

				<sec:authorize ifAnyGranted="ROLE_EDIT_OFFERS,ROLE_ADMIN">

			<ul class="nav navbar-nav">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><i
						class="glyphicon glyphicon-thumbs-up white"></i> Offers <b
						class="caret"></b> </a>
					<ul class="dropdown-menu">
						
							<li><a
								href="${pageContext.request.contextPath}/admin/editmiles"
								title="Miles n More"><i
									class="glyphicon glyphicon-plus-sign white"></i> Edit Miles n More </a></li>
						
							<li><a
								href="${pageContext.request.contextPath}/admin/editkotaksat"
								title="Miles n More"><i
									class="glyphicon glyphicon-plus-sign white"></i> Edit Kotak Saturday </a></li>
						
					</ul></li>
</ul></sec:authorize>
		<sec:authorize ifAnyGranted="ROLE_EDIT_CORPORATE,ROLE_READ_CORPORATE,ROLE_ADMIN">
			<ul class="nav navbar-nav">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><i
						class="glyphicon glyphicon-gift white"></i> C-Wallet <b
						class="caret"></b> </a>

					<ul class="dropdown-menu">
					<sec:authorize ifAnyGranted="ROLE_EDIT_CORPORATE,ROLE_ADMIN">
						<li><a
								href="${pageContext.request.contextPath}/admin/addcorporate"
								title="Today's Lead List"><i
									class="glyphicon glyphicon-plus-sign white"></i> Add Corporate
							</a></li>
							</sec:authorize>
						<li><a
							href="${pageContext.request.contextPath}/admin/corporatelist"
							title="Today's Lead List"><i
								class="glyphicon glyphicon-pencil white"></i> View/Edit Corporate </a></li>


					</ul></li>


			</ul>
		</sec:authorize>

		<sec:authorize ifAnyGranted="ROLE_READ_BANNER,ROLE_EDIT_BANNER,ROLE_READ_NEWS,ROLE_EDIT_NEWS,ROLE_READ_EVENT,ROLE_EDIT_EVENT,ROLE_READ_TAG,ROLE_EDIT_TAG,ROLE_READ_CONFIG,ROLE_EDIT_CONFIG,ROLE_READ_FAQ,ROLE_EDIT_FAQ,ROLE_ADMIN,ROLE_READ_TNC,ROLE_EDIT_TNC,ROLE_READ_PRIVACY,ROLE_EDIT_PRIVACY,ROLE_SEND_NOTIFICATION,ROLE_READ_AREA_COLOR,ROLE_EDIT_AREA_COLOR">


			<ul class="nav navbar-nav">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><i
						class="glyphicon glyphicon-gift white"></i> Others <b
						class="caret"></b> </a>

					<ul class="dropdown-menu">
						<sec:authorize
						ifAnyGranted="ROLE_READ_CONFIG,ROLE_EDIT_CONFIG,ROLE_ADMIN">
									<li><a
										href="${pageContext.request.contextPath}/admin/configList"
										title="Pending Leads"><i
											class="glyphicon glyphicon-pencil white"></i> View/Edit Config </a></li>
								
						</sec:authorize>
						
					
						<sec:authorize ifAnyGranted="ROLE_EDIT_FAQ,ROLE_ADMIN">
							<li><a
								href="${pageContext.request.contextPath}/admin/addfaq"
								title="Today's Lead List"><i
									class="glyphicon glyphicon-plus-sign white"></i> Add FAQ </a></li>
						</sec:authorize>
						<sec:authorize
							ifAnyGranted="ROLE_READ_FAQ,ROLE_ADMIN,ROLE_EDIT_FAQ">
							<li><a
								href="${pageContext.request.contextPath}/admin/faqlist"
								title="Today's Lead List"><i
									class="glyphicon glyphicon-pencil white"></i> View/Edit FAQ </a></li>
						</sec:authorize>
						<sec:authorize
							ifAnyGranted="ROLE_READ_TNC,ROLE_ADMIN,ROLE_EDIT_TNC">
							<li><a
								href="${pageContext.request.contextPath}/admin/tnclist"
								title="Today's Lead List"><i
									class="glyphicon glyphicon-pencil white"></i> View/Edit Terms
									and Conditions </a></li>
						</sec:authorize>
						<sec:authorize
							ifAnyGranted="ROLE_READ_PRIVACY,ROLE_ADMIN,ROLE_EDIT_PRIVACY">
							<li><a
								href="${pageContext.request.contextPath}/admin/privacylist"
								title="Today's Lead List"><i
									class="glyphicon glyphicon-pencil white"></i> View/Edit Privacy
									Policy </a></li>
						</sec:authorize>
						<sec:authorize ifAnyGranted="ROLE_SEND_NOTIFICATION,ROLE_ADMIN">
							<li><a
								href="${pageContext.request.contextPath}/admin/addpushnotification"
								title="Today's Lead List"><i
									class="glyphicon glyphicon-pencil white"></i> Send Push
									Notification </a></li>
						</sec:authorize>
						<sec:authorize ifAnyGranted="ROLE_EDIT_AREA_COLOR,ROLE_ADMIN">
							<li><a
								href="${pageContext.request.contextPath}/admin/addAreaColor"
								title="Today's Lead List"><i
									class="glyphicon glyphicon-plus-sign white"></i> Add Area Color
							</a></li>
						</sec:authorize>
						<sec:authorize
							ifAnyGranted="ROLE_READ_AREA_COLOR,ROLE_ADMIN,ROLE_EDIT_AREA_COLOR">
							<li><a
								href="${pageContext.request.contextPath}/admin/areaColorList"
								title="Today's Lead List"><i
									class="glyphicon glyphicon-pencil white"></i> View/Edit Area
									Color </a></li>
						</sec:authorize>
						
						
						 <sec:authorize ifAnyGranted="ROLE_EDIT_TAG,ROLE_ADMIN">
							<li><a
								href="${pageContext.request.contextPath}/admin/addtag"
								title="Add Meta Tag"><i
									class="glyphicon glyphicon-plus-sign white"></i> Add Meta Tag
							</a></li>
						</sec:authorize>
						<sec:authorize
							ifAnyGranted="ROLE_READ_TAG,ROLE_ADMIN,ROLE_EDIT_TAG">
							<li><a
								href="${pageContext.request.contextPath}/admin/taglist"
								title="Edit Meta Tag"><i
									class="glyphicon glyphicon-pencil white"></i> View/Edit Meta Tag </a></li>
						</sec:authorize>
						
						 <sec:authorize ifAnyGranted="ROLE_EDIT_EVENT,ROLE_ADMIN">
							<li><a
								href="${pageContext.request.contextPath}/admin/addevent"
								title="Add Event"><i
									class="glyphicon glyphicon-plus-sign white"></i> Add Event
							</a></li>
						</sec:authorize>
						<sec:authorize
							ifAnyGranted="ROLE_READ_EVENT,ROLE_ADMIN,ROLE_EDIT_EVENT">
							<li><a
								href="${pageContext.request.contextPath}/admin/eventlist"
								title="Edit Event"><i
									class="glyphicon glyphicon-pencil white"></i> View/Edit Event </a></li>
						</sec:authorize>
						
						<sec:authorize ifAnyGranted="ROLE_EDIT_NEWS,ROLE_ADMIN">
							<li><a
								href="${pageContext.request.contextPath}/admin/addnews"
								title="Add News"><i
									class="glyphicon glyphicon-plus-sign white"></i> Add News
							</a></li>
						</sec:authorize>
						<sec:authorize
							ifAnyGranted="ROLE_READ_NEWS,ROLE_ADMIN,ROLE_EDIT_NEWS">
							<li><a
								href="${pageContext.request.contextPath}/admin/newslist"
								title="Edit News"><i
									class="glyphicon glyphicon-pencil white"></i> View/Edit News </a></li>
						</sec:authorize>
						
						<sec:authorize ifAnyGranted="ROLE_EDIT_BANNER,ROLE_ADMIN">
							<li><a
								href="${pageContext.request.contextPath}/admin/addbanner"
								title="Add Banner"><i
									class="glyphicon glyphicon-plus-sign white"></i> Add Static Banner
							</a></li>
						</sec:authorize>
						<sec:authorize
							ifAnyGranted="ROLE_READ_BANNER,ROLE_ADMIN,ROLE_EDIT_BANNER">
							<li><a
								href="${pageContext.request.contextPath}/admin/bannerlist"
								title="Edit Banner"><i
									class="glyphicon glyphicon-pencil white"></i> View/Edit Static Banner </a></li>
						</sec:authorize>       

						<%-- 			<li><a
							href="${pageContext.request.contextPath}/admin/addcityemailtemplate"
							title="Today's Lead List"><i
								class="glyphicon glyphicon-pencil white"></i> Add Email Template - City </a></li>
								
									<li><a
							href="${pageContext.request.contextPath}/admin/emailCityTemplateList"
							title="Today's Lead List"><i
								class="glyphicon glyphicon-pencil white"></i>  View/Edit Email Template - City </a></li> --%>

					
					</ul></li>

			</ul>
		</sec:authorize>
		
		<sec:authorize ifAnyGranted="ROLE_EDIT_FINNOTICE,ROLE_READ_FINNOTICE,ROLE_ADMIN,ROLE_EDIT_FINREPORT,ROLE_READ_FINREPORT,ROLE_EDIT_FINSHARE,ROLE_READ_FINSHARE">

			<ul class="nav navbar-nav">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><i
						class="glyphicon glyphicon-thumbs-up white"></i> Financials <b
						class="caret"></b> </a>
					<ul class="dropdown-menu">
						
							<sec:authorize ifAnyGranted="ROLE_EDIT_FINSHARE,ROLE_ADMIN">
							<li><a
								href="${pageContext.request.contextPath}/admin/addfinanceshare"
								title="Add Notice"><i
									class="glyphicon glyphicon-plus-sign white"></i> Add Shareholding Pattern
							</a></li>
						</sec:authorize>
						<sec:authorize
							ifAnyGranted="ROLE_READ_FINSHARE,ROLE_ADMIN,ROLE_EDIT_FINSHARE">
							<li><a
								href="${pageContext.request.contextPath}/admin/financesharelist"
								title="Edit Notice"><i
									class="glyphicon glyphicon-pencil white"></i> View/Edit Shareholding Pattern </a></li>
						</sec:authorize>
							<sec:authorize ifAnyGranted="ROLE_EDIT_FINAREPORT,ROLE_ADMIN">
							<li><a
								href="${pageContext.request.contextPath}/admin/addfinanceareport"
								title="Add Notice"><i
									class="glyphicon glyphicon-plus-sign white"></i> Add Annual Report
							</a></li>
						</sec:authorize>
						
						<sec:authorize
							ifAnyGranted="ROLE_READ_FINAREPORT,ROLE_ADMIN,ROLE_EDIT_FINAREPORT">
							<li><a
								href="${pageContext.request.contextPath}/admin/financeareportlist"
								title="Edit Notice"><i
									class="glyphicon glyphicon-pencil white"></i> View/Edit Annual Reports </a></li>
						</sec:authorize> 
						
						
						
							<sec:authorize ifAnyGranted="ROLE_EDIT_FINREPORT,ROLE_ADMIN">
							<li><a
								href="${pageContext.request.contextPath}/admin/addfinanceqreport"
								title="Add Notice"><i
									class="glyphicon glyphicon-plus-sign white"></i> Add Quaterly Report
							</a></li>
						</sec:authorize>
						
						<sec:authorize
							ifAnyGranted="ROLE_READ_FINREPORT,ROLE_ADMIN,ROLE_EDIT_FINREPORT">
							<li><a
								href="${pageContext.request.contextPath}/admin/financeqreportlist"
								title="Edit Notice"><i
									class="glyphicon glyphicon-pencil white"></i> View/Edit Quaterly Reports </a></li>
						</sec:authorize> 
							<sec:authorize ifAnyGranted="ROLE_EDIT_FINNOTICE,ROLE_ADMIN">
							<li><a
								href="${pageContext.request.contextPath}/admin/addfinancenotice"
								title="Add Notice"><i
									class="glyphicon glyphicon-plus-sign white"></i> Add Notice
							</a></li>
						</sec:authorize>
						<sec:authorize
							ifAnyGranted="ROLE_READ_FINNOTICE,ROLE_ADMIN,ROLE_EDIT_FINNOTICE">
							<li><a
								href="${pageContext.request.contextPath}/admin/financenoticelist"
								title="Edit Notice"><i
									class="glyphicon glyphicon-pencil white"></i> View/Edit Notice </a></li>
						</sec:authorize>      
						
					</ul></li>
		</ul></sec:authorize>
		
		<%-- 	<sec:authorize ifAnyGranted="ROLE_READ_MULTIPAYMENT,ROLE_ADMIN,ROLE_EDIT_MULTIPAYMENT">

			<ul class="nav navbar-nav">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><i
						class="glyphicon glyphicon-thumbs-up white"></i> Payments <b
						class="caret"></b> </a>
					<ul class="dropdown-menu">
						<sec:authorize
							ifAnyGranted="ROLE_EDIT_MULTIPAYMENT,ROLE_ADMIN">
							<li><a
								href="${pageContext.request.contextPath}/admin/addmultipayment"
								title="Add Multi Payment"><i
									class="glyphicon glyphicon-plus-sign white"></i> Add Multi Payment </a></li>
						</sec:authorize>
							<li><a
								href="${pageContext.request.contextPath}/admin/multipaymentlist"
								title="Edit Multi Payment"><i
									class="glyphicon glyphicon-plus-sign white"></i> Edit Multi Payment </a></li>
						
					</ul></li>
</ul></sec:authorize>

 --%>		

		<ul class="nav navbar-nav navbar-right">
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"><i
					class="glyphicon glyphicon-wrench white"></i> Settings <b
					class="caret"></b> </a>
				<ul class="dropdown-menu">
					<li><a
						href="${pageContext.request.contextPath}/admin/changepassword"
						title="Change Password"><i
							class="glyphicon glyphicon-copyright-mark white"></i> Change
							Password </a></li>
					<li><a href="<c:url value="/j_spring_security_logout" />"
						title="Logout"><i class="glyphicon glyphicon-log-out white"></i>
							Logout </a></li>


				</ul></li>
		</ul>


	</div>

	<!-- /.navbar-collapse -->
</nav>