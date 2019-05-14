package model.bean;

public class Category {
	private int catId;
	private int parent_id;
	private String catName;

	public Category(int catId, int parent_id, String catName) {
		super();
		this.catId = catId;
		this.parent_id = parent_id;
		this.catName = catName;
	}

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

}
