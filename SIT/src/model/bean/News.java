package model.bean;

import java.sql.Timestamp;

public class News {
	private int id;
	private int counter;
	private int active;
	private String name;
	private String preview_text;
	private String detail_text;
	private Timestamp date_create;
	private String picture;
	private Category objCat;
	private User user;

	public News(int id, int counter, int active, String name, String preview_text, String detail_text,
			Timestamp date_create, String picture, Category objCat, User user) {
		super();
		this.id = id;
		this.counter = counter;
		this.active = active;
		this.name = name;
		this.preview_text = preview_text;
		this.detail_text = detail_text;
		this.date_create = date_create;
		this.picture = picture;
		this.objCat = objCat;
		this.user = user;
	}

	

	public News(String name) {
		super();
		this.name = name;
	}



	public News(int id, int counter, int active, String name, String preview_text, String detail_text,
			Timestamp date_create, String picture, Category objCat) {
		super();
		this.id = id;
		this.counter = counter;
		this.active = active;
		this.name = name;
		this.preview_text = preview_text;
		this.detail_text = detail_text;
		this.date_create = date_create;
		this.picture = picture;
		this.objCat = objCat;
	}



	public News() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPreview_text() {
		return preview_text;
	}

	public void setPreview_text(String preview_text) {
		this.preview_text = preview_text;
	}

	public String getDetail_text() {
		return detail_text;
	}

	public void setDetail_text(String detail_text) {
		this.detail_text = detail_text;
	}

	public Timestamp getDate_create() {
		return date_create;
	}

	public void setDate_create(Timestamp date_create) {
		this.date_create = date_create;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Category getObjCat() {
		return objCat;
	}

	public void setObjCat(Category objCat) {
		this.objCat = objCat;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
