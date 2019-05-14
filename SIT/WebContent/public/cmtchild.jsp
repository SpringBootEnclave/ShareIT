<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
	
<%
	int cmtid = (int) session.getAttribute("cmtid");
	int newsid = (int) session.getAttribute("newsid");
%>
<div class="comment-list left-padding" id="comment-reply">
	<div class="comment-form">
		<h4>BÌNH LUẬN</h4>
		<form>
			<div class="form-group form-inline">
				<div class="form-group col-lg-6 col-md-12 name">
					<input required="required" type="text" class="form-control"
						id="username" name="username" placeholder="Nhập tên..."
						onfocus="this.placeholder = ''"
						onblur="this.placeholder = 'Nhập tên...'">
				</div>
			</div>
			<div class="form-group">
				<textarea class="form-control mb-10" rows="5" name="cmt" id="cmt"
					placeholder="Nội bình luận..." onfocus="this.placeholder = ''"
					onblur="this.placeholder = 'Messege'" required="required"></textarea>
			</div>
			<a href="javascript:void(0)" title=""
				class="primary-btn text-uppercase"
				onclick="return xuLyRepCmt(<%=cmtid%>, <%=newsid%>)">Trả Lời</a>
		</form>
	</div>
</div>

