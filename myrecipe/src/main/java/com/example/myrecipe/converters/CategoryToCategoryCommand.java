package com.example.myrecipe.converters;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.myrecipe.command.CategoryCommand;
import com.example.myrecipe.domain.Category;


@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    
    public CategoryCommand convert(Category source) {
        if (source == null) {
            return null;
        }

        final CategoryCommand categoryCommand = new CategoryCommand();

        categoryCommand.setId(source.getId());
        categoryCommand.setDescription(source.getDescription());

        return categoryCommand;
    }
}