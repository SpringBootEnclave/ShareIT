<%@page import="model.bean.News"%>
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
                <h2>Sửa tin tức</h2>
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
                            if(request.getParameter("msg") != null){
                            	int msg = Integer.parseInt(request.getParameter("msg"));
                            	if(msg == 0){
                            		msgText = "Có lỗi trong quá trình thực hiện!";
                            	}
                            }
                            %>
                                <form role="form" action="" method="post" enctype="multipart/form-data" id="form" >
                                <%
                                	if(request.getAttribute("objNews") != null){
                                		News objNews = (News) request.getAttribute("objNews");
                                %>
                                 	<div class="form-group">
                                        <label for="name">ID tin tức</label>
                                        <input type="text" id="nid" disabled ="disabled" value="<%=objNews.getId()%>" name="nid" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="name">Tên tin tức</label>
                                        <input type="text" id="name" value="<%=objNews.getName()%>" name="name" class="form-control" required/>
                                    </div>
                       
                                    <div class="form-group">
                                        <label for="category">Danh mục</label>
                                        <select id="category" name="catId" class="form-control" required title="Vui lòng chọn">
                                        <%
                                        	if(request.getAttribute("listCat") != null){
                                        		ArrayList<Category> listCat = (ArrayList<Category>)request.getAttribute("listCat");
                                        		if(listCat.size()>0){
                                        			for(Category objCat : listCat){
                                        			
                                        %>
                          						<option 
                          						<%
													if(objNews.getObjCat().getCatId() == objCat.getCatId()){
													%> 
														selected = "selected"
													<%}%>
                          						value="<%=objCat.getCatId()%>"><%=objCat.getCatName()%>
                          						</option>
                        				<%}}}%>
                                        </select>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label for="picture">Hình ảnh</label>
                                        <input type="file" name="picture" />
                                       <%
                                       	if(!objNews.getPicture().isEmpty()){
                                       %>
                                        <img width="150px" height="150px" src="<%=request.getContextPath()%>/files/<%=objNews.getPicture()%>"/>
                                    	<%}%>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label for="preview">Mô tả</label>
                                        <textarea class="ckeditor" id="preview"  class="form-control" rows="3" name="preview"><%=objNews.getPreview_text()%></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label for="detail">Chi tiết</label>
                                        <textarea class="ckeditor" id="detail" class="form-control" id="detail" rows="5" name="detail"><%=objNews.getDetail_text()%></textarea>
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Sửa</button>
                                <%} %>
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
    document.getElementById("song").classList.add('active-menu');
   /*  $(document).ready(function(){
		$('#form').validate({ 
			rules:{
				name:{
					required: true,
				},
				picture:{
					required: true,
				},
				preview:{
					required: true,
				},
				detail:{
					required: true,
				},
			},
			messages:{
				name:{
					required: "Vui lòng nhập!",
				},
				picture:{
					required: "Vui lòng chọn hình ảnh!",
				},
				preview:{
					required: "Vui lòng nhập",
				},
				detail:{
					required: "Vui lòng nhập",
				},
			
			}
		});
	}); */
</script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/templates/admin/assets/ckeditor/ckeditor.js"></script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>