package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.Contact;
import model.dao.ContactDAO;

public class PublicContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicContactController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/public/contact.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String message = request.getParameter("message");

		ContactDAO contactDAO = new ContactDAO();
		Contact item = new Contact(0, name, email, subject, message);
		if (contactDAO.addItem(item) > 0) {
			response.sendRedirect(request.getContextPath() + "/public/contact?msg=1");
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/public/contact.jsp?err=1");
			rd.forward(request, response);
		}

	}

}
