package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.News;
import model.bean.User;
import model.dao.CommentDAO;
import model.dao.ContactDAO;
import model.dao.NewsDAO;
import model.dao.UserDAO;
import util.AuthUtil;

public class AdminDeleteCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDeleteCommentController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/auth/login");
			return;
		}
		
		int page = Integer.parseInt(request.getParameter("page"));
		
		CommentDAO commentDAO = new CommentDAO();
		int id = 0;
		try {
			id = Integer.valueOf(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/comment/index?msg=0");
			return;
		}
		
        if(commentDAO.deleteItem(id) > 0) {
        	response.sendRedirect(request.getContextPath() + "/admin/comment/index?page="+page+"&msg=3");
        	return;
        }else {
        	response.sendRedirect(request.getContextPath() + "/admin/comment/index?msg=0");
        	return;
        }
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
