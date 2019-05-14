<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp"%>

<div class="site-main-container">
	<section class="contact-page-area pt-50 pb-120">
		<div class="container">
			<div class="article" width= 300px;>
					<h2>
						<span style="color: #F6214B">Liên hệ</span>
						
					</h2>
					<br>
					<div class="clr"></div>
					<%
						String msg = request.getParameter("msg");
						String err = request.getParameter("err");

						String name = request.getParameter("name");
						String email = request.getParameter("email");
						String subject = request.getParameter("subject");
						String message = request.getParameter("message");
						if ("1".equals(msg)) {
							out.print("Gửi liên hệ thành công!");
						}
						if ("1".equals(err)) {
							out.print("Gửi liên hệ thất bại!");
						}
					%>
					<p>Khi bạn có thắc mắc, vui lòng gửi liên hệ, chúng tôi sẽ phản
						hồi trong thời gian sớm nhất.</p>
				</div>
			<div class="row contact-wrap">
				<div class="col-lg-3 d-flex flex-column address-wrap">
					<div class="single-contact-address d-flex flex-row">
						<div class="icon">
							<span class="lnr lnr-home"></span>
						</div>
						<div class="contact-details">
							<h5>Liên Chiểu, Đà Nẵng</h5>
							<p>
								H1/31 K55 Nam Cao, <br>Hòa Khánh Nam
							</p>
						</div>
					</div>
					<div class="single-contact-address d-flex flex-row">
						<div class="icon">
							<span class="lnr lnr-phone-handset"></span>
						</div>
						<div class="contact-details">
							<h5>0978 17 11 97</h5>
						</div>
					</div>
					<div class="single-contact-address d-flex flex-row">
						<div class="icon">
							<span class="lnr lnr-envelope"></span>
						</div>
						<div class="contact-details">
							<h5>ketulanhlung4@gmail.com</h5>
							<p>Gửi cho chúng tôi!</p>
						</div>
					</div>
				</div>
				<div class="col-lg-9">
					<form class="form-area contact-form text-right" id="myForm"
						action="<%=request.getContextPath() %>/public/contact" method="post">
						<div class="row">
							<div class="col-lg-6">
								<input name="name" placeholder="Nhập vào tên..."
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Nhập vào tên...'"
									class="common-input mb-20 form-control" required="" type="text">

								<input name="email" placeholder="Nhập vào email..."
									pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{1,63}$"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Nhập vào email...'"
									class="common-input mb-20 form-control" required=""
									type="email"> <input name="subject"
									placeholder="Nhập vào chủ đề..."
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Nhập vào chủ đề...'"
									class="common-input mb-20 form-control" required="" type="text">
							</div>
							<div class="col-lg-6">
								<textarea class="common-textarea form-control" name="message"
									placeholder="Nhập vào nội dung..."
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Nhập vào nội dung...'" required=""></textarea>
							</div>
							<div class="col-lg-12">
								<div class="alert-msg" style="text-align: left;"></div>
								<button class="primary-btn primary" style="float: right;">Gửi
									liên hệ</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<!-- End contact-page Area -->
</div>
<!-- start footer Area -->
<%@ include file="/templates/public/inc/footer.jsp" %>