<%@page import="model.dao.CatDAO"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>

<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Thêm Tin Tức</h2>
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
    								String msg = request.getParameter("msg");
    								if ("0".equals(msg)) {
    									out.print("Thêm thất bại");
    								}if ("9".equals(msg)) {
    									out.print("Vui lòng nhập vào tất cả các trường!");
    								}
    							%>
                                <form role="form" method="post" enctype="multipart/form-data" id="form" action="<%=request.getContextPath()%>/admin/news/add">
                                    <div class="form-group">
                                        <label for="name">Tên tin tức</label>
                                        <input type="text" id="name" value="" name="name" class="form-control" required/>
                                    </div>
                       
                                    <div class="form-group">
                                        <label for="category">Danh mục</label>
                                        <select id="category" name="cat_id" class="form-control" required title="Vui lòng chọn">
                                       <%
											CatDAO catDAO = new CatDAO();
											ArrayList<Category> listCatChild = catDAO.getCatChild();
												for (Category catChild : listCatChild) {
										%>
                          						<option value="<%=catChild.getCatId()%>"><%=catChild.getCatName() %></option>
                        				<%}%>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="picture">Hình ảnh</label>
                                        <input type="file" name="picture" />
                                    </div>
                                    <div class="form-group">
                                        <label for="preview">Mô tả</label>
                                        <textarea class="ckeditor" id="preview" class="form-control" rows="3" name="preview"></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label for="detail">Chi tiết</label>
                                        <textarea class="ckeditor" id="detail" class="form-control" id="detail" rows="5" name="detail"></textarea>
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Thêm</button>
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
	document.getElementById("news").classList.add('active-menu');
		$(document).ready(function() {
			$('#form').validate({
				rules : {
					name : {
						required : true
					},
				},
				messages : {
					name : {
						required : "Vui lòng nhập tên tin!"
					},
				},
			});
		});
</script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/templates/admin/assets/ckeditor/ckeditor.js"></script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>