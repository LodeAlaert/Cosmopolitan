package Models;

public class Recipe {

	// variables
	private int recipe_id;
	private String name;
	private int difficulty;
	private int persons;
	private String description;
	private String recipe;
	private String price;
	private int time;

	// Getters & Setters
	public int getRecipe_id() {
		return recipe_id;
	}

	public void setRecipe_id(int recipe_id) {
		this.recipe_id = recipe_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public int getPersons() {
		return persons;
	}

	public void setPersons(int persons) {
		this.persons = persons;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRecipe() {
		return recipe;
	}

	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}


	// not-standard constructor
	public Recipe(int recipe_id, String name, int difficulty, int persons, String description, String recipe,
			String price, int time) {
		this.recipe_id = recipe_id;
		this.name = name;
		this.difficulty = difficulty;
		this.persons = persons;
		this.description = description;
		this.recipe = recipe;
		this.price = price;
		this.time = time;
	}
}
