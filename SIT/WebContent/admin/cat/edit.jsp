<%@page import="model.bean.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<%@ include file="/templates/admin/inc/leftbar.jsp"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/templates/admin/assets/js/jquery-3.3.1.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/templates/admin/assets/js/jquery.validate.js"></script>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>Sửa danh mục</h2>
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
											msgText= "Có lỗi trong quá trình sửa, vui lòng thử lại!";
										}
									}
								%>
								<p><%=msgText %></p>
								<%
									String catName = "";
									int catId = 0;
									if(request.getAttribute("objCat") != null){
										Category objCat = (Category) request.getAttribute("objCat");
										catName = objCat.getCatName();
										catId = objCat.getCatId();
									}
								%>
								<form action="" method="post" id="form" class="form">
									<div class="form-group">
										<label for="name">Sửa tên danh mục (*)</label> <input type="text" required 
											id="name" value="<%=catName %>" name="name" class="form-control" />
									</div>

									<button type="submit" name="submit"
										class="btn btn-success btn-md">Sửa</button>
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