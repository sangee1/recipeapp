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
	private Long recipeId;
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
	
	public Long getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(Long recipeId) {
		this.recipeId = recipeId;
	}
	public UnitOfMeasureCommand getUom() {
		return uom;
	}
	public void setUom(UnitOfMeasureCommand uom) {
		this.uom = uom;
	}
	
	


}
