package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Comment;
import model.dao.CommentDAO;

public class PublicXuLyComment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicXuLyComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("ausername");
		String content = request.getParameter("acmt");
		int newsid = Integer.valueOf(request.getParameter("anewsid"));
		Comment cmt = new Comment(0, newsid, 1, 0 ,username, content, null);
		
		/*System.out.println(username);
		System.out.println(content);
		System.out.println(newsid);*/
		CommentDAO commentDAO = new CommentDAO();
		if (commentDAO.addCmt(cmt) > 0) {
			Comment objCmt = commentDAO.getLastItem(newsid);
			/*out.print(	"<div class='comment-list' id='list-cmt'>"
						+ "<div class='single-comment justify-content-between d-flex'>"
						+	 "<div class='user justify-content-between d-flex'>"
						+		"<div class='thumb'>"
						+			"<img src='"+ request.getContextPath()+ "/templates/public/img/1.png' alt=''>"
						+		"</div>"
						+		"<div class='desc'>"
						+ 			"<h5>"
						+ 				"<a href='#'>"+objCmt.getUsername() +"</a>"
						+ 			"</h5>"
						+ 			"<p class='date'>"+objCmt.getTimecmt() +"</p>"
						+ 			"<p class='comment'>"+objCmt.getContent() +"</p>"
						+ 		"</div>"
						+ 	 "</div>"
						+  "<div class='reply-btn'>"
						+ 	  "<a href='javascript:void(0)' class='btn-reply text-uppercase' onclick='return xuLyCmtChild("+ cmt.getId() +","+ newsid +")>reply</a>"
						+  "</div>"
						+ "</div>"
					+ "</div>");*/
			HttpSession session = request.getSession();
			session.setAttribute("newsidCmtCha",newsid);
			session.setAttribute("cmt", objCmt);
			response.sendRedirect(request.getContextPath()+ "/public/cmtcha.jsp");
			return;
		}
	}

}
