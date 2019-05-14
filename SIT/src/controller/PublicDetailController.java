 package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Comment;
import model.bean.News;
import model.dao.CommentDAO;
import model.dao.NewsDAO;



public class PublicDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicDetailController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		NewsDAO newsDAO = new NewsDAO();
		
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("nid"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}
		
		// lấy ra danh sách comment
		CommentDAO commentDAO = new CommentDAO();
		ArrayList<Comment> listCmt = commentDAO.getItems(id);
		// lấy ra số cmt của news
		int countCmt = commentDAO.countCmt(id);
		
		News news = newsDAO.getItem(id);
		if(news != null) {
			int count = news.getCounter();
			count += 1;
			newsDAO.setCount(count,id);
			
			request.setAttribute("listCmt", listCmt);
			request.setAttribute("countCmt", countCmt);
			request.setAttribute("news", news);
			RequestDispatcher rd = request.getRequestDispatcher("/public/detail.jsp");
			rd.forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath()+ "/public/detail.jsp");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
