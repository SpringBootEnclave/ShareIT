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

public class PublicXuLyReplyComment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicXuLyReplyComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		int cmtid = Integer.parseInt(request.getParameter("acmtid"));
		int newsid = Integer.valueOf(request.getParameter("anewsid"));
		String commentreply = request.getParameter("acommentreply");
		
		session.setAttribute("cmtid", cmtid);
		session.setAttribute("newsid", newsid);
		
		response.sendRedirect(request.getContextPath()+ "/public/cmtchild.jsp");
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("ausername");
		String content = request.getParameter("acmt");
		
		int cmtid = Integer.valueOf(request.getParameter("acmtid"));
		int newsid = Integer.valueOf(request.getParameter("anewsid"));
		
		Comment cmt = new Comment(0, newsid, 1, cmtid, username, content, null);
		
		CommentDAO commentDAO = new CommentDAO();
		if (commentDAO.addCmtReply(cmt, newsid, cmtid) > 0) {
			Comment objCmt1 = commentDAO.getLastItem(newsid,cmtid);
			/*out.print(
						"<div class='single-comment justify-content-between d-flex'>" 
					+	"<div class='user justify-content-between d-flex'>" 
					+   "<div class='desc'>" 
					+	"<h5>" 
					+   "<a href='#'>"+ objCmt.getUsername() + "</a>" 
					+ 	"</h5>" 
					+   "<p class='date'>"+ objCmt.getTimecmt() + "</p>" 
					+   "<p class='comment'>"+ objCmt.getContent() + "</p>" 
					+   "</div>" + 
					"			</div>" + 
					"		</div>");*/
			//System.out.println("Lộc");
			HttpSession session = request.getSession();
			session.setAttribute("objCmt1", cmt);
			//System.out.println(objCmt1.getContent());
			response.sendRedirect(request.getContextPath()+ "/public/repplycmtchild.jsp");
			return;
			
		}
	}
	
}
