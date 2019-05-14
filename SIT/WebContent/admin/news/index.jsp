
<%@page import="model.bean.News"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Quản lý tin tức</h2>
            </div>
        </div>
        <!-- /. ROW  -->
       
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Advanced Tables -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="table-responsive">
                            <div class="row">
                                <div class="col-sm-6">
                                    <a href="<%=request.getContextPath() %>/admin/news/add" class="btn btn-success btn-md">Thêm</a>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="post" action="<%=request.getContextPath()%>/admin/search-news">
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" name="newsname" required  class="form-control input-sm" placeholder="Nhập tên tin tức" style="float:right; width: 300px;" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div>
                            </div>
							 <%
                                	String msgText = "";
                                	if(request.getParameter("msg") != null){
                                		int msg = Integer.parseInt(request.getParameter("msg"));
                                		if(msg == 1){
                                			msgText = "Thêm thành công";
                                		}
                                		if(msg == 2){
                                			msgText = "Sửa thành công";
                                		}
                                		if(msg == 3){
                                			msgText = "Xóa thành công";
                                		}
                                		if(msg == 0){
                                			msgText = "Có lỗi trong quá trình thực hiện";
                                		}
                                		if(msg == 4){
                                			msgText = "Thêm thất bại";
                                		}
                                		if(msg == 5){
                                			msgText = "Sửa thất bại";
                                		}
                                		if(msg == 6){
                                			msgText = "Xóa thất bại";
                                		}
                                		if(msg == 7){
                                			msgText = "Id không tồn tại";
                                		}
                                	}
                                %>
                               <p><%=msgText %></p>
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên tin tức</th>
                                        <th width="130px">Danh mục</th>
                                        <th width="200px">Hình ảnh</th>
                                        <th width="130px">Trạng Thái</th>
                                        <th width="180px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
		                                int numberOfPages = (Integer)request.getAttribute("numberOfPages");
		                         	    int currentPage = (Integer)request.getAttribute("currentPage");
		                         	    int numberOfItems = (Integer)request.getAttribute("numberOfItems");
                                		ArrayList<News> listNews = (ArrayList<News>) request.getAttribute("listNews");
                                		if(listNews != null || listNews.size()>0){
                                			for(News objNews: listNews){
                                			
                                %>
                                    <tr>
                                        <td><%=objNews.getId() %></td>
                                        <td class="center"><%=objNews.getName() %></td>
                                        <td class="center"><%=objNews.getObjCat().getCatName()%></td>
                                        <td class="center">
                                        	<%
                                        		if(!"".equals((objNews.getPicture()))){
                                        	%>
											<img width="200px" height="200px" src="<%=request.getContextPath() %>/files/<%=objNews.getPicture() %>" alt=""/>
                                        	<%
                                        		}else{
                                        	%>
                                        	<p>--Không có hình--</p>
                                        	<%} %>
                                        </td>
                                        <%
											if(objNews.getActive() == 1){
										%>
										
										<td class="text-center">
                                        		<a href="javascript:void(0)" ><img id="<%=objNews.getId() %>" src="<%=request.getContextPath()%>/templates/admin/assets/img/active.gif" alt="" /></a>
                                       	</td>
                                       	<%}else{%>
                                       	<td class="text-center">
                                        		<a href="javascript:void(0)" ><img id="<%=objNews.getId() %>" src="<%=request.getContextPath()%>/templates/admin/assets/img/deactive.gif" alt="" /></a>
                                       	</td>
                                       	<%} %>
                                       	
                                       	
                                        <td class="center">
                                            <a href="<%=request.getContextPath() %>/admin/news/edit?id=<%=objNews.getId() %>&page=<%=currentPage %>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a href="<%=request.getContextPath() %>/admin/news/delete?id=<%=objNews.getId() %>&page=<%=currentPage %>" title="" onclick="return confirm('Bạn có chắc chắn muốn xóa')" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
								<%}}%>
                                </tbody>
                            </table>
                            
                            <%
                         	    if (listNews != null || listNews.size() >0){
                            %>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị trang thứ <%=currentPage %> của <%=numberOfPages %> trang</div>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                            <li class="paginate_button previous disabled" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/news/index?page=<%=currentPage - 1%>">Trang trước</a></li>
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
												<a href="<%=request.getContextPath()%>/admin/news/index?page=<%=i %>"><%=i %></a>
											</li>
											<%} %>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/news/index?page=<%=currentPage + 1%>">Trang tiếp</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <%} %>
                                     
                          
                        </div>

                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>
    </div>
</div>
<script>
		$(document).ready(function () {
	        $('.text-center img').click(function () {
	        	var src = $(this).attr('src');
	        	var id = $(this).attr('id');
	            $.ajax({
					url: '<%=request.getContextPath()%>/active-news',
					type: 'POST',
					cache: false,
					data: {
						asrc: src,
						aid: id
					},
					success: function(data){
						$('#' + id).attr('src', data);
					},
					error: function (){
						alert('Có lỗi xảy ra');
					}
				});
	        });
	 	});  
</script>
<script>
    document.getElementById("news").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>