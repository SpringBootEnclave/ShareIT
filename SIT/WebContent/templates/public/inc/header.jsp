<%@page import="util.StringUtil"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.CatDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<style type="text/css">
	.img-fluid{
		width: 200px;
		height: 120px;
	} 
</style>
<link
	href="<%=request.getContextPath() %>/templates/admin/assets/css/bootstrap.css"
	rel="stylesheet" /><!-- thêm vô để hiện css trang,hihi -->
<html lang="zxx" class="no-js">
	<head>
		<!-- Mobile Specific Meta -->
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<!-- Favicon-->
		<link rel="shortcut icon" href="<%=request.getContextPath() %>/templates/public/img/fav.png">
		<!-- Author Meta -->
		<meta name="author" content="colorlib">
		<!-- Meta Description -->
		<meta name="description" content="">
		<!-- Meta Keyword -->
		<meta name="keywords" content="">
		<!-- meta character set -->
		<meta charset="UTF-8">
		<!-- Site Title -->
		<title>ShareIT | VinaEnter</title>
		<link href="https://fonts.googleapis.com/css?family=Poppins:100,200,400,300,500,600,700" rel="stylesheet">
		<LINK REL="SHORTCUT ICON"  HREF="<%=request.getContextPath() %>/templates/public/img/logo1.png">
		<!--
		CSS
		============================================= -->
		<link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/linearicons.css">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/font-awesome.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/bootstrap.css">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/magnific-popup.css">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/nice-select.css">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/animate.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/owl.carousel.css">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/jquery-ui.css">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/main.css">
	</head>
	<body>
		<header>
			
			<div class="header-top">
				<div class="container">
					<div class="row">
						<div class="col-lg-6 col-md-6 col-sm-6 col-6 header-top-left no-padding">
							<ul>
								<li><a href="https://www.facebook.com/tranloc1997/"><i class="fa fa-facebook"></i></a></li>
								<li><a href="https://www.facebook.com/tranloc1997/"><i class="fa fa-twitter"></i></a></li>
								<li><a href="https://www.facebook.com/tranloc1997/"><i class="fa fa-dribbble"></i></a></li>
							</ul>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-6 header-top-right no-padding">
							<ul>
								<li><a href="tel:+84 978 17 11 97"><span class="lnr lnr-phone-handset"></span><span>+84 978 17 11 97</span></a></li>
								<li><a href="mailto:ketulanhlung4@gmail.com"><span class="lnr lnr-envelope"></span><span>ketulanhlung4@gmail.com</span></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="logo-wrap">
				<div class="container">
					<div class="row justify-content-between align-items-center">
						<div class="col-lg-4 col-md-4 col-sm-12 logo-left no-padding">
							<a href="<%=request.getContextPath() %>/public/index">
								<img class="img-fluid" style="font-size: " src="<%=request.getContextPath() %>/templates/public/img/logo1.png" alt="" width="150px" height="300px">
							</a>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-12 logo-right no-padding ads-banner">
							<img class="img-fluid" src="" alt="">
						</div>
					</div>
				</div>
			</div>
			<div class="container main-menu" id="main-menu">
				<div class="row align-items-center justify-content-between">
					<nav id="nav-menu-container">
						<ul class="nav-menu">
							<li class="menu-active"><a href="<%=request.getContextPath()%>/public/index">Trang chủ</a></li>
							<%
								CatDAO catDAO = new CatDAO();
								ArrayList<Category> catCha = catDAO.getItems();
									for(Category objCatCha : catCha){
							%>
							<li class="menu-has-children"><a href="<%=request.getContextPath()%>/public/cat/index?cidcha=<%=objCatCha.getCatId()%>"><%=objCatCha.getCatName() %></a>
									
									<ul><%
										ArrayList<Category> listCatChild = catDAO.getCatChild(objCatCha.getCatId());
											for (Category catChild : listCatChild) {
            									String urlCat = request.getContextPath() + "/public/cat/index/" + StringUtil.makeSlug(catChild.getCatName())
            									+ "-" + catChild.getCatId() + ".html";
									%>
									 
										<li><a href="<%=urlCat%>"><%=catChild.getCatName() %></a></li>
									
									<%} %></ul>
							</li>
							
							<%} %>
							
							
							<!-- <li class="menu-has-children"><a href="">Lập trình</a>
								<ul>
									<li><a href="standard-post.html">Standard Post</a></li>
								</ul>
							</li>
							<li class="menu-has-children"><a href="">Tâm sự coder</a>
								<ul>
									<li><a href="standard-post.html">Standard Post</a></li>
								</ul>
							</li> -->
						<li><a href="<%=request.getContextPath() %>/public/contact">Liên Hệ</a></li>
					</ul>
					</nav><!-- #nav-menu-container -->
					<div class="navbar-right">
						<form class="Search" action="<%=request.getContextPath() %>/search-news" method="post" id="form">
							<input type="text" class="form-control Search-box" required name="Search-box" id="Search-box" placeholder="Tìm kiếm...">
							<label for="Search-box" class="Search-box-label">
								<span class="lnr lnr-magnifier"></span>
							</label>
							<span class="Search-close">
								<span class="lnr lnr-cross"></span>
							</span>
						</form>
					</div>
				</div>
			</div>
		</header>