package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Category;
import model.bean.News;
import util.DBConnectionUtil;
import util.DefineUtil;

public class NewsDAO {
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement pst;

	public ArrayList<News> getItems() {
		ArrayList<News> listItem = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT n.id AS newsId, n.name AS newsName, preview_text, detail_text, date_create, picture, counter, cat_id, active, c.name AS catName, c.parent_id AS parentId FROM news AS n INNER JOIN categories AS c ON n.cat_id = c.id ORDER BY n.id DESC";
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				News objItem = new News(rs.getInt("newsId"), rs.getInt("counter"), rs.getInt("active"), rs.getString("newsName"), rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),new Category(rs.getInt("cat_id"),rs.getInt("parentId"), rs.getString("catName")));
				listItem.add(objItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, st, conn);
		}

		return listItem;
	}

	public int numberOfItems() {
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM news";
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
	public int numberOfItemsActive() {
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM news WHERE active = 1";
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

	public ArrayList<News> getItemsPagination(int offset) {
		ArrayList<News> listItem = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT n.id AS newsId, n.name AS newsName, preview_text, detail_text, date_create, picture, counter, cat_id, active, c.name AS catName, c.parent_id AS parentId FROM news AS n INNER JOIN categories AS c ON n.cat_id = c.id ORDER BY n.id DESC LIMIT ?, ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {
				News objItem = new News(rs.getInt("newsId"), rs.getInt("counter"), rs.getInt("active"), rs.getString("newsName"), rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),new Category(rs.getInt("cat_id"),rs.getInt("parentId"), rs.getString("catName")));
				listItem.add(objItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listItem;
	}
	public ArrayList<News> getItemsPaginationActive(int offset) {
		ArrayList<News> listItem = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT n.id AS newsId, n.name AS newsName, preview_text, detail_text, date_create, picture, counter, cat_id, active, c.name AS catName, c.parent_id AS parentId FROM news AS n INNER JOIN categories AS c ON n.cat_id = c.id WHERE active = 1 ORDER BY n.id DESC LIMIT ?, ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {
				News objItem = new News(rs.getInt("newsId"), rs.getInt("counter"), rs.getInt("active"), rs.getString("newsName"), rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),new Category(rs.getInt("cat_id"),rs.getInt("parentId"), rs.getString("catName")));
				listItem.add(objItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listItem;
	}
	public ArrayList<News> getNewsLimit1() {
		ArrayList<News> listItem = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT n.id AS newsId, n.name AS newsName, preview_text, detail_text, date_create, picture, counter, cat_id, active, c.name AS catName, c.parent_id AS parentId FROM news AS n INNER JOIN categories AS c ON n.cat_id = c.id WHERE active = 1 ORDER BY n.id DESC LIMIT 0, 1";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				News objItem = new News(rs.getInt("newsId"), rs.getInt("counter"), rs.getInt("active"), rs.getString("newsName"), rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),new Category(rs.getInt("cat_id"),rs.getInt("parentId"), rs.getString("catName")));
				listItem.add(objItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, st, conn);
		}
		return listItem;
	}
	
	public ArrayList<News> getNewsLimit2() {
		ArrayList<News> listItem = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT n.id AS newsId, n.name AS newsName, preview_text, detail_text, date_create, picture, counter, cat_id, active, c.name AS catName, c.parent_id AS parentId FROM news AS n INNER JOIN categories AS c ON n.cat_id = c.id WHERE active = 1 ORDER BY n.id DESC LIMIT 1, 2 ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				News objItem = new News(rs.getInt("newsId"), rs.getInt("counter"), rs.getInt("active"), rs.getString("newsName"), rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),new Category(rs.getInt("cat_id"),rs.getInt("parentId"), rs.getString("catName")));
				listItem.add(objItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, st, conn);
		}
		return listItem;
	}

	public ArrayList<News> getNewsView() {
		ArrayList<News> listItem = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT n.id AS newsId, n.name AS newsName, preview_text, detail_text, date_create, picture, counter, cat_id, active, c.name AS catName, c.parent_id AS parentId FROM news AS n INNER JOIN categories AS c ON n.cat_id = c.id  WHERE active = 1 ORDER BY counter DESC LIMIT 7";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				News objItem = new News(rs.getInt("newsId"), rs.getInt("counter"), rs.getInt("active"), rs.getString("newsName"), rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),new Category(rs.getInt("cat_id"),rs.getInt("parentId"), rs.getString("catName")));
				listItem.add(objItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, st, conn);
		}
		return listItem;
	}

	public void updateActive(int i, int id) {
		conn = DBConnectionUtil.getConnection();
		String sql = "UPDATE news SET active = ? WHERE id = ?";
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

	public int deleteItem(int id) {
		conn = DBConnectionUtil.getConnection();//tạo kết nối
		String sql = "DELETE FROM news WHERE id = ?";
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

	public int addItem(News objNews, int i) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO news (name, preview_text, detail_text, picture, cat_id, created_by) "
				+ " VALUES (?, ?, ?, ?, ?, ?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objNews.getName());
			pst.setString(2, objNews.getPreview_text());
			pst.setString(3, objNews.getDetail_text());
			pst.setString(4, objNews.getPicture());
			pst.setInt(5, objNews.getObjCat().getCatId());
			pst.setInt(6, i);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}
		return result;
	}

	public News getItem(int id) {
		News objNews = null;
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT n.id AS newsId, n.name AS newsName, preview_text, detail_text, date_create, picture, counter, cat_id, active, c.name AS catName, c.parent_id AS parentId FROM news AS n INNER JOIN categories AS c ON n.cat_id = c.id  WHERE n.id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				objNews = new News(rs.getInt("newsId"), rs.getInt("counter"), rs.getInt("active"), rs.getString("newsName"), rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),new Category(rs.getInt("cat_id"),rs.getInt("parentId"), rs.getString("catName")));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, st, conn);
		}
		return objNews;
	}

	public int editItem(News objNews) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "UPDATE news SET name = ?, preview_text = ?, detail_text = ?, picture = ?, cat_id = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objNews.getName());
			pst.setString(2, objNews.getPreview_text());
			pst.setString(3, objNews.getDetail_text());
			pst.setString(4, objNews.getPicture());
			pst.setInt(5, objNews.getObjCat().getCatId());
			pst.setInt(6, objNews.getId());
			
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}
		return result;
	}

	/*public int numberOfItemsActive(int cid) {
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM news WHERE  AND active = 1";
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
	}*/

	public int numberOfItems(int id) {
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM news WHERE cat_id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();

			if(rs.next()) {
				int count = rs.getInt("count");
				return count;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return 0;
	}

	public ArrayList<News> getItemByIdPagination(int offset, int id) {
		ArrayList<News> listItem = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT n.id AS newsId, n.name AS newsName, preview_text, detail_text, date_create, picture, counter, cat_id, active, c.name AS catName, c.parent_id AS parentId FROM news AS n INNER JOIN categories AS c ON n.cat_id = c.id  WHERE active = 1 AND cat_id = ? ORDER BY newsId DESC LIMIT  ? , ?";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setInt(2, offset);
			pst.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {
				News objItem = new News(rs.getInt("newsId"), rs.getInt("counter"), rs.getInt("active"), rs.getString("newsName"), rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),new Category(rs.getInt("cat_id"),rs.getInt("parentId"), rs.getString("catName")));
				listItem.add(objItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listItem;
	}

	public ArrayList<News> searchsong(String nameNews) {
		ArrayList<News> listItem = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		
		String sql = "SELECT n.id AS newsId, n.name AS newsName, preview_text, detail_text, date_create, picture, counter, cat_id, active, c.name AS catName, c.parent_id AS parentId FROM news AS n INNER JOIN categories AS c ON n.cat_id = c.id  WHERE active = 1 AND n.name LIKE ? ORDER BY n.id DESC";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + nameNews + "%");
			rs = pst.executeQuery();
			while(rs.next()) {
				News objItem = new News(rs.getInt("newsId"), rs.getInt("counter"), rs.getInt("active"), rs.getString("newsName"), rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),new Category(rs.getInt("cat_id"),rs.getInt("parentId"), rs.getString("catName")));
				listItem.add(objItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listItem;
	}

	public void setCount(int count,int id) {
		conn = DBConnectionUtil.getConnection();
		String sql = "UPDATE news SET counter = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, count);
			pst.setInt(2, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}
	}

	public ArrayList<News> searchsongadmin(String nameNews) {
		ArrayList<News> listItem = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		
		String sql = "SELECT n.id AS newsId, n.name AS newsName, preview_text, detail_text, date_create, picture, counter, cat_id, active, c.name AS catName, c.parent_id AS parentId FROM news AS n INNER JOIN categories AS c ON n.cat_id = c.id  WHERE n.name LIKE ? ORDER BY n.id DESC";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + nameNews + "%");
			rs = pst.executeQuery();
			while(rs.next()) {
				News objItem = new News(rs.getInt("newsId"), rs.getInt("counter"), rs.getInt("active"), rs.getString("newsName"), rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),new Category(rs.getInt("cat_id"),rs.getInt("parentId"), rs.getString("catName")));
				listItem.add(objItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listItem;
	}


}
