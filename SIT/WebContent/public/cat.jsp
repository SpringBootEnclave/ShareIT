<%@page import="util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>
		
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
									Category category = (Category) request.getAttribute("category");
								%>
								<h4 class="cat-title" style="font-size: 17px;"><%=category.getCatName() %></h4>
								<%
                                		ArrayList<News> newsList = (ArrayList<News>) request.getAttribute("newsList");
                                		if(newsList.size()>0){
                                			for(News objNews: newsList){
                                				String urlDetail = request.getContextPath() + "/public/detail/index/" + StringUtil.makeSlug(objNews.getName())
            									+ "-" + objNews.getId() + ".html";
            									
            									String urlCat = request.getContextPath() + "/public/cat/index/" + StringUtil.makeSlug(objNews.getName())
            									+ "-" + objNews.getObjCat().getCatId() + ".html";
                                				/* <rule>
                                				<!-- /public/detail?id=16 -->
                                				<from>/public/detail/index/(.*)-([0-9]+).html</from>
                                				<to>/public/detail/index?nid=$2</to>
                                			</rule> */
                                %>
								<div class="single-latest-post row align-items-center">
									<div class="col-lg-5 post-left">
										<div class="feature-img relative">
											<div class="overlay overlay-bg"></div>
											<a href="<%=urlDetail%>"><img class="img-fluid" src="<%=request.getContextPath() %>/files/<%=objNews.getPicture() %>" alt=""></a>
										</div>
										<ul class="tags">
											<li><a href="<%=urlCat%>"><%=objNews.getObjCat().getCatName() %></a></li>
										</ul>
									</div>
									<div class="col-lg-7 post-right">
										<a href="<%=urlDetail%>">
											<h4><%=objNews.getName() %></h4>
										</a>
										<ul class="meta">
											<!-- <li><a href="#"><span class="lnr lnr-user"></span>Mark wiens</a></li> -->
											<li><a href="#"><span class="lnr lnr-calendar-full"></span><%=objNews.getDate_create() %></a></li>
											<li><a href="#"><span class="lnr lnr-bubble"></span><%=objNews.getCounter() %></a></li>
										</ul>
										<a href="<%=urlDetail%>">Xem chi tiết</a>
									</div>
								</div>
								<%}} %>
								
								<%
									
	                            	int numberOfPages = (Integer)request.getAttribute("numberOfPages");
	                         	    int currentPage = (Integer)request.getAttribute("currentPage");
	                         	    int numberOfItems = (Integer)request.getAttribute("numberOfItems");
	                         	    
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
                                           %>
											<li class="paginate_button <%=active %>" aria-controls="dataTables-example" tabindex="0">
												<a href="<%=request.getContextPath()%>/public/cat/index?cid=<%=category.getCatId() %>&page=<%=i %>"><%=i %></a>
											</li>
											<%} %>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/public/cat/index?cid=<%=category.getCatId() %>&page=<%=currentPage + 1%>">Trang tiếp</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
								
							</div>
							<!-- End latest-post Area -->
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