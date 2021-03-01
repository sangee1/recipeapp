package com.example.myrecipe.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.myrecipe.domain.Category;
import com.example.myrecipe.domain.UnitOfMeasure;
import com.example.myrecipe.repositories.CategoryRepository;
import com.example.myrecipe.repositories.UnitOfMeasureRepository;

@Controller
public class IndexController {
	
	private CategoryRepository categoryRepo;
	private UnitOfMeasureRepository uomRepo;
	
	
	
	public IndexController(CategoryRepository categoryRepo, UnitOfMeasureRepository uomRepo) {
		super();
		this.categoryRepo = categoryRepo;
		this.uomRepo = uomRepo;
	}



	@RequestMapping({"/","/index"})
	public String getIndexPage() {
		Optional<Category> category = categoryRepo.findByDescription("American");
		Optional<UnitOfMeasure> uom = uomRepo.findByDescription("Teaspoon");
	System.out.println("Category id is : " +  category.get().getId());
	System.out.println("Uom id is : " + uom.get().getId());
		return "index";
	}

}
