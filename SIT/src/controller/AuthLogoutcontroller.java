package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthLogoutcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AuthLogoutcontroller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//xóa session được tạo ra ở login
		HttpSession session = request.getSession();
		if(session.getAttribute("userInfo") !=null) {
			session.removeAttribute("userInfo");
		}
		response.sendRedirect(request.getContextPath()+ "/auth/login");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
