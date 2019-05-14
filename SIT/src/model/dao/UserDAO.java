package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.User;
import util.DBConnectionUtil;
import util.DefineUtil;


public class UserDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public User getItemByUserNameAndPassWord(String username, String password) {
		User objItem = null;
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM users WHERE username = ? && password = ? && active = 1";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1,username);
			pst.setString(2,password);
			rs = pst.executeQuery();
			if(rs.next()) {
				objItem = new User(rs.getInt("id"), rs.getInt("active"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, st, conn);
		}
		return objItem;//nên để trả về kiểu đối tượng thay vì boolean hay int
	}

	public int numberOfItems() {
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM users";
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

	public ArrayList<User> getItemsPagination(int offset) {
		ArrayList<User> listItem = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM users ORDER BY id DESC LIMIT ?, ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {
				User item = new User(rs.getInt("id"), rs.getInt("active"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"));
				listItem.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, st, conn);
		}
		return listItem;
	}

	public int deleteItem(int id) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM users WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}
		return result;
	}

	public User getItem(int uid) {
		User item = null;
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM users WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, uid);
			rs = pst.executeQuery();
			if(rs.next()) {
				item = new User(rs.getInt("id"), rs.getInt("active"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, st, conn);
		}
		return item;
	}

	public boolean hasUser(String username) {
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM users WHERE username = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			rs = pst.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		
		return false;
	}

	public int addItem(User item) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO users(active, username, password, fullname) VALUES (?, ?, ?, ?)";
		try {
			pst = conn.prepareStatement(sql);
			
			pst.setInt(1, 1);
			pst.setString(2, item.getUsername());
			pst.setString(3, item.getPassword());
			pst.setString(4, item.getFullname());
			
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}
		return result;
	}

	public int editItem(User item) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "UPDATE users SET password = ?, fullname = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, item.getPassword());
			pst.setString(2, item.getFullname());
			pst.setInt(3, item.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}
		return result;
	}

	public void updateActive(int i, int id) {
		conn = DBConnectionUtil.getConnection();
		String sql = "UPDATE users SET active = ? WHERE id = ?";
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

	
}
