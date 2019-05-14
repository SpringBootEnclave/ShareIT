<%@page import="model.bean.Comment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%
	Comment cmt1 = (Comment) session.getAttribute("objCmt1");
%>

<div class="single-comment justify-content-between d-flex">
	<div class="user justify-content-between d-flex">
		<div class="thumb">
			<img src="<%=request.getContextPath()%>/templates/public/img/1.png"
				alt="">
		</div>
		<div class="desc">
			<h5>
				<a href="#"><%=cmt1.getUsername()%></a>
			</h5>
			<p class="date">Just now</p>
			<p class="comment"><%=cmt1.getContent()%></p>
		</div>
	</div>
</div>

