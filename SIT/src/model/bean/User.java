package model.bean;

public class User {
	private int id;
	private int active;
	private String username;
	private String password;
	private String fullname;

	public User(int id, int active, String username, String password, String fullname) {
		super();
		this.id = id;
		this.active = active;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

}
