package model.bean;

import java.sql.Timestamp;

public class Comment {
	private int id;
	private int newsid;
	private int active;
	private int parent_id;
	private String username;
	private String content;
	private Timestamp timecmt;
	private News objNews;

	public Comment(int id, int newsid, int active, String username, String content, Timestamp timecmt, News objNews) {
		super();
		this.id = id;
		this.newsid = newsid;
		this.active = active;
		this.username = username;
		this.content = content;
		this.timecmt = timecmt;
		this.objNews = objNews;
	}

	public Comment(int id, int newsid, int active, int parent_id, String username, String content, Timestamp timecmt) {
		super();
		this.id = id;
		this.newsid = newsid;
		this.active = active;
		this.parent_id = parent_id;
		this.username = username;
		this.content = content;
		this.timecmt = timecmt;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNewsid() {
		return newsid;
	}

	public void setNewsid(int newsid) {
		this.newsid = newsid;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getTimecmt() {
		return timecmt;
	}

	public void setTimecmt(Timestamp timecmt) {
		this.timecmt = timecmt;
	}

	public News getObjNews() {
		return objNews;
	}

	public void setObjNews(News objNews) {
		this.objNews = objNews;
	}

}
