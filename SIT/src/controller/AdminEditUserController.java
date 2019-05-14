package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;
import model.dao.UserDAO;
import util.AuthUtil;
import util.StringUtil;

public class AdminEditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditUserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/auth/login");
			return;
		}
		
		UserDAO userDAO = new UserDAO();
		
		int id = 0;
		try {
			id = Integer.valueOf(request.getParameter("id"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/user/index?err=2");
			return;
		}

		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("userInfo");
		
		//System.out.println(userDAO.getItem(userLogin.getId()).getUsername());
		if ("admindemo".equals(userDAO.getItem(userLogin.getId()).getUsername()) || (id == userLogin.getId())) {
			// admindemo toan quyen, hoac chinh user, id dang nhap moi dc sua thong tin
			User item = userDAO.getItem(id);
			if (item != null) {
				request.setAttribute("user", item);
				RequestDispatcher rd = request.getRequestDispatcher("/admin/user/edit.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/admin/user/index?err=2");
				return;
			}
		} else {
			// khong co quyen
			response.sendRedirect(request.getContextPath() + "/admin/user/index?err=5");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UserDAO userDAO = new UserDAO();
		int id = 0;
		try {
			id = Integer.valueOf(request.getParameter("id"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/user/index?err=2");
			return;
		}

		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("userInfo");
		if ("admindemo".equals(userDAO.getItem(id).getUsername()) || (id == userLogin.getId())) {
			// lấy lại dữ liệu cũ
			User item = userDAO.getItem(id);
			String password = request.getParameter("password");
			// nếu password rỗng thì lấy password cũ
			if ("".equals(password)) {
				password = item.getPassword();
			} else {
				// mã hóa dữ liệu
				password = StringUtil.md5(password);
			}
			String fullname = request.getParameter("fullname");
			// VALIDATE DỮ LIỆU....

			// set dữ liệu mới
			item.setPassword(password);
			item.setFullname(fullname);

			// edit
			if (userDAO.editItem(item) > 0) {
				response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=2");
			} else {
				request.setAttribute("user", item);
				RequestDispatcher rd = request.getRequestDispatcher("/admin/user/edit.jsp?msg=0");
				rd.forward(request, response);
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/user/index?err=5");
			return;
		}

	}

}
