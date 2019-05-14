package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.dao.CatDAO;
import util.AuthUtil;

public class AdminAddCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAddCatController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/auth/login");
			return;
		}

		RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		CatDAO catDAO = new CatDAO();
		String name = request.getParameter("name");
		int cat = Integer.parseInt(request.getParameter("cat"));
		
		if (!"".equals(name) || (name != null)) {
			Category objCat = new Category(0, cat, name);
			if (cat == 0) {
				if (catDAO.addItemCha(objCat) > 0) {
					response.sendRedirect(request.getContextPath() + "/admin/cat/index?msg=1");
					return;
				} else {
					response.sendRedirect(request.getContextPath() + "/admin/cat/add?msg=0");
					return;
				}
			} else {
				if (catDAO.addItemCon(objCat,cat) > 0) {
					response.sendRedirect(request.getContextPath() + "/admin/cat/index?msg=1");
					return;
				} else {
					response.sendRedirect(request.getContextPath() + "/admin/cat/add?msg=0");
					return;
				}
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/cat/add?msg=0");
			return;
		}

	}

}
