package com.example.myrecipe.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.myrecipe.domain.Recipe;
import com.example.myrecipe.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {

	private RecipeRepository recipeRepo;
	
	
	public RecipeServiceImpl(RecipeRepository recipeRepo) {
		
		this.recipeRepo = recipeRepo;
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

}
