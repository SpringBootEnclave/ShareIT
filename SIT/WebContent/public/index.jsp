<%@page import="util.StringUtil"%>
<%@page import="model.dao.NewsDAO"%>
<%@page import="model.bean.News"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>

		<div class="site-main-container">
			<!-- Start top-post Area -->
			<section class="top-post-area pt-10">
				<div class="container no-padding">
					<div class="row small-gutters">
						<%
							NewsDAO newsDAO = new NewsDAO();
							ArrayList<News> newsLimit1 = newsDAO.getNewsLimit1();
								for (News objNews : newsLimit1) {
									String urlDetail = request.getContextPath() + "/public/detail/index/" + StringUtil.makeSlug(objNews.getName())
									+ "-" + objNews.getId() + ".html";
									
									String urlCat = request.getContextPath() + "/public/cat/index/" + StringUtil.makeSlug(objNews.getName())
									+ "-" + objNews.getObjCat().getCatId() + ".html";
						%>
						<div class="col-lg-8 top-post-left">
							<div class="feature-image-thumb relative">
								<div class="overlay overlay-bg"></div>
								<a href="<%=urlDetail%>"><img class="img-fluid" src="<%=request.getContextPath() %>/files/<%=objNews.getPicture() %>" alt=""></a>
							</div>
							<div class="top-post-details">
								<ul class="tags">
									<li><a href="<%=urlCat%>"><%=objNews.getObjCat().getCatName() %></a></li>
								</ul>
								<a href="<%=urlDetail%>">
									<h3><%=objNews.getName() %></h3>
								</a>
								<ul class="meta">
									<!-- <li><a href="#"><span class="lnr lnr-user"></span>Mark wiens</a></li> -->
									<li><a href="#"><span class="lnr lnr-calendar-full"></span><%=objNews.getDate_create() %></a></li>
									<li><a href="#"><span class="fa fa-tripadvisor"></span>   <%=objNews.getCounter() %></a></li>
								</ul>
							</div>
						</div>
						<%} %>
						
						<div class="col-lg-4 top-post-right">
						<%
							ArrayList<News> newsLimit2 = newsDAO.getNewsLimit2();
								for (News objNews : newsLimit2) {
									String urlDetail = request.getContextPath() + "/public/detail/index/" + StringUtil.makeSlug(objNews.getName())
									+ "-" + objNews.getId() + ".html";
									
									String urlCat = request.getContextPath() + "/public/cat/index/" + StringUtil.makeSlug(objNews.getName())
									+ "-" + objNews.getObjCat().getCatId() + ".html";
						%>
							<div class="single-top-post">
								<div class="feature-image-thumb relative">
									<div class="overlay overlay-bg"></div>
									<a href="<%=urlDetail%>"><img class="img-fluid" src="<%=request.getContextPath() %>/files/<%=objNews.getPicture() %>" alt=""></a>
								</div>
								<div class="top-post-details">
									<ul class="tags">
										<li><a href="<%=urlCat%>"><%=objNews.getObjCat().getCatName() %></a></li>
									</ul>
									<a href="<%=urlDetail%>">
										<h4><%=objNews.getName() %></h4>
									</a>
									<ul class="meta">
										<!-- <li><a href="#"><span class="lnr lnr-user"></span>Mark wiens</a></li> -->
										<li><a href="#"><span class="lnr lnr-calendar-full"></span><%=objNews.getDate_create() %></a></li>
										<li><a href="#"><span class="fa fa-tripadvisor"   ></span>   <%=objNews.getCounter() %></a></li>
									</ul>
								</div>
							</div>
							<%} %>
						</div>
						
						
						<%
							for (News objNews : newsLimit1) {
						%>
						<div class="col-lg-12">
							<div class="news-tracker-wrap">
								<h6><span>Tin tức mới nhất:    </span><a href="#"><%=objNews.getName() %></a></h6>
							</div>
						</div>
						<%} %>
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
								<h4 class="cat-title">Tin tức mới</h4>
								 <%
                                		ArrayList<News> listNews = (ArrayList<News>) request.getAttribute("listNews");
                                		if(listNews != null || listNews.size()>0){
                                			for(News objNews: listNews){
                                				String urlDetail = request.getContextPath() + "/public/detail/index/" + StringUtil.makeSlug(objNews.getName())
            									+ "-" + objNews.getId() + ".html";
            									
            									String urlCat = request.getContextPath() + "/public/cat/index/" + StringUtil.makeSlug(objNews.getName())
            									+ "-" + objNews.getObjCat().getCatId() + ".html";
                                			
                                %>
								<div class="single-latest-post row align-items-center">
									<div class="col-lg-5 post-left">
										<div class="feature-img relative">
											<div class="overlay overlay-bg"></div>
											<a href="<%=urlDetail%>"><img class="img-fluid" src="<%=request.getContextPath() %>/files/<%=objNews.getPicture() %>" alt="" width="400px" height="240px"></a>
										</div>
										<ul class="tags">
											<li><a href="<%=urlCat%>"><%=objNews.getObjCat().getCatName() %></a></li>
										</ul>
									</div>
									<div class="col-lg-7 post-right">
										<a href="<%=urlDetail%>">
											<h4><%=objNews.getName() %>.</h4>
										</a>
										<ul class="meta">
											<!-- <li><a href="#"><span class="lnr lnr-user"></span>Mark wiens</a></li> -->
											<li><a href="#"><span class="lnr lnr-calendar-full"></span><%=objNews.getDate_create() %></a></li>
											<li><a href="#"><span class="fa fa-tripadvisor"></span>   <%=objNews.getCounter() %></a></li>
										</ul>
										<p class="excert">
											<%=objNews.getPreview_text() %>
										</p>
									</div>
								</div>
								<%}} %>
							</div>
							<%
                            	int numberOfPages = (Integer)request.getAttribute("numberOfPages");
                         	    int currentPage = (Integer)request.getAttribute("currentPage");
                         	    int numberOfItems = (Integer)request.getAttribute("numberOfItems");
                         	    if (listNews != null || listNews.size() >0){
                         	    	int j = currentPage + 1;
                         	    	String urlPageNext = request.getContextPath() + "/public/index/-" + j + ".html";
                            %>
                            <div class="row">
                            <div class="col-sm-6" style="text-align: left;"></div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                            <%
                                           		String active = "";
                                           		for(int i = 1 ; i<= numberOfPages ; i++){
                                           			if(currentPage == i){
                                           				active = "active";
                                           			}else{
                                           				active = "";
                                           			}
                                           			String urlPage = request.getContextPath() + "/public/index/-" + i + ".html";
                                           			
                                           %>
											<li class="paginate_button <%=active %>" aria-controls="dataTables-example" tabindex="0">
												<a href="<%=urlPage%>"><%=i %></a>
											</li>
											<%} %>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=urlPageNext%>">Trang tiếp</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <%} %>
							<!-- End latest-post Area -->
							
							<%-- <!-- Start banner-ads Area -->
							<div class="col-lg-12 ad-widget-wrap mt-30 mb-30">
								<img class="img-fluid" src="<%=request.getContextPath() %>/templates/public/img/banner-ad.jpg" alt="">
							</div>
							<!-- End banner-ads Area --> --%>
						</div>
						<div class="col-lg-4">
							<%@ include file="/templates/public/inc/leftbar.jsp" %>
						</div>
					</div>
				</div>
			</section>
			<!-- End latest-post Area -->
		</div>
		
		<!-- start footer Area -->
		<%@ include file="/templates/public/inc/footer.jsp" %>	