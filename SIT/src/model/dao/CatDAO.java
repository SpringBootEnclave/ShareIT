package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Category;
import util.DBConnectionUtil;
import util.DefineUtil;

public class CatDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public int numberOfItems() {
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM categories";
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

	public ArrayList<Category> getItemsPagination(int offset) {
		ArrayList<Category> listItem = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();//táº¡o káº¿t ná»‘i dáº¿n csdl
		String sql = "SELECT * FROM categories WHERE parent_id = 0 ORDER BY id DESC LIMIT ?, ?";

		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();

			while (rs.next()) {
				Category objItem = new Category(rs.getInt("id"),rs.getInt("parent_id"), rs.getString("name"));
				listItem.add(objItem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listItem;
	}

	public int addItem(Category objCat) {

		int result = 0;
		conn = DBConnectionUtil.getConnection();
		
		String sql = "INSERT INTO categories (name) VALUES (?)";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objCat.getCatName());

			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}

		return result;
	}

	public int deleteItem(int id) {
		conn = DBConnectionUtil.getConnection();//táº¡o káº¿t ná»‘i
		
		String sql = "DELETE FROM categories WHERE id = ?";
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

	public Category getItem(int catId) {
		Category objItem  = null;
		 
		conn = DBConnectionUtil.getConnection();//táº¡o káº¿t ná»‘i
		
		String sql = "SELECT * FROM categories WHERE id = ?";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, catId);
			rs = pst.executeQuery();
			if (rs.next()) {
				objItem = new Category(rs.getInt("id"),rs.getInt("parent_id"), rs.getString("name"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}
		return objItem;
	}

	public int editItem(Category objCat) {
		conn = DBConnectionUtil.getConnection();
		
		String sql = "UPDATE categories SET name = ? WHERE id = ?";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objCat.getCatName());
			pst.setInt(2, objCat.getCatId());

			result = pst.executeUpdate();///executeUpdate tráº£ vá»� result sá»‘ khÃ¡c 0 nÃªn khi sang bÃªn chá»‰ edit Sevrlet chá»‰ cáº§n catDAO.editItem(objCat) > 0
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}

		return result;
	}
	
	public ArrayList<Category> getCatChild(int id) {
		ArrayList<Category> listItem = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();//táº¡o káº¿t ná»‘i dáº¿n csdl
		String sql = "SELECT * FROM categories WHERE parent_id = ?";

		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();

			while (rs.next()) {
				Category objItem = new Category(rs.getInt("id"), rs.getInt("parent_id"),rs.getString("name"));
				listItem.add(objItem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listItem;
	}
	public ArrayList<Category> getCatChild() {
		ArrayList<Category> listItem = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();//táº¡o káº¿t ná»‘i dáº¿n csdl
		String sql = "SELECT * FROM categories WHERE parent_id > 0";

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				Category objItem = new Category(rs.getInt("id"), rs.getInt("parent_id"),rs.getString("name"));
				listItem.add(objItem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listItem;
	}


	public ArrayList<Category> getItems() {
		ArrayList<Category> listItem = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM categories WHERE parent_id = 0";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Category objItem = new Category(rs.getInt("id"), rs.getInt("parent_id"),rs.getString("name"));
				listItem.add(objItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, st, conn);
		}
		return listItem;
	}
	
	public int addItemCha(Category objCat) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		
		String sql = "INSERT INTO categories (name,parent_id) VALUES (?,?)";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objCat.getCatName());
			pst.setInt(2, 0);
			
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}

		return result;
	}

	public int addItemCon(Category objCat, int cat) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		
		String sql = "INSERT INTO categories (name, parent_id) VALUES (?, ?)";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objCat.getCatName());
			pst.setInt(2, cat);

			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}

		return result;
	}

	public int editItemCatCon(Category objCat, int catCon, String name, int catCha) {
		conn = DBConnectionUtil.getConnection();
		
		String sql = "UPDATE categories SET name = ? , parent_id = ? WHERE id = ?";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objCat.getCatName());
			pst.setInt(2, catCha);
			pst.setInt(3, catCon);

			result = pst.executeUpdate();///executeUpdate tráº£ vá»� result sá»‘ khÃ¡c 0 nÃªn khi sang bÃªn chá»‰ edit Sevrlet chá»‰ cáº§n catDAO.editItem(objCat) > 0
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}

		return result;
	}

	public ArrayList<Category> getItem() {
		ArrayList<Category> listCat = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM news ORDER BY id DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Category objCat = new Category(rs.getInt("id"), rs.getInt("parent_id"),rs.getString("name"));
				listCat.add(objCat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUtil.close(rs, st, conn);
		}
		
		return listCat;
	}

	public int deleteCatCon(int id) {
		conn = DBConnectionUtil.getConnection();//táº¡o káº¿t ná»‘i
		
		String sql = "DELETE FROM categories WHERE parent_id = ?";
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

	public Category getItemCha(int id) {
		Category objItem  = null;
		 
		conn = DBConnectionUtil.getConnection();//táº¡o káº¿t ná»‘i
		
		String sql = "SELECT * FROM categories WHERE parent_id = ?";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				objItem = new Category(rs.getInt("id"),rs.getInt("parent_id"), rs.getString("name"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}
		return objItem;
	}

}
