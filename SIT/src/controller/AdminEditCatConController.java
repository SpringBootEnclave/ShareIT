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

public class AdminEditCatConController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditCatConController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+ "/auth/login");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		CatDAO catDAO = new CatDAO();
		String name = request.getParameter("name");
		int catCha = Integer.parseInt(request.getParameter("cat"));
		int catCon = Integer.parseInt(request.getParameter("child_id"));

		Category objCat = new Category(catCha, catCon , name);
		
		if (catDAO.editItemCatCon(objCat, catCon, name, catCha) > 0) {
			/*request.setAttribute("catName", catName);*/
			response.sendRedirect(request.getContextPath() + "/admin/cat/index?msg=2");
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/cat/index?msg=0");
			return;
		}

	}

}
