<%@page import="model.dao.CommentDAO"%>
<%@page import="model.bean.Comment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp"%>

<div class="site-main-container">
	<!-- Start top-post Area -->
	<section class="top-post-area pt-10">
		<div class="container no-padding">
			<div class="row">
				<div class="col-lg-12">
					<!-- <div class="news-tracker-wrap">
								<h6><span>Tin tức mới nhất:</span>   <a href="#">Tin tức mới nhất</a></h6>
							</div> -->
				</div>
			</div>
		</div>
	</section>
	<!-- End top-post Area -->
	<!-- Start latest-post Area -->
	<section class="latest-post-area pb-120">
		<div class="container no-padding">
			<div class="row">
				<div class="col-lg-8 post-list">
					<!-- Start single-post Area -->
					<div class="single-post-wrap">
						<div class="content-wrap">
							<%	
								News news = (News) request.getAttribute("news");
							%>
							<div>
								<ul class="tags mt-10">
									<li><a href="#"><%=news.getObjCat().getCatName() %></a></li>
								</ul>
								<a href="#">
									<h3><%=news.getPreview_text() %></h3>
								</a>
								<ul class="meta pb-20">
									<li><a href="#"><span class="lnr lnr-calendar-full"></span><%=news.getDate_create() %></a></li>
									<li><a href="#"><span class="fa fa-tripadvisor"></span>   <%=news.getCounter() +1%></a></li>
								</ul>

								<blockquote><%=news.getDetail_text() %></blockquote>

								<div class="navigation-wrap justify-content-between d-flex">
									<!-- <a class="prev" href="#"><span class="lnr lnr-arrow-left"></span>Prev Post</a>
									<a class="next" href="#">Next Post<span class="lnr lnr-arrow-right"></span></a> -->
								</div>
							</div>


							<!-- 	------------------------------------------------- -->



							<div class="comment-sec-area">
								<div class="container">
									<div class="row flex-column">
										<%
								      		int countCmt = 0;
									      	if(request.getAttribute("countCmt") != null) {
									  			countCmt = (Integer)request.getAttribute("countCmt");
									      	}
								      	%>
										<h6 style="font-size: 20px"><%=countCmt %> Bình luận</h6>
										
										<%
											if(request.getAttribute("listCmt") != null) {
												  ArrayList<Comment> listCmt = (ArrayList<Comment>)request.getAttribute("listCmt");
												  if (listCmt.size() > 0) {
												  	for (Comment objCmt:listCmt) {
												  		int id = objCmt.getId();
										%>
										<div class="comment-list-cmt" id="list-cmt">
											<div class="single-comment justify-content-between d-flex">
												
												<div class="user justify-content-between d-flex">
														<div class="thumb">
															<img src="<%=request.getContextPath() %>/templates/public/img/1.png" alt="">
														</div>
														<div class="desc">
															<h5>
																<a href="#"><%=objCmt.getUsername() %></a>
															</h5>
															<p class="date"><%=objCmt.getTimecmt() %></p>
															<p class="comment"><%=objCmt.getContent() %></p>
														</div>
													
												</div>
												<div class="reply-btn">
													<a href="javascript:void(0)" class="btn-reply text-uppercase" onclick="return xuLyCmtChild(<%=objCmt.getId()%>, <%=news.getId()%>)">reply</a>
												</div>
											</div>
										</div>
										
										<%
											CommentDAO commentDAO = new CommentDAO();
											ArrayList<Comment> listCommentChild = commentDAO.getCommentChild(id);
												for (Comment commentChild : listCommentChild) {
										%>
										
										<div class="comment-list left-padding" id="list-cmt-reply-<%=objCmt.getId()%>">
											
											<div class="single-comment justify-content-between d-flex">
												<div class="user justify-content-between d-flex">
													<div class="thumb">
															<img src="<%=request.getContextPath() %>/templates/public/img/1.png" alt="">
													</div>
													<div class="desc">
														<h5>
															<a href="#"><%=commentChild.getUsername() %></a>
														</h5>
														<p class="date"><%=commentChild.getTimecmt() %></p>
														<p class="comment"><%=commentChild.getContent() %></p>
													</div>
												</div>
											</div>
											
										</div>
										
										<%} %>
										
										<div class="comment-list left-padding" id="comment-reply-<%=objCmt.getId() %>" >
											<!-- xxxxxxxxxxx -->
										</div>
										
										<%}}}else{ %>
											<div class="comment-list-cmt">
												
											</div>
										<%} %>
									</div>
								</div>
							</div>
							<!-- ---------------------------------------------------------- -->
						</div>
						
						<div class="comment-form">
							<h4>BÌNH LUẬN</h4>
							<form>
								<div class="form-group form-inline">
									<div class="form-group col-lg-6 col-md-12 name">
										<input required="required" type="text" class="form-control" id="username" name="username"
											placeholder="Nhập tên..." onfocus="this.placeholder = ''"
											onblur="this.placeholder = 'Nhập tên...'">
									</div>
								</div>
								<div class="form-group">
									<textarea class="form-control mb-10" rows="5" name="cmt" id="cmt"
										placeholder="Nội bình luận..." onfocus="this.placeholder = ''"
										onblur="this.placeholder = 'Messege'" required="required"></textarea>
								</div>
								<a href="javascript:void(0)" title="" class="primary-btn text-uppercase" onclick="return xuLyCmt(<%=news.getId()%>)">BÌNH LUẬN</a>
							</form>
						</div>
						
						
					</div>
					<!-- End single-post Area -->
				</div>
				<div class="col-lg-4">
					<%@ include file="/templates/public/inc/leftbar.jsp"%>
				</div>
			</div>
		</div>
	</section>
	<!-- End latest-post Area -->
