package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnectionUtil {
	private static Connection conn;
	private static String URL;
	private static String USER;
	private static String PASSWORD;

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Properties properties = PropertiesUtil.readProperties();
			URL = properties.getProperty("url");
			USER = properties.getProperty("user");
			PASSWORD = properties.getProperty("password");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);// tạo chuỗi kết nối
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // nạp driver
		return conn;
	}

	public static void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void close(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void close(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs, Statement st, Connection conn) {
		close(rs);
		close(st);
		close(conn);
	}

	public static void close(PreparedStatement pst, Connection conn) {
		close(pst);
		close(conn);
	}

	public static void main(String[] args) {
		System.out.println(DBConnectionUtil.getConnection());
	}
}
