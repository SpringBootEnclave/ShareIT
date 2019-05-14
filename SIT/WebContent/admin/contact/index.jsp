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
                <h2>Quản lý liên hệ</h2>
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
                                <!-- <div class="col-sm-6" style="text-align: right;">
                                    <form method="post" action="">
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" class="form-control input-sm" placeholder="Search..." style="float:right; width: 300px;" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div> -->
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
                                        <th>ID</th>
                                        <th>Tên</th>
                                        <th>Email</th>
                                        <th>Website</th>
                                        <th>Message</th>
                                        <th width="85px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<%
                                			ArrayList<Contact> listContact = (ArrayList<Contact>)request.getAttribute("listContact");
                                			if(listContact != null || listContact.size() > 0) {
                                				for(Contact item:listContact) {
                                	%>
                                    <tr>
                                        <td><%=item.getId() %></td>
                                        <td class="center"><%=item.getName() %></td>
                                        <td class="center"><%=item.getEmail() %></td>
                                        <td class="center"><%=item.getsubject() %></td>
                                        <td class="center"><%=item.getMessage() %> </td>
                                        <td class="center">
                                            <a onclick="return confirm('Bạn có chắc chắn muốn xóa không?')" href="<%=request.getContextPath() %>/admin/contact/delete?id=<%=item.getId() %>" title="" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
									<%}}%>
                                </tbody>
                            </table>
                            
                             <%
                            	int numberOfPages = (Integer)request.getAttribute("numberOfPages");
                         	    int currentPage = (Integer)request.getAttribute("currentPage");
                         	    int numberOfItems = (Integer)request.getAttribute("numberOfItems");
                         	    if (listContact != null || listContact.size() >0){
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
                                            <li class="paginate_button <%=active %>" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/contact/index?page=<%=i %>"><%=i %></a></li>
                                            <%} %>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/contact/index?page=<%=currentPage +1%>">Trang tiếp</a></li>
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
    document.getElementById("contact").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>