package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UserDAO;


public class XuLyActiveUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public XuLyActiveUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		UserDAO userDAO = new UserDAO();
		
		String src = request.getParameter("asrc");
		int id = Integer.valueOf(request.getParameter("aid"));
		String status = src.substring(src.lastIndexOf("/") + 1, src.lastIndexOf("."));
		if ("active".equals(status)) {
			userDAO.updateActive(0, id);
			src = request.getContextPath() + "/templates/admin/assets/img/deactive.gif";
		} else {
			userDAO.updateActive(1, id);
			src = request.getContextPath() + "/templates/admin/assets/img/active.gif";
		}
		out.print(src);
	}

}
