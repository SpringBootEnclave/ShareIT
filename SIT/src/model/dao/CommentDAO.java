package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Category;
import model.bean.Comment;
import model.bean.Contact;
import model.bean.News;
import util.DBConnectionUtil;
import util.DefineUtil;

public class CommentDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public int numberOfItems() {
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM comments";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				int count = rs.getInt("count");
				return count;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, st, conn);
		}
		return 0;
	}

	public ArrayList<Comment> getItemsPagination(int offset) {
		ArrayList<Comment> listItem = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT c.id AS commentId, username , content, timecmt, c.active AS active, n.name AS newsName, c.newsid AS newsidcmt, n.id AS newsid, n.name FROM comments AS c INNER JOIN news AS n ON c.newsid = n.id ORDER BY c.id DESC LIMIT ?, ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				Comment objComment = new Comment(rs.getInt("commentId"), rs.getInt("newsidcmt"), rs.getInt("active"), rs.getString("username"), rs.getString("content"), rs.getTimestamp("timecmt"), new News(rs.getString("newsName")));
				listItem.add(objComment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}

		return listItem;
	}

	public void updateActive(int i, int id) {
		conn = DBConnectionUtil.getConnection();
		String sql = "UPDATE comments SET active = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, i);
			pst.setInt(2, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}
	}

	public int addCmt(Comment objCmt) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO comments (username, content, newsid, active, parent_id) VALUES (?, ?, ?, ?, ?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objCmt.getUsername());
			pst.setString(2, objCmt.getContent());
			pst.setInt(3, objCmt.getNewsid());
			pst.setInt(4, 1);
			pst.setInt(5, 0);
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}
		return result;
	}

	public Comment getLastItem(int newsid) {
		Comment cmt = null;
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM comments WHERE newsid = ? AND active = 1 AND parent_id = 0 ORDER BY id DESC";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, newsid);
			rs = pst.executeQuery();
			if (rs.next()) {
				cmt = new Comment(rs.getInt("id"), rs.getInt("newsid"), rs.getInt("active"), rs.getString("username"), rs.getString("content"), rs.getTimestamp("timecmt"), null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}
		return cmt;
	}
	
	public ArrayList<Comment> getCommentChild(int id) {
		ArrayList<Comment> listItem = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM comments WHERE parent_id = ? AND active = 1 ORDER BY id DESC";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				Comment objComment = new Comment(rs.getInt("id"), rs.getInt("newsid"), rs.getInt("active"), rs.getString("username"), rs.getString("content"), rs.getTimestamp("timecmt"), null);
				listItem.add(objComment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}
		return listItem;
	}

	public ArrayList<Comment> getItems(int id) {
		ArrayList<Comment> listCmt = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM comments WHERE newsid = ? AND active= 1 AND parent_id = 0 ORDER BY id DESC";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				Comment cmt = new Comment(rs.getInt("id"), rs.getInt("newsid"), rs.getInt("active"), rs.getString("username"), rs.getString("content"), rs.getTimestamp("timecmt"), null);
				listCmt.add(cmt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listCmt;
	}

	public int countCmt(int id) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM comments WHERE newsid = ? AND active = 1 AND parent_id = 0";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				result = rs.getInt("count");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return result;
	}

	public int deleteItem(int id) {
		conn = DBConnectionUtil.getConnection();//tạo kết nối
		
		String sql = "DELETE FROM comments WHERE id = ?";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);

			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}

		return result;
	}

	public int addCmtReply(Comment cmt,int newsid, int cmtid) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO comments (username, content, newsid, active, parent_id) VALUES (?, ?, ?, ?, ?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, cmt.getUsername());
			pst.setString(2, cmt.getContent());
			pst.setInt(3, cmt.getNewsid());
			pst.setInt(4, 1);
			pst.setInt(5, cmtid);
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}
		return result;
	}

	public Comment getLastItem(int newsid, int cmtid) {
		Comment cmt = null;
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM comments WHERE newsid = ? AND id = ? AND active = 1 ORDER BY id DESC";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, newsid);
			pst.setInt(1, cmtid);
			rs = pst.executeQuery();
			if (rs.next()) {
				cmt = new Comment(rs.getInt("id"), rs.getInt("newsid"), rs.getInt("active"), rs.getString("username"), rs.getString("content"), rs.getTimestamp("timecmt"), null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}
		return cmt;
	}

	public ArrayList<Comment> searchCommentAdmin(String comments) {
		ArrayList<Comment> listItem = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		
		String sql = "SELECT c.id AS commentId, username , content, timecmt, c.active AS active, n.name AS newsName, c.newsid AS newsidcmt, n.id AS newsid, n.name FROM comments AS c INNER JOIN news AS n ON c.newsid = n.id WHERE n.name = ? OR username = ? ORDER BY c.id DESC ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + comments + "%");
			pst.setString(2, "%" + comments + "%");
			rs = pst.executeQuery();
			while (rs.next()) {
				Comment objComment = new Comment(rs.getInt("commentId"), rs.getInt("newsidcmt"), rs.getInt("active"), rs.getString("username"), rs.getString("content"), rs.getTimestamp("timecmt"), new News(rs.getString("newsName")));
				listItem.add(objComment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listItem;
	}

}
