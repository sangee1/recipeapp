package com.example.myrecipe.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.myrecipe.domain.Recipe;
import com.example.myrecipe.repositories.RecipeRepository;

class RecipeServiceImplTest {
	
	RecipeServiceImpl recipeServiceImpl;
	
	@Mock
	RecipeRepository recipeRepo;

	@BeforeEach
	void setUp() throws Exception {
		
		MockitoAnnotations.openMocks(this);
		recipeServiceImpl = new RecipeServiceImpl(recipeRepo, null, null);
	}
	

	@Test
	public void getRecipeByIdTest() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setID(2L);
		when(recipeRepo.findById(2L)).thenReturn(Optional.of(recipe));
		
		Recipe result = recipeServiceImpl.findById(2L);
		assertNotNull(result, "Null recipe returned");
		verify(recipeRepo, times(1)).findById(2L);
		verify(recipeRepo,never()).findAll();
	}

}
