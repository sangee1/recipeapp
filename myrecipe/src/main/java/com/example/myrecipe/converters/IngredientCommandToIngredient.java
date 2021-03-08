package com.example.myrecipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.myrecipe.command.IngredientCommand;
import com.example.myrecipe.domain.Ingredient;
import com.example.myrecipe.domain.Recipe;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
        this.uomConverter = uomConverter;
    }

     @Override
    public Ingredient convert(IngredientCommand source) {
        if (source == null) {
            return null;
        }

        final Ingredient ingredient = new Ingredient();
        ingredient.setId(source.getId());
        if(source.getRecipeId() != null) {
        	Recipe recipe = new Recipe();
        	recipe.setID(source.getRecipeId());
        	ingredient.setRecipe(recipe);
            recipe.getIngredients().add(ingredient);
        }
        ingredient.setAmount(source.getAmount());
        ingredient.setDescription(source.getDescription());
        ingredient.setUom(uomConverter.convert(source.getUom()));
        return ingredient;
    }
}
