package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.dao.CatDAO;
import util.AuthUtil;

public class AdminEditCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditCatController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+ "/auth/login");
			return;
		}
		//lấy id, sau đó getItem(Category) => name set
		int catId = Integer.parseInt(request.getParameter("id"));
		CatDAO catDAO = new CatDAO();
		Category objCat = catDAO.getItem(catId);
		request.setAttribute("objCat", objCat);
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		CatDAO catDAO = new CatDAO();
		String catName = request.getParameter("name");
		int id = Integer.parseInt(request.getParameter("id"));

		Category objCat = new Category(id,0, catName);
		
		if (catDAO.editItem(objCat) > 0) {
			request.setAttribute("catName", catName);
			response.sendRedirect(request.getContextPath() + "/admin/cat/index?msg=2");
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/cat/edit?msg=0&id="+ id);
			return;
		}

	}

}
