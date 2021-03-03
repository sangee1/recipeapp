package com.example.myrecipe.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.myrecipe.domain.Category;
import com.example.myrecipe.domain.UnitOfMeasure;
import com.example.myrecipe.repositories.CategoryRepository;
import com.example.myrecipe.repositories.UnitOfMeasureRepository;
import com.example.myrecipe.service.RecipeService;

@Controller
public class IndexController {
	
	private RecipeService recipeService;
	
	public IndexController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}

	
	@RequestMapping({"/","/index"})
	public String getIndexPage(Model model) {
		model.addAttribute("recipes", recipeService.getRecipes());
		return "index";
	}

}
