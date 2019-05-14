package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.bean.Category;
import model.bean.News;
import model.dao.CatDAO;
import model.dao.NewsDAO;
import util.AuthUtil;
import util.DefineUtil;
import util.FileUtil;
@MultipartConfig
public class AdminEditNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditNewsController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/auth/login");
			return;
		}
		
		NewsDAO newsDAO = new NewsDAO();
		CatDAO catDAO = new CatDAO();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		}catch(NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/news/index?msg=7");//msgText = "Id không tồn tại";
			return;
		}
		
		News objNews = newsDAO.getItem(id);
		request.setAttribute("objNews", objNews);
		
		ArrayList<Category> listCat = catDAO.getCatChild();
		request.setAttribute("listCat", listCat);
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/news/edit.jsp?");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int idNews = 0;
		try {
			idNews = Integer.parseInt(request.getParameter("id"));
		}catch(NumberFormatException e){
			
		}
		
		int page = Integer.valueOf(request.getParameter("page"));
		
		int catId = Integer.parseInt(request.getParameter("catId"));
		String name = request.getParameter("name");
		String preview = request.getParameter("preview");
		String detail = request.getParameter("detail"); 
		
		
		NewsDAO newsDAO = new NewsDAO();
		News news = newsDAO.getItem(idNews);
		if (news == null) {
			response.sendRedirect(request.getContextPath() + "/admin/news/index?msg=0");
			return;
		}
		
		Part filePart = request.getPart("picture");
		String fileName = filePart.getSubmittedFileName();
		
		
		//Nếu file ko rỗng
		if (!"".equals(fileName)) {
			String dirPath = request.getServletContext().getRealPath("") + DefineUtil.DIR_UPLOAD;
			File dirFile = new File(dirPath);
			
			if (!dirFile.exists()) {
				dirFile.mkdir();
			}
			
			fileName = FileUtil.rename(fileName);
			String filePath = dirPath + File.separator + fileName;
			
			//Xóa file cũ
			String oldFilePath = dirPath + File.separator + news.getPicture();
			File oldFile = new File(oldFilePath);
			if (oldFile.exists()) {
				oldFile.delete();
			}
			//Ghi file mới vào
			filePart.write(filePath);
			
		} else {
			fileName = news.getPicture();
		}
		
		
		
		
		news = new News(idNews, 0, 0, name, preview, detail, null, fileName, new Category(catId, 0 , null));
		
		if(newsDAO.editItem(news) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/news/index?page="+ page + "&msg=2");
		} else {
			/*request.setAttribute("objSong", news);*/
			RequestDispatcher rd = request.getRequestDispatcher("/admin/news/edit.jsp?msg=0");
			rd.forward(request, response);
		}
	}

}
