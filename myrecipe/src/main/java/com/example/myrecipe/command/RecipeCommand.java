package com.example.myrecipe.command;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.example.myrecipe.domain.Category;
import com.example.myrecipe.domain.Difficulty;
import com.example.myrecipe.domain.Ingredient;
import com.example.myrecipe.domain.Notes;

public class RecipeCommand {
	
private Long ID;
	
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
	private String directions;
	private Set<IngredientCommand> ingredients = new HashSet<>();
	private Byte[]image;
	private Difficulty difficulty;
	private Set<CategoryCommand> categories = new HashSet<>();
	private NotesCommand notes;
	
	
	public RecipeCommand() {
		
	}
	
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getPrepTime() {
		return prepTime;
	}
	public void setPrepTime(Integer prepTime) {
		this.prepTime = prepTime;
	}
	public Integer getCookTime() {
		return cookTime;
	}
	public void setCookTime(Integer cookTime) {
		this.cookTime = cookTime;
	}
	public Integer getServings() {
		return servings;
	}
	public void setServings(Integer servings) {
		this.servings = servings;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDirections() {
		return directions;
	}
	public void setDirections(String directions) {
		this.directions = directions;
	}
	public Set<IngredientCommand> getIngredients() {
		return ingredients;
	}
	public void setIngredients(Set<IngredientCommand> ingredients) {
		this.ingredients = ingredients;
	}
	public Byte[] getImage() {
		return image;
	}
	public void setImage(Byte[] image) {
		this.image = image;
	}
	public Difficulty getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
	public Set<CategoryCommand> getCategories() {
		return categories;
	}
	public void setCategories(Set<CategoryCommand> categories) {
		this.categories = categories;
	}
	public NotesCommand getNotes() {
		return notes;
	}
	public void setNotes(NotesCommand notes) {
		this.notes = notes;
	}
	
	
	

}
