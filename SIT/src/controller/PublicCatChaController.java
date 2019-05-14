 package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.bean.News;
import model.dao.CatDAO;
import model.dao.NewsDAO;
import util.DefineUtil;



public class PublicCatChaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicCatChaController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		NewsDAO newsDAO = new NewsDAO();
		
		int id = 0;
		int currentPage = 1;
		
		try {
			id = Integer.parseInt(request.getParameter("cidcha"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			currentPage = 1;
		}
		
		CatDAO catDAO = new CatDAO();
		Category category = catDAO.getItemCha(id);
		
		if(category == null) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}
		
		int numberOfItems = newsDAO.numberOfItems(id);
		System.out.println(numberOfItems);
		int numberOfPages = (int) Math.ceil((float) numberOfItems / DefineUtil.NUMBER_PER_PAGE);
		if (currentPage > numberOfPages || currentPage < 1) {
			currentPage = 1;
		}
		int offset = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE;
		

		ArrayList<News> newsList = newsDAO.getItemByIdPagination(offset,id);
		request.setAttribute("newsList", newsList);
		request.setAttribute("category", category);
		request.setAttribute("numberOfPages", numberOfPages);
		request.setAttribute("currentPage", currentPage);
		
		RequestDispatcher rd = request.getRequestDispatcher("/public/catcha.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
