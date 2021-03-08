package com.example.myrecipe.service;

import java.util.Optional;
import org.slf4j.*;

import org.springframework.stereotype.Service;

import com.example.myrecipe.command.IngredientCommand;
import com.example.myrecipe.converters.IngredientCommandToIngredient;
import com.example.myrecipe.converters.IngredientToIngredientCommand;
import com.example.myrecipe.domain.Ingredient;
import com.example.myrecipe.domain.Recipe;
import com.example.myrecipe.repositories.RecipeRepository;
import com.example.myrecipe.repositories.UnitOfMeasureRepository;

@Service
public class IngredientServiceImpl implements IngredientService {
	
	
	RecipeRepository recipeRepo;
	UnitOfMeasureRepository uomRepo;
	IngredientToIngredientCommand ingredientToIngredientCommand;
	IngredientCommandToIngredient ingredientCommandToIngredient;
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	public IngredientServiceImpl(RecipeRepository recipeRepo, UnitOfMeasureRepository uomRepo,
			IngredientToIngredientCommand ingredientToIngredientCommand) {
		super();
		this.recipeRepo = recipeRepo;
		this.uomRepo = uomRepo;
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
	
	public IngredientCommand saveIngredientCommand(IngredientCommand ingCommand){
		Optional<Recipe> recipeOptional = recipeRepo.findById(ingCommand.getRecipeId());
		if(!recipeOptional.isPresent()) {
			log.error("Recipe not found");
		}
		Recipe recipe = recipeOptional.get();
		Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream().filter(ing -> ing.getId().equals(ingCommand.getId())).findFirst();
		
		if(ingredientOptional.isPresent()) {
			//update the ingredient
			Ingredient ingredient = ingredientOptional.get();
			ingredient.setDescription(ingCommand.getDescription());
			ingredient.setAmount(ingCommand.getAmount());
			ingredient.setUom(uomRepo.findById(ingCommand.getUom().getId()).get());
		}
		else {
			recipe.getIngredients().add(ingredientCommandToIngredient.convert(ingCommand));
		}
		Recipe savedRecipe = recipeRepo.save(recipe);
		
		return ingredientToIngredientCommand.convert(savedRecipe.getIngredients().
						stream().filter(ingredient -> ingCommand.getId().equals(ingredient.getId())).findFirst().get());
		
	}

}
