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
					<!-- Start latest-post Area -->
					<div class="latest-post-wrap">
						<%
							String err = (String)request.getAttribute("nameNews");
						%>
						<h3>Không có kết quả nào cho "<span style="color:red"><%=err %></span>"</h3>
					</div>
					<!-- End latest-post Area -->
				</div>
				<div class="col-lg-4">
					<%@ include file="/templates/public/inc/leftbar.jsp"%>
				</div>
			</div>
		</div>
	</section>
	<!-- End latest-post Area -->
</div>

<!-- start footer Area -->
<%@ include file="/templates/public/inc/footer.jsp"%>
