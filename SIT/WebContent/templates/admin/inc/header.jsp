<%@page import="model.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>AdminCP | VinaEnter Edu | News</title>
<!-- BOOTSTRAP STYLES-->
<link
	href="<%=request.getContextPath() %>/templates/admin/assets/css/bootstrap.css"
	rel="stylesheet" />
<!-- FONTAWESOME STYLES-->
<link
	href="<%=request.getContextPath() %>/templates/admin/assets/css/font-awesome.css"
	rel="stylesheet" />
<!-- CUSTOM STYLES-->
<link
	href="<%=request.getContextPath() %>/templates/admin/assets/css/custom.css"
	rel="stylesheet" />
<!-- GOOGLE FONTS-->
<LINK REL="SHORTCUT ICON"  HREF="<%=request.getContextPath() %>/templates/public/img/logo1.png">	
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
	<%-- <script type="text/javascript"
	src="<%=request.getContextPath()%>/templates/admin/assets/ckeditor/ckeditor.js"></script> --%>
</head>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/templates/admin/assets/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/templates/admin/assets/js/jquery.validate.js"></script>
	
<body>
	<div id="wrapper">
		<nav class="navbar navbar-default navbar-cls-top " role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".sidebar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" style="color:red" href="<%=request.getContextPath() %>/admin/index">VinaEnter Edu</a>
			</div>
			<%
             	if(session.getAttribute("userInfo")!=null){
             		User userInfo = (User) session.getAttribute("userInfo");   	
            %>
			<div
				style="color: white; padding: 15px 50px 5px 50px; float: right; font-size: 16px;">
				Xin chào, <%=userInfo.getUsername() %><b></b> &nbsp; 
				<a href="<%=request.getContextPath() %>/auth/logout" class="btn btn-danger square-btn-adjust">Đăng xuất</a>
			</div>
			<%} %>
		</nav>
		<!-- /. NAV TOP  -->