package com.example.myrecipe.bootstrap;

import com.example.myrecipe.repositories.CategoryRepository;
import com.example.myrecipe.repositories.RecipeRepository;
import com.example.myrecipe.repositories.UnitOfMeasureRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.example.myrecipe.domain.*;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent>{
	
	private CategoryRepository categoryRepo;
	private RecipeRepository recipeRepo;
	private UnitOfMeasureRepository uomRepo;
	
	
	public RecipeBootstrap(CategoryRepository categoryRepo, RecipeRepository recipeRepo,
			UnitOfMeasureRepository uomRepo) {
		
		this.categoryRepo = categoryRepo;
		this.recipeRepo = recipeRepo;
		this.uomRepo = uomRepo;
	}
	
	private List<Recipe> getRecipes(){
		List<Recipe> recipeList = new ArrayList<>();
		
		Recipe sandwichRecipe = new Recipe();
		sandwichRecipe.getCategories().add(categoryRepo.findByDescription("American").get());
		sandwichRecipe.setCookTime(5);
		sandwichRecipe.setDescription("Veggie Sandwich");
		sandwichRecipe.setDifficulty(Difficulty.EASY);
		sandwichRecipe.setDirections("1.Take a slices of brown bread."
				+ "2.Spread two tablespoons of mayonnaise over it.3.places slices of tomatoes ,onions and cucumbers over the slice."
				+ "4.Place another bread slice over it.5.Toast it in a sandwich maker or pan.6.Eat!");
		sandwichRecipe.setImage(null);
		sandwichRecipe.setIngredients(null);
		Notes sandwichNotes = new Notes();
		sandwichNotes.setRecipeNotes("Be creative.Experiment with different veggie combos n dressings");
		sandwichRecipe.setNotes(sandwichNotes);
		sandwichRecipe.setPrepTime(5);
		sandwichRecipe.setServings(2);
		sandwichRecipe.setSource("My own cooking experience");
		sandwichRecipe.setUrl("https://www.google.co.in");
		Set<Ingredient> ing = new HashSet<>();
	
		ing.add(new Ingredient("Bread",new BigDecimal(2),uomRepo.findByDescription("slices").get(),sandwichRecipe));

		ing.add(new Ingredient("Tomatoes",new BigDecimal(3),uomRepo.findByDescription("slices").get(),sandwichRecipe));
		ing.add(new Ingredient("Onions",new BigDecimal(3),uomRepo.findByDescription("slices").get(),sandwichRecipe));
		ing.add(new Ingredient("Cucmbers",new BigDecimal(3),uomRepo.findByDescription("slices").get(),sandwichRecipe));
		ing.add(new Ingredient("Mayonnaise",new BigDecimal(2),uomRepo.findByDescription("Tablespoon").get(),sandwichRecipe));
		sandwichRecipe.setIngredients(ing);
		
		
		Recipe pakodaRecipe = new Recipe();
		pakodaRecipe.getCategories().add(categoryRepo.findByDescription("Indian").get());
		pakodaRecipe.setCookTime(5);
		pakodaRecipe.setDescription("Onion Pakoda");
		pakodaRecipe.setDifficulty(Difficulty.EASY);
		pakodaRecipe.setDirections("1.Take a bowl and put 3 tbsp of Besan."
				+ "2.Add some rice flour,chilli powder and salt to taste.3.Mix with water and make a batter with semi thick consistency."
				+ "4.Cut 4 onions into slices.5.Dip the onions into the batter and fry it on medium hot oil till it turns brown.6.Eat!");
		pakodaRecipe.setImage(null);
		pakodaRecipe.setIngredients(null);
		Notes pakodaNotes = new Notes();
		sandwichNotes.setRecipeNotes("Be creative.Experiment with different veggie combos n dressings");
		pakodaRecipe.setNotes(pakodaNotes);
		pakodaRecipe.setPrepTime(5);
		pakodaRecipe.setServings(3);
		pakodaRecipe.setSource("My cooking");
		pakodaRecipe.setUrl("www.takeiteasy.com");
		Set<Ingredient> ing1=new HashSet<>();
		ing1.add(new Ingredient("Besan",new BigDecimal(4),uomRepo.findByDescription("Tablespoon").get(),pakodaRecipe));
		ing1.add(new Ingredient("Rice flour",new BigDecimal(2),uomRepo.findByDescription("Tablespoon").get(),pakodaRecipe));
		ing1.add(new Ingredient("Chilli powder",new BigDecimal(2),uomRepo.findByDescription("Tablespoon").get(),pakodaRecipe));
		ing1.add(new Ingredient("Salt",new BigDecimal(13),uomRepo.findByDescription("Teaspoon").get(),pakodaRecipe));
		ing1.add(new Ingredient("Onion",new BigDecimal(4),uomRepo.findByDescription("slices").get(),pakodaRecipe));
		pakodaRecipe.setIngredients(ing1);
		
		
		recipeList.add(sandwichRecipe);
		recipeList.add(pakodaRecipe);
		
		return recipeList;
		
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		recipeRepo.saveAll(getRecipes());
		
	}
	
	

}
