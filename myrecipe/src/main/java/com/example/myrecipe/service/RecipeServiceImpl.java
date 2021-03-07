package com.example.myrecipe.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myrecipe.command.RecipeCommand;
import com.example.myrecipe.converters.RecipeCommandToRecipe;
import com.example.myrecipe.converters.RecipeToRecipeCommand;
import com.example.myrecipe.domain.Recipe;
import com.example.myrecipe.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {

	private RecipeRepository recipeRepo;
	private RecipeCommandToRecipe recipeCommandToRecipe;
	private RecipeToRecipeCommand recipeToRecipeCommand;
	
	
	public RecipeServiceImpl(RecipeRepository recipeRepo, RecipeCommandToRecipe recipeCommandToRecipe,
			RecipeToRecipeCommand recipeToRecipeCommand) {
		super();
		this.recipeRepo = recipeRepo;
		this.recipeCommandToRecipe = recipeCommandToRecipe;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
	}


	@Override
	public Set<Recipe> getRecipes() {
		// TODO Auto-generated method stub
		Set<Recipe> result = new HashSet<>();
		recipeRepo.findAll().iterator().forEachRemaining(result::add);
		return result;
	}


	public Recipe findById(long l) {
		// TODO Auto-generated method stub
		Optional<Recipe> recipeOptional = recipeRepo.findById(l);
		if(!recipeOptional.isPresent())
			throw new RuntimeException("No recipe found!");
		return recipeOptional.get();
		
	}


	@Override
	@Transactional
	public RecipeCommand save(RecipeCommand recipeCommand) {
		Recipe recipe = recipeCommandToRecipe.convert(recipeCommand);
		Recipe newRecipe = recipeRepo.save(recipe);
		return recipeToRecipeCommand.convert(newRecipe);
		
	}


	@Override
	@Transactional
	public RecipeCommand findCommandById(Long l) {
		return recipeToRecipeCommand.convert(findById(l));
	}


	@Override
	public void deleteById(Long l) {
		recipeRepo.deleteById(l);
		
	}

}
