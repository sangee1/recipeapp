package com.example.myrecipe.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public class ImageServiceImpl implements ImageService {
	Logger log = LoggerFactory.getLogger(getClass());
	@Override
	public void saveImageFile(Long recipeId, MultipartFile file) {
		log.debug("Received image file");

	}

}
