package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Comment;
import model.bean.Contact;
import model.dao.CommentDAO;
import model.dao.ContactDAO;
import util.AuthUtil;
import util.DefineUtil;

public class AdminIndexCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminIndexCommentController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/auth/login");
			return;
		}
		CommentDAO commentDAO = new CommentDAO();

		int numberOfItems = commentDAO.numberOfItems();
		int numberOfPages = (int) Math.ceil((float) numberOfItems / DefineUtil.NUMBER_PER_PAGE);
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (currentPage > numberOfPages || currentPage < 1) {
			currentPage = 1;
		}

		int offset = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE;

		ArrayList<Comment> listComment = commentDAO.getItemsPagination(offset);// lop phan trang
		request.setAttribute("listComment", listComment);
		request.setAttribute("numberOfPages", numberOfPages);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("numberOfItems", numberOfItems);

		// ArrayList<Contact> listContact = contactDAO.getItems();
		// request.setAttribute("listContact", listContact);

		RequestDispatcher rd = request.getRequestDispatcher("/admin/comment/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
