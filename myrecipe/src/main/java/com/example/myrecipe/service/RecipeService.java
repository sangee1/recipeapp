package com.example.myrecipe.service;

import java.util.Set;

import com.example.myrecipe.command.RecipeCommand;
import com.example.myrecipe.domain.Recipe;

public interface RecipeService {
	
	Set<Recipe> getRecipes();
	Recipe findById(long l);
	RecipeCommand save(RecipeCommand recipeCommand);

}
