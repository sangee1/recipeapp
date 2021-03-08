package com.example.myrecipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.myrecipe.command.IngredientCommand;
import com.example.myrecipe.domain.Ingredient;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
        this.uomConverter = uomConverter;
    }

       public IngredientCommand convert(Ingredient ingredient) {
        if (ingredient == null) {
            return null;
        }

        IngredientCommand ingredientCommand = new IngredientCommand();
        if(ingredient.getRecipe() != null) {
        	ingredientCommand.setRecipeId(ingredient.getRecipe().getID());
        }
        ingredientCommand.setId(ingredient.getId());
        ingredientCommand.setAmount(ingredient.getAmount());
        ingredientCommand.setDescription(ingredient.getDescription());
        ingredientCommand.setUom(uomConverter.convert(ingredient.getUom()));
        return ingredientCommand;
    }
}
