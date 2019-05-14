<%@page import="model.bean.Comment"%>
<%@page import="model.bean.Contact"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Quản lý bình luận</h2>
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
                            	<%-- <div class="col-sm-6" style="text-align: left;"></div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="post" action="<%=request.getContextPath()%>/admin/comment/search">
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" name="comments" class="form-control input-sm" required placeholder="Tên tin tức, người bình luận..." style="float:right; width: 300px;" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div> --%>
                            </div>
							<%
								String msgText = "";
								if(request.getParameter("msg") != null){
									int msg = Integer.valueOf(request.getParameter("msg"));
									switch(msg) {
										case 1: msgText = "Thêm thành công";
										break;
										case 2: msgText = "Sửa thành công";
										break;
										case 3: msgText = "Xóa thành công";
										break;
										case 0: msgText = "Có lỗi xảy ra";
										break;
									}
								}
							%>
							<%	if(!"".equals(msgText)) { %>
								<div class="alert alert-success" role="alert">
	 								 <strong><%=msgText %></strong>
								</div>
							<%} %>
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th width="5%">ID</th>
                                        <th width="30%">Tên tin</th>
                                        <th width="12%">Người bình luận</th>
                                        <th>Nội dung</th>
                                        <th width="8%">Trạng thái</th>
                                        <th width="8%">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<%
		                                	int numberOfPages = (Integer)request.getAttribute("numberOfPages");
		                             	    int currentPage = (Integer)request.getAttribute("currentPage");
		                             	    int numberOfItems = (Integer)request.getAttribute("numberOfItems");
                                			ArrayList<Comment> listComment = (ArrayList<Comment>)request.getAttribute("listComment");
                                			if(listComment.size() > 0) {
                                				for(Comment item : listComment) {
                                	%>
                                    <tr>
                                        <td><%=item.getId()%></td>
                                        <td class="center"><%=item.getObjNews().getName()%></td>
                                        <td class="center"><%=item.getUsername()%></td>
                                        <td class="center"><%=item.getContent()%></td>
                                        <%
											if(item.getActive() == 1){
										%>
										<td class="text-center">
                                        		<a href="javascript:void(0)" ><img id="<%=item.getId() %>" src="<%=request.getContextPath()%>/templates/admin/assets/img/active.gif" alt="" /></a>
                                       	</td>
                                       	<%}else{%>
                                       	<td class="text-center">
                                        		<a href="javascript:void(0)" ><img id="<%=item.getId() %>" src="<%=request.getContextPath()%>/templates/admin/assets/img/deactive.gif" alt="" /></a>
                                       	</td>
                                       	<%} %>
                                       	<td class="text-center">
											<a href="<%=request.getContextPath() %>/admin/comment/delete?id=<%=item.getId() %>&page=<%=currentPage %>" onclick="return confirm('Bạn có chắc chắn muốn xóa')" title="" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
										</td>
                                    </tr>
									<%}}%>
                                </tbody>
                            </table>
                            
                             <%
                         	    if (listComment != null || listComment.size() >0){
                            %>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị trang thứ <%=currentPage %> của <%=numberOfPages %> trang</div>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/contact/index?page=<%=currentPage -1%>">Trang trước</a></li>
                                            <%
                                           		String active = "";
                                           		for(int i = 1 ; i<= numberOfPages ; i++){
                                           			if(currentPage == i){
                                           				active = "active";
                                           			}else{
                                           				active = "";
                                           			}
                                           %>
                                            <li class="paginate_button <%=active %>" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/comment/index?page=<%=i %>"><%=i %></a></li>
                                            <%} %>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/comment/index?page=<%=currentPage +1%>">Trang tiếp</a></li>
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
    document.getElementById("comment").classList.add('active-menu');
</script>
<script>
		$(document).ready(function () {
	        $('.text-center img').click(function () {
	        	var src = $(this).attr('src');
	        	var id = $(this).attr('id');
	            $.ajax({
					url: '<%=request.getContextPath()%>/active-comment',
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
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>