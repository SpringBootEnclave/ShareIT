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
import util.StringUtil;

public class SeachNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public SeachNewsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String text = "Không có kết quả nào!";
		String nameNews;
		try {
			nameNews = request.getParameter("Search-box");
			nameNews = StringUtil.standardizedString(nameNews);
			NewsDAO newsDAO = new NewsDAO();
			ArrayList<News> listNews = newsDAO.searchsong(nameNews);
			if (listNews.size() > 0) {
				request.setAttribute("listNews", listNews);
				request.setAttribute("nameNews", nameNews);
				RequestDispatcher rd = request.getRequestDispatcher("/public/search.jsp");
				rd.forward(request, response);
				return;
			} else {
				System.out.println("dd");
				request.setAttribute("nameNews", nameNews);
				RequestDispatcher rd = request.getRequestDispatcher("/public/search.jsp?err");
				rd.forward(request, response);
				return;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}*/
			
		
		
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String nameNews = request.getParameter("Search-box");
		nameNews = StringUtil.standardizedString(nameNews);
		
		NewsDAO newsDAO = new NewsDAO();
		ArrayList<News> listNews = newsDAO.searchsong(nameNews);
		if (listNews.size() > 0) {
			request.setAttribute("nameNews", nameNews);
			request.setAttribute("listNews", listNews);
			RequestDispatcher rd = request.getRequestDispatcher("/public/search.jsp");
			rd.forward(request, response);
			return;
		} else {
			request.setAttribute("nameNews", nameNews);
			RequestDispatcher rd = request.getRequestDispatcher("/public/searcherr.jsp");
			rd.forward(request, response);
			return;
		}
		}
		
	}