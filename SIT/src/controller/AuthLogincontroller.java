package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;
import model.dao.UserDAO;
import util.StringUtil;

public class AuthLogincontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AuthLogincontroller() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/auth/login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDAO userDAO = new UserDAO();
		
		String username = request.getParameter("username");
		String password = StringUtil.md5(request.getParameter("password"));
		
		User userInfo = userDAO.getItemByUserNameAndPassWord(username, password);
		//System.out.println(userInfo);
		if(userInfo != null) {
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", userInfo);
			response.sendRedirect(request.getContextPath()+ "/admin/index");
			return;
		}else {
			response.sendRedirect(request.getContextPath()+ "/auth/login?msg=Error");
			return;
		}
	}

}
