package com.example.myrecipe.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.myrecipe.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{

}
