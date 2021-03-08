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
			IngredientToIngredientCommand ingredientToIngredientCommand,
			IngredientCommandToIngredient ingredientCommandToIngredient) {
		super();
		this.recipeRepo = recipeRepo;
		this.uomRepo = uomRepo;
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
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
			return new IngredientCommand();
		}
		else {
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
			
			Ingredient newIng = ingredientCommandToIngredient.convert(ingCommand);
			newIng.setRecipe(recipe);
			recipe.getIngredients().add(newIng);
		}
		Recipe savedRecipe = recipeRepo.save(recipe);
		
		Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream().
										filter(recipeIngredient -> recipeIngredient.getDescription().equals(ingCommand.getDescription())).
										filter(recipeIngredient -> recipeIngredient.getAmount().equals(ingCommand.getAmount())).
										filter(recipeIngredient -> recipeIngredient.getUom().getId().equals(ingCommand.getUom().getId())).
										findFirst();
		
		
		return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
		}
		
	}

}
