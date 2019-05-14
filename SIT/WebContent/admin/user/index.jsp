<%@page import="model.bean.User"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<%@ include file="/templates/admin/inc/leftbar.jsp"%>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>Quản lý người dùng</h2>
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
								<%
									User userLogin = (User) session.getAttribute("userInfo");
									if ("admindemo".equals(userLogin.getUsername())) {
								%>
									<div class="col-sm-6">
										<a href="<%=request.getContextPath()%>/admin/user/add"
											class="btn btn-success btn-md">Thêm</a>
									</div>
								<%
									}
								%>
								<div class="col-sm-6" style="text-align: right;">
									<form method="post" action="">
										<!-- <input type="submit" name="search" value="Tìm kiếm"
											class="btn btn-warning btn-sm" style="float: right" /> <input
											type="search" class="form-control input-sm"
											placeholder="Search" style="float: right; width: 300px;" /> -->
										<br><br><div style="clear: both"></div>
									</form>
									<br />
								</div>
							</div>
							<%
								String err = request.getParameter("err");
								String msg = request.getParameter("msg");
								if ("0".equals(msg)) {
									out.print("Thêm thất bại");
								}
								if ("1".equals(msg)) {
									out.print("Thêm thành công");
								}
								if ("2".equals(msg)) {
									out.print("Sửa thành công");
								}
								if ("3".equals(msg)) {
									out.print("Xóa thành công");
								}
								if ("4".equals(msg)) {
									out.print("Không có quyền thêm");
								}
								if ("5".equals(msg)) {
									out.print("Không có quyền sửa");
								}
								if ("6".equals(msg)) {
									out.print("Không có quyền xóa");
								}
								if ("0".equals(err)) {
									out.print("Id không tồn tại!");
								}
								if ("1".equals(err)) {
									out.print("Có lỗi khi thêm");
								}
								if ("2".equals(err)) {
									out.print("Id không tồn tại");
								}
								if ("3".equals(err)) {
									out.print("Xóa thất bại");
								}
								if ("4".equals(err)) {
									out.print("Không có quyền thêm");
								}
								if ("5".equals(err)) {
									out.print("Không có quyền sửa");
								}
								if ("6".equals(err)) {
									out.print("Không có quyền xóa");
								}
							%>

							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<thead>
									<tr>
										<th>ID</th>
										<th>Username</th>
										<th>Fullname</th>
										<%
											if ("admindemo".equals(userLogin.getUsername())) {
										%>
										<th width="130px">Trạng thái</th>
										<%}%>
										<th>Chức năng</th>
										
									</tr>
								</thead>
								<tbody>
									<%
											ArrayList<User> listUser = (ArrayList<User>) request.getAttribute("listUser");
											if (listUser != null || listUser.size() > 0) {
												for (User item : listUser) {
													String urlEdit = request.getContextPath() + "/admin/user/edit?id=" + item.getId();
													String urlDel = request.getContextPath() + "/admin/user/delete?id=" + item.getId();
									%>
									<tr>
										<td><%=item.getId()%></td>
										<td class="center"><%=item.getUsername()%></td>
										<td class="center"><%=item.getFullname()%></td>
										<%
											if ("admindemo".equals(userLogin.getUsername())) {
										%>
										<%
											if(item.getId() >2){
										%>
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
                                       	<%}else{ %>
                                       		<td class="text-center">
                                        		<a href="javascript:void(0)" ><img id="<%=item.getId() %>" src="" alt="" /></a>
                                      	 	</td>
                                       	<%}}%>
                                       	
										<%
											if("admindemo".equals(userLogin.getUsername())){
										%>
											<td class="center">
												<a href="<%=urlEdit%>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a> 
												<a href="<%=urlDel%>" onclick="return myConfirm()" title="" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
											</td>
										<%}else{%>
											<td class="center">
												<%
													if(userLogin.getId() == item.getId()){
												%>
												<a href="<%=urlEdit%>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a> 
												<%
													}
												%>
											</td>
											
										<% }%>
										
									</tr>
									<%
										}}
									%>
								</tbody>
							</table>
							
							<%
                            	int numberOfPages = (Integer)request.getAttribute("numberOfPages");
                         	    int currentPage = (Integer)request.getAttribute("currentPage");
                         	    int numberOfItems = (Integer)request.getAttribute("numberOfItems");
                         	    if (listUser != null || listUser.size() >0){
                            %>
							<div class="row">
								<div class="col-sm-6">
									<div class="dataTables_info" id="dataTables-example_info"
										style="margin-top: 27px">Hiển thị <%=listUser.size() %> của <%=numberOfItems %>
										users</div>
								</div>
								<div class="col-sm-6" style="text-align: right;">
									<div class="dataTables_paginate paging_simple_numbers"
										id="dataTables-example_paginate">
										<ul class="pagination">
											
											<li class="paginate_button previous disabled"
												aria-controls="dataTables-example" tabindex="0"
												id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/user/index?page=<%=currentPage -1%>">Trang
													trước</a></li>
											<%
                                           		String active = "";
                                           		for(int i = 1 ; i<= numberOfPages ; i++){
                                           			if(currentPage == i){
                                           				active = "active";
                                           			}else{
                                           				active = "";
                                           			}
                                           %>
											<li class="paginate_button <%=active %>"
												aria-controls="dataTables-example" tabindex="0"><a
												href="<%=request.getContextPath()%>/admin/user/index?page=<%=i %>"><%=i %></a></li>
											 <%} %>
											<li class="paginate_button next"
												aria-controls="dataTables-example" tabindex="0"
												id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/user/index?page=<%=currentPage +1%>">Trang tiếp</a></li>
											
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
					url: '<%=request.getContextPath()%>/active-user',
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
	document.getElementById("user").classList.add('active-menu');
	function myConfirm() {
		var rs = confirm("Bạn có chắc chắn muốn xóa?");
		if (rs) {
			return true;
		} else
			return false;
	}
</script>

<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>