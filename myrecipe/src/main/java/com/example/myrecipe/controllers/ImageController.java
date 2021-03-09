package com.example.myrecipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.myrecipe.service.ImageService;
import com.example.myrecipe.service.RecipeService;

@Controller
public class ImageController {
	private ImageService imageService;
	private RecipeService recipeService;
	
	public ImageController(ImageService imageService, RecipeService recipeService) {
		super();
		this.imageService = imageService;
		this.recipeService = recipeService;
	}
	
	@GetMapping("/recipe/{id}/image")
	public String showUploadForm(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
		return "recipe/imageuploadform";
		
		
	}
	
	@PostMapping("/recipe/{recipeId}/image")
	public String saveImage(@PathVariable String recipeId , @RequestParam("imagefile")MultipartFile file) {
		imageService.saveImageFile(Long.valueOf(recipeId),file);
		return "redirect:/recipe/" + recipeId + "/show";
	}

}
