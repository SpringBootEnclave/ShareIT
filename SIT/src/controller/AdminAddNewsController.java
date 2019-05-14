package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.jasper.tagplugins.jstl.core.Out;

import model.bean.Category;
import model.bean.News;
import model.bean.User;
import model.dao.CatDAO;
import model.dao.NewsDAO;
import util.AuthUtil;
import util.DefineUtil;
import util.FileUtil;
@MultipartConfig
public class AdminAddNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAddNewsController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+ "/auth/login");
			return;
		}
		
		CatDAO catDAO = new CatDAO();
		ArrayList<Category> listCat = catDAO.getItem();
		RequestDispatcher rd = request.getRequestDispatcher("/admin/news/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+ "/auth/login");
			return;//coi lỗi ở mô
		}
		NewsDAO newsDAO = new NewsDAO();
		
		String name = request.getParameter("name");
		int cat_id = Integer.valueOf(request.getParameter("cat_id"));
		String preview = request.getParameter("preview");
		String detail = request.getParameter("detail");
		Part filePart = request.getPart("picture");
		if("".equals(name) || "".equals(preview) || "".equals(detail)) {
			response.sendRedirect(request.getContextPath() + "/admin/news/add?msg=9");
			return;
		}else {
		
		
		String fileName = filePart.getSubmittedFileName();
		if (!"".equals(fileName)) {
			
			String dirPath = request.getServletContext().getRealPath("") + DefineUtil.DIR_UPLOAD;
			File dirFile = new File(dirPath);
			if (!dirFile.exists()) {
				dirFile.mkdir();
			}
			fileName = FileUtil.rename(fileName);
			String filePath = dirPath + File.separator + fileName;
			filePart.write(filePath);
		}//chạy đi
		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("userInfo");
		int i = userLogin.getId();
		
		News objNews = new News(0, 0, 0, name, preview, detail, null, fileName, new Category(cat_id, 0 ,""));

		if (newsDAO.addItem(objNews, i) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/news/index?msg=1");
			return;
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/news/add.jsp?msg=0");
			rd.forward(request, response);
		}
	}}

}
