package com.example.myrecipe.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.myrecipe.domain.Recipe;
import com.example.myrecipe.service.RecipeService;

class RecipeControllerTest {
	
	RecipeController recipeController;
	
	@Mock
	RecipeService recipeService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		recipeController = new RecipeController(recipeService);
	}

	@Test
	void testShowRecipe() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
		Recipe recipe = new Recipe();
		recipe.setID(2L);
		when(recipeService.findById(2L)).thenReturn(recipe);
		mockMvc.perform(get("/recipe/2/show")).andExpect(status().isOk()).andExpect(view().name("recipe/show"));
		
	
	}

}
