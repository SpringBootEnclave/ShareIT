<%@page import="model.bean.Comment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%
	Comment cmt = (Comment) session.getAttribute("cmt");
	int newsid = (int) session.getAttribute("newsidCmtCha");
%>
<div class="comment-list" id="list-cmt">
	<div class="single-comment justify-content-between d-flex">

		<div class="user justify-content-between d-flex">
			<div class="thumb">
				<img src="<%=request.getContextPath()%>/templates/public/img/1.png"
					alt="">
			</div>
			<div class="desc">
				<h5>
					<a href="#"><%=cmt.getUsername()%></a>
				</h5>
				<p class="date">Just now</p>
				<p class="comment"><%=cmt.getContent()%></p>
			</div>

		</div>
		<div class="reply-btn">
			<a href="javascript:void(0)" class="btn-reply text-uppercase"
				onclick="return xuLyCmtChild(<%=cmt.getId()%>, <%=newsid%>)">reply</a>
		</div>
	</div>
</div>