</div>

<script>
	function xuLyCmtChild(cmtid,nid){
		var commentreply = $('#comment-reply').val() + '';	
			$.ajax({
				url: '<%=request.getContextPath()%>/xu-ly-reply-comment',
				type : 'GET',
				cache : false,
				data : {
					acmtid : cmtid,
					anewsid : nid,
					acommentreply : commentreply
				},
				success : function(data) {
					$('#comment-reply-'+(cmtid+1).html(data);
				},
				error : function() {
					alert('Có lỗi xảy ra');
				}
			});
		return false;
	}
</script>
<script>
function xuLyRepCmt(cmtid,newsid){
	var username = $('#username').val() + '';
	var cmt	= $('#cmt').val() + '';	
	var regex = /^[\s]+$/;
	if (username != "" && cmt != "" && !regex.exec(username) && !regex.exec(cmt)){
		$.ajax({
			url: '<%=request.getContextPath()%>/xu-ly-reply-comment',
			type : 'POST',
			cache : false,
			data : {
				ausername : username,
				acmt : cmt,
				anewsid : newsid,
				acmtid : cmtid
			},
			success : function(data) {
				$('#username').val('');
				$('#cmt').val('');
				$('#list-cmt-reply-'+cmtid).prepend(data);
				
			},
			error : function() {

				alert('Có lỗi xảy ra');
			}
		});
	}
	return false;
}
</script>
<script>
	function xuLyCmt(nid){
		var username = $('#username').val() + '';
		var cmt	= $('#cmt').val() + '';	
		var regex = /^[\s]+$/;
		if (username != "" && cmt != "" && !regex.exec(username) && !regex.exec(cmt)){
			$.ajax({
				url: '<%=request.getContextPath()%>/xu-ly-comment',
				type : 'POST',
				cache : false,
				data : {
					ausername : username,
					acmt : cmt,
					anewsid : nid
				},
				success : function(data) {
					$('#username').val('');
					$('#cmt').val('');
					$('.comment-list-cmt').prepend(data);
				},
				error : function() {

					alert('Có lỗi xảy ra');
				}
			});
		}
		return false;
	}
</script>
<!-- start footer Area -->
<%@ include file="/templates/public/inc/footer.jsp"%>
