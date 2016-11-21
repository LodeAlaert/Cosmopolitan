package Models;

public class Ingredient {
	
	// variables
	private int ingredient_ID;
	private String name;
	private String unit;

	// Getters & Setters
	public int getIngredient_ID() {
		return ingredient_ID;
	}
	public void setIngredient_ID(int ingredient_ID) {
		this.ingredient_ID = ingredient_ID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	// Standard constructor
	public Ingredient(){
		
	}
	
	// not standard constructor
	public Ingredient(int ingredient_id, String name, String unit){
		this.ingredient_ID = ingredient_id;
		this.name = name;
		this.unit = unit;
	}
	

}
