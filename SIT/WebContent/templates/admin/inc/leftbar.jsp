<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<nav class="navbar-default navbar-side" role="navigation">
    <div class="sidebar-collapse">
        <ul class="nav" id="main-menu">
            <%-- <li class="text-center">
                <a href="<%=request.getContextPath() %>/admin/index"><img src="<%=request.getContextPath() %>/templates/admin/assets/img/1.jpg" class="user-image img-responsive" /></a>
            </li> --%>
            <li>
            	<a></a>
            </li>
            <li>
                <a id="index" href="<%=request.getContextPath()%>/admin/index/index.jsp"><i class="fa fa-dashboard fa-3x"></i> Trang chủ</a>
            </li>
            <li>
                <a id="category" href="<%=request.getContextPath()%>/admin/cat/index"><i class="fa fa-list fa-3x"></i> Quản lý danh mục</a>
            </li>
            <li>
                <a id="news" href="<%=request.getContextPath()%>/admin/news/index"><i class="fa fa-music fa-3x"></i> Quản lý tin tức</a>
            </li>
            <li>
                <a id="user" href="<%=request.getContextPath()%>/admin/user/index"><i class="fa fa-user fa-3x"></i> Quản lý người dùng</a>
            </li>
            <li>
                <a id="comment" href="<%=request.getContextPath()%>/admin/comment/index"><i class="fa fa-user fa-3x"></i> Quản lý bình luận</a>
            </li>
            <li>
                <a id="contact" href="<%=request.getContextPath()%>/admin/contact/index"><i class="fa fa-envelope fa-3x"></i> Quản lý liên hệ</a>
            </li>
        </ul>
    </div>
</nav>
<!-- /. NAV SIDE  -->