 package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.News;
import model.dao.NewsDAO;
import util.DefineUtil;



public class PublicIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicIndexController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		NewsDAO newsDao = new NewsDAO();
		
		int numberOfItems = newsDao.numberOfItemsActive();
		
		int numberOfPages = (int)Math.ceil((float)numberOfItems / DefineUtil.NUMBER_PER_PAGE);
		System.out.println(numberOfItems);
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(currentPage > numberOfPages || currentPage < 1) {
			currentPage = 1;
		}
		
		int offsett = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE;
		int offset = offsett + 3;
		
		ArrayList<News> listNews = newsDao.getItemsPaginationActive(offset);
		request.setAttribute("listNews", listNews);
		request.setAttribute("numberOfItems", numberOfItems);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("numberOfPages", numberOfPages);
		
		RequestDispatcher rd = request.getRequestDispatcher("/public/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
