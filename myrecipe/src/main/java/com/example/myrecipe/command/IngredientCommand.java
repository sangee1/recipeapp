package com.example.myrecipe.command;

import java.math.BigDecimal;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.example.myrecipe.domain.Recipe;
import com.example.myrecipe.domain.UnitOfMeasure;

public class IngredientCommand {
	
	private Long id;
	private String description;
	private BigDecimal amount;
	private Recipe recipe;
	private UnitOfMeasureCommand uom;
	
	
	public IngredientCommand() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Recipe getRecipe() {
		return recipe;
	}
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	public UnitOfMeasureCommand getUom() {
		return uom;
	}
	public void setUom(UnitOfMeasureCommand uom) {
		this.uom = uom;
	}
	
	


}
