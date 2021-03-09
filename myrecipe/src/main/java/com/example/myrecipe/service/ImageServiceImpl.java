package com.example.myrecipe.service;

import java.io.IOException;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.myrecipe.domain.Recipe;
import com.example.myrecipe.repositories.RecipeRepository;



@Service
public class ImageServiceImpl implements ImageService {
	
	RecipeRepository recipeRepo;
	Logger log = LoggerFactory.getLogger(getClass());
	
	public ImageServiceImpl(RecipeRepository recipeRepo) {
		this.recipeRepo = recipeRepo;
	}
	
	@Override
	@Transactional
	public void saveImageFile(Long recipeId, MultipartFile file) {
		try {
			Recipe recipe = recipeRepo.findById(recipeId).get();
			Byte[] objects = new Byte[file.getBytes().length];
			int i = 0;
			for(byte b : file.getBytes()) {
				objects[i++] = b;
			}
			recipe.setImage(objects);
			recipeRepo.save(recipe);
		}
		catch(IOException e) {
			log.error("Error occured", e);
			e.printStackTrace();
		}

	}

}
