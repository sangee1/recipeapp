package com.example.myrecipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.myrecipe.command.RecipeCommand;
import com.example.myrecipe.domain.Recipe;
import com.example.myrecipe.service.RecipeService;

@Controller
public class RecipeController {
	
	private final RecipeService recipeService;
	

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	
	@RequestMapping("/recipe/{id}/show")
	public String showRecipe(@PathVariable String id,Model model) {
		
		model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
		return "recipe/show";
	}
	
	@RequestMapping("/recipe/new")
	public String newRecipe(Model model) {
		model.addAttribute("recipe",new RecipeCommand());
		return "recipe/recipeform";
		
	}
	
	@PostMapping()
	@RequestMapping("recipe")
	public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
		
		RecipeCommand savedRecipe = recipeService.save(command);
		return "redirect:/recipe/" + savedRecipe.getID() + "/show";
		
	}
	
	@RequestMapping("/recipe/{id}/update")
	public String updateRecipe(@PathVariable String id,Model model) {
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
		return "recipe/recipeform";
	}
	
	@RequestMapping("/recipe/{id}/delete")
	public String deleteById(@PathVariable String id) {
		recipeService.deleteById(Long.valueOf(id));
		return "redirect:/";
	}
	

}
