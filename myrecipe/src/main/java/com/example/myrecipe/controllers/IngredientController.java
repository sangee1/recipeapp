package com.example.myrecipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.myrecipe.service.IngredientService;
import com.example.myrecipe.service.RecipeService;

@Controller
public class IngredientController {
	
	RecipeService recipeService;
	IngredientService ingredientService;

	
	
	public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
		super();
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
	}

	@RequestMapping("/recipe/{id}/ingredients")
	public String listIngredients(@PathVariable String id,Model model) {
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
		return "recipe/ingredient/list";
		
	}
	
	@RequestMapping("/recipe/{recipeId}/ingredient/{id}/show")
	public String showIngredient(@PathVariable String recipeId, 
						@PathVariable String id, Model model) {
		model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
		return "recipe/ingredient/show";
	}
	

}
