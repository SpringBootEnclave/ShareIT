<%@page import="model.dao.CatDAO"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<%@ include file="/templates/admin/inc/leftbar.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/templates/admin/assets/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/templates/admin/assets/js/jquery.validate.js"></script>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>Thêm danh mục</h2>
			</div>
		</div>
		<!-- /. ROW  -->
		<hr />
		<div class="row">
			<div class="col-md-12">
				<!-- Form Elements -->
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12">

								<%
									String msgText = "";
									if (request.getParameter("msg") != null) {

										int msg = Integer.parseInt(request.getParameter("msg"));

										if(msg == 0){
											msgText= "Có lỗi trong quá trình thêm, vui lòng thử lại!";
										}
									}
								%>
								<p><%=msgText %></p>
								
								
								<%
									CatDAO catDAO = new CatDAO();
									ArrayList<Category> listCat = catDAO.getItems();
								%>
								<form action="<%=request.getContextPath() %>/admin/cat/add" method="post" id="form" class="form">
									<div class="form-group">
										<label for="name">Tên danh mục (*)</label> 
										<input type="text" required id="name" value="" name="name" class="form-control" />
									</div>
									<div class="form-group">
                                           <label>Danh mục cha</label>
                                            <select class="form-control" name="cat">
                                               <option value="0">Thêm danh mục cha</option>
                                               <%
                                               		for(Category objCatCha : listCat){
                                               %>
                                               <option value="<%=objCatCha.getCatId()%>"><%=objCatCha.getCatName() %></option>
                                               <%} %>
                                            </select>
                                           <p class="help-block"></p>
                                    </div>

									<button type="submit" name="submit"
										class="btn btn-success btn-md">Thêm</button>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- End Form Elements -->
			</div>
		</div>
		<!-- /. ROW  -->
	</div>
	<!-- /. PAGE INNER  -->
</div>

<script type="text/javascript">
	document.getElementById("category").classList.add('active-menu');
	
		$(document).ready(function() {
			$('.form').validate({
				rules : {
					name : {
						required : true,
					},
				},
				messages : {
					name : {
						required : "Nhập vào tên danh mục bài hát!"
					},
				},
			});
		});
	</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>