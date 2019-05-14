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

public class AdminDeleteCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDeleteCatController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+ "/auth/login");
			return;
		}
		
		CatDAO catDAO = new CatDAO();
		int id = Integer.parseInt(request.getParameter("id"));
		
		if (catDAO.deleteItem(id) > 0) {
			if(catDAO.deleteCatCon(id) > 0) {
				response.sendRedirect(request.getContextPath() + "/admin/cat/index?msg=3");
				return;
			}else {
				response.sendRedirect(request.getContextPath() + "/admin/cat/index?msg=7");
				return;
			}
			
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/cat/index?msg=0");
			return;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
