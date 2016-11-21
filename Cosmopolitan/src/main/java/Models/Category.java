package Models;

public class Category {
	
	// Variables
	private int category_ID;
	private String name;
	
	// Getters & setters
	public int getCategory_ID() {
		return category_ID;
	}
	public void setCategory_ID(int category_ID) {
		this.category_ID = category_ID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	// standard cons
	public Category(){
		
	}
	
	// not standard constructor
	public Category(int category_ID, String name){
		this.category_ID = category_ID;
		this.name = name;
	}
	
	

}
