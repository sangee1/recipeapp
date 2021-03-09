package com.example.myrecipe.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.myrecipe.command.RecipeCommand;
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
	
	@GetMapping("/recipe/{recipeId}/recipeimage")
	public void renderImageFromDB(@PathVariable String recipeId,HttpServletResponse response) throws IOException {
		RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));
		
		if(recipeCommand.getImage() != null) {
		byte[] byteArray = new byte[recipeCommand.getImage().length];
		
		int i =0;
		
		for(byte wrappedByte : recipeCommand.getImage()) {
			byteArray[i++] =  wrappedByte;
		}
		response.setContentType("image/jpeg");
		InputStream is = new ByteArrayInputStream(byteArray);
		IOUtils.copy(is, response.getOutputStream());
		}
		
	}

}
