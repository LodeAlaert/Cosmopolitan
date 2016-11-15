/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataEntities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Olivier
 */

// The Entity annotation indicates that this class is a JPA entity.
// The Table annotation specifies the name for the table in the db.
@Entity
@Table(name = "category")
public class Category {
	
	// Variables
	@Id
	private int Category_ID;
	
	@NotNull
	private String Name;
	
	
	
	// Getters & Setters
	public int getCategory_ID() {
		return Category_ID;
	}

	public void setCategory_ID(int category_ID) {
		Category_ID = category_ID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	
	
	// standard constructor
	public Category() {
	}
	
	// not-standard constructor
	public Category(int Category_ID, String Name){
		this.Category_ID = Category_ID;
		this.Name = Name;
	}
}
