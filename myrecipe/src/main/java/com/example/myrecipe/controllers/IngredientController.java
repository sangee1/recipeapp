package com.example.myrecipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.myrecipe.command.IngredientCommand;
import com.example.myrecipe.command.RecipeCommand;
import com.example.myrecipe.command.UnitOfMeasureCommand;
import com.example.myrecipe.service.IngredientService;
import com.example.myrecipe.service.RecipeService;
import com.example.myrecipe.service.UnitOfMeasureService;

@Controller
public class IngredientController {
	
	RecipeService recipeService;
	IngredientService ingredientService;
	UnitOfMeasureService uomService;

	
	
	public IngredientController(RecipeService recipeService, IngredientService ingredientService,
			UnitOfMeasureService uomService) {
		
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
		this.uomService = uomService;
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
	
	@GetMapping
	@RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
	public String updateIngredient(@PathVariable String recipeId, 
			            @PathVariable String ingredientId , Model model) {
		model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
		model.addAttribute("uomList",uomService.listAllUoms() );
		return "recipe/ingredient/ingredientform";
		
		
	}
	
	@PostMapping
	@RequestMapping("/recipe/{recipeId}/ingredient")
	public String saveOrUpdateIngredient(@ModelAttribute IngredientCommand ingCommand) {
		IngredientCommand savedCommand = ingredientService.saveIngredientCommand(ingCommand);
		return "redirect:/recipe/" +  savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
		
	}
	
	@PostMapping
	@RequestMapping("/recipe/{recipeId}/ingredient/new")
	public String newIngredient(@PathVariable String recipeId,Model model) {
		RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setRecipeId(Long.valueOf(recipeId));
		model.addAttribute("ingredient", ingredientCommand);
		ingredientCommand.setUom(new UnitOfMeasureCommand());
		model.addAttribute("uomList", uomService.listAllUoms());
		return "recipe/ingredient/ingredientform";
		
	}
	

}
