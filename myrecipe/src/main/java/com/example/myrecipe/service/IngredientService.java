package com.example.myrecipe.service;

import com.example.myrecipe.command.IngredientCommand;

public interface IngredientService {
	IngredientCommand findByRecipeIdAndIngredientId(Long recipeId,Long ingredientId );
	IngredientCommand saveIngredientCommand(IngredientCommand ingCommand);
	void deleteById(Long recipeId,Long ingredientId);
}
