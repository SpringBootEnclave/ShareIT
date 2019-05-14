package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import util.AuthUtil;
import util.StringUtil;

public class AdminSeachCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AdminSeachCommentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/auth/login");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String comments = request.getParameter("comments");
		comments = StringUtil.standardizedString(comments);
		
		if(!"".equals(comments) || comments != null) {
			
			CommentDAO commentDAO = new CommentDAO();
			ArrayList<Comment> listComment = commentDAO.searchCommentAdmin(comments);
			if (listComment.size() > 0) {
				request.setAttribute("comments", comments);
				request.setAttribute("listComment", listComment);
				RequestDispatcher rd = request.getRequestDispatcher("/admin/comment/search.jsp");
				rd.forward(request, response);
				return;
			} else {
				request.setAttribute("comments", comments);
				RequestDispatcher rd = request.getRequestDispatcher("/admin/comment/searcherr.jsp");
				rd.forward(request, response);
				return;
			}
		}else {
			out.println("Vui lòng nhập vào!");
		}
		}
		
	}