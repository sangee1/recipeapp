package com.example.myrecipe.service;

import java.util.Optional;
import org.slf4j.*;

import org.springframework.stereotype.Service;

import com.example.myrecipe.command.IngredientCommand;
import com.example.myrecipe.converters.IngredientToIngredientCommand;
import com.example.myrecipe.domain.Recipe;
import com.example.myrecipe.repositories.RecipeRepository;

@Service
public class IngredientServiceImpl implements IngredientService {
	
	
	RecipeRepository recipeRepo;
	IngredientToIngredientCommand ingredientToIngredientCommand;
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	public IngredientServiceImpl(RecipeRepository recipeRepo,
			IngredientToIngredientCommand ingredientToIngredientCommand) {
		
		this.recipeRepo = recipeRepo;
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
	}


	@Override
	public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
		
		Optional<Recipe> recipeOptional = recipeRepo.findById(recipeId);
		
		if(!recipeOptional.isPresent()) {
			log.error("Recipe Id : " + recipeId + "not found");
			
		}
		
		Recipe recipe = recipeOptional.get();
		Optional<IngredientCommand> ingredientOptional = recipe.getIngredients().stream().
				                  filter(ingredient -> ingredient.getId().equals(ingredientId)).
		                          map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();
		
		if(!ingredientOptional.isPresent()) {
			log.error("Ingredient Id is not found");
		}
		
		return ingredientOptional.get();
	}

}
