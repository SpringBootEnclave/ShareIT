package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;
import model.dao.UserDAO;
import util.AuthUtil;

public class AdminDeleteUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

	public AdminDeleteUserController() {
		super();
		userDAO = new UserDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/auth/login");
			return;
		}
		
		int id = 0;
		try {
			id = Integer.valueOf(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/user/index?err=2");
			return;
		}

		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("userInfo");

		User user = userDAO.getItem(id);

		if ("admindemo".equals(user.getUsername())) {
			// khogn duoc phep
			response.sendRedirect(request.getContextPath() + "/admin/user/index?err=6");
			return;
		} else {
			if ("admindemo".equals(userLogin.getUsername())) {
				// duoc phep xoa
				if (userDAO.deleteItem(id) > 0) {
					response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=3");
				} else {
					response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=0");
				}
			} else {
				//khong dc phep xoa
				response.sendRedirect(request.getContextPath() + "/admin/user/index?err=6");
				return;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
