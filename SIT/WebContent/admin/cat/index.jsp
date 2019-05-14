<%@page import="model.dao.CatDAO"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<%@ include file="/templates/admin/inc/leftbar.jsp"%>
<script LANGUAGE="JavaScript">
	function confirmAction() {
		return confirm("Bạn có chắc chắn muốn xóa danh mục này không???")
	}
</script>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>Quản lý danh mục</h2>
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
									<a href="<%=request.getContextPath()%>/admin/cat/add"
										class="btn btn-success btn-md">Thêm</a>
								</div>
								<div class="col-sm-6" style="text-align: right;">
									<form method="post" action="">
										<!-- <input type="submit" name="search" value="Tìm kiếm"
											class="btn btn-warning btn-sm" style="float: right" /> <input
											type="search" class="form-control input-sm"
											placeholder="Nhập tên tin tức"
											style="float: right; width: 300px;" /> -->
										<div style="clear: both"></div>
									</form>
									<br />
								</div>
							</div>
							<%
								String msgText = "";
								if (request.getParameter("msg") != null) {

									int msg = Integer.parseInt(request.getParameter("msg"));

									switch (msg) {
										case 1 :
											msgText = "Thêm thành công!";
											break;
										case 0 :
											msgText = "Có lỗi trong quá trình thực hiện!";
											break;
										case 3 :
											msgText = "Xóa thành công!";
											break;
										case 2 :
											msgText = "Sửa thành công!";
											break;
										case 7 :
											msgText = "Xóa damh mục cha thành công, danh mục con thất bại!";
											break;
									}
								}
							%>
							<p><%=msgText%></p>
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<thead>
									<tr>
										<th>ID</th>
										<th>Tên danh mục</th>
										<th>Chức năng</th>
									</tr>
								</thead>
								<tbody>
									<%
										ArrayList<Category> listCat = (ArrayList<Category>) request.getAttribute("listCat");
										if (listCat != null || listCat.size() > 0) {
											for (Category objCat : listCat) {
												int id = objCat.getCatId();
									%>
									<tr>
										<td><%=objCat.getCatId()%></td>

										<td class="center"><%=objCat.getCatName()%>
											<ul>
												<%
													CatDAO catDAO = new CatDAO();
													ArrayList<Category> listCatChild = catDAO.getCatChild(id);
														for (Category catChild : listCatChild) {
												%>
												<%-- <li class="glyphicon glyphicon-trash"><%=catChild.getCatName() %><%=catChild.getCatId()%></li><br> --%>
												<li> <%=catChild.getCatName() %> 
													<a href="<%=request.getContextPath() %>/admin/catcon/delete?cid=<%=catChild.getCatId() %>" onclick="return confirm('Bạn có chắc chắn muốn xóa')" class="fa fa-trash-o"></a> 
													<a href="" data-toggle="modal" data-target="#myModal-<%=catChild.getCatId() %>" class="fa fa-pencil"></a>
														<div class="modal fade" id="myModal-<%=catChild.getCatId() %>" role="dialog" aria-hidden="true" style="display: none;">
															<div class="modal-dialog modal-sm">
																<div class="modal-content">
																	<div class="modal-header">
																		<button type="button" class="close"
																			data-dismiss="modal">×</button>
																		<h4 class="modal-title">SỬA DANH MỤC TIN</h4>
																	</div>
																	
																	
																	<form action="<%=request.getContextPath() %>/admin/catcon/edit" method="post">
																		<!-- <input type="hidden" name="_token"
																			value="ZxSpPyEHAZVMFaQ5OJeAcBUVukKMDqibOWY8F2hj"> -->
																		<div class="modal-body">
																			<div class="form-group">
																				<label>ID danh mục</label> 
																				<input readonly class="form-control" type="text" name="child_id"
																					value="<%=catChild.getCatId() %>">
																			</div>
																			<div class="form-group">
																				<label>Tên danh mục</label> <input
																					class="form-control" type="text" name="name"
																					value="<%=catChild.getCatName() %> ">
	
																			</div>
																			<div class="form-group">
																				<label>Danh mục cha</label> 
																				<select class="form-control" name="cat">
																					<%
																						for(Category catPr : listCat){
																							
																							
																					%>
																					<option 
																						<%
																							if(catChild.getParent_id() == catPr.getCatId()){
																						%> 
																						selected = "selected"
																						<%}%>
																						value="<%=catPr.getCatId()%>"><%=catPr.getCatName()%>
																					</option>
																					<%}%>
																				</select>
	
																			</div>
	
																		</div>
																		<div class="modal-footer">
																			<input class="btn btn-info" type="submit"
																				name="submit" value="Lưu">
																			<button type="button" class="btn btn-default"
																				data-dismiss="modal">Đóng</button>
																		</div>
																	</form>
																	
																	
																	
																</div>
															</div>
														</div>
													</li>
												<%
													}
												%>
											</ul></td>
										<td class="center"><a
											href="<%=request.getContextPath()%>/admin/cat/edit?id=<%=objCat.getCatId()%>"
											title="" class="btn btn-primary"><i class="fa fa-edit "></i>
												Sửa</a> <a
											href="<%=request.getContextPath()%>/admin/cat/delete?id=<%=objCat.getCatId()%>"
											title="" class="btn btn-danger"
											onclick="return confirmAction()"><i class="fa fa-pencil"></i>
												Xóa</a>
										</td>
									</tr>
									<%
										}
										}
									%>
								</tbody>
							</table>


							<%-- <%
								int numberOfPages = (Integer) request.getAttribute("numberOfPages");
								int currentPage = (Integer) request.getAttribute("currentPage");
								int numberOfItems = (Integer) request.getAttribute("numberOfItems");
								if (listCat != null || listCat.size() > 0) {
							%>
							<div class="row">
								<div class="col-sm-6">
									<div class="dataTables_info" id="dataTables-example_info"
										style="margin-top: 27px">
										Hiển thị
										<%=listCat.size()%>
										của
										<%=numberOfItems%>
										danh mục
									</div>
								</div>
								<div class="col-sm-6" style="text-align: right;">
									<div class="dataTables_paginate paging_simple_numbers"
										id="dataTables-example_paginate">
										<ul class="pagination">
											<li class="paginate_button previous disabled"
												aria-controls="dataTables-example" tabindex="0"
												id="dataTables-example_previous"><a
												href="<%=request.getContextPath()%>/admin/cat/index?page=<%=currentPage - 1%>">Trang
													trước</a></li>

											<%
												String active = "";
													for (int i = 1; i <= numberOfPages; i++) {
														if (currentPage == i) {
															active = "active";
														} else {
															active = "";
														}
											%>
											<li class="paginate_button <%=active%>"
												aria-controls="dataTables-example" tabindex="0"><a
												href="<%=request.getContextPath()%>/admin/cat/index?page=<%=i%>"><%=i%></a>
											</li>
											<%
												}
											%>
											<li class="paginate_button next"
												aria-controls="dataTables-example" tabindex="0"
												id="dataTables-example_next"><a
												href="<%=request.getContextPath()%>/admin/cat/index?page=<%=currentPage + 1%>">Trang
													tiếp</a></li>
										</ul>
									</div>
								</div>
							</div>
							<%
								}
							%> --%>
						</div>

					</div>
				</div>
				<!--End Advanced Tables -->
			</div>
		</div>
	</div>
</div>
<script>
	document.getElementById("category").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>