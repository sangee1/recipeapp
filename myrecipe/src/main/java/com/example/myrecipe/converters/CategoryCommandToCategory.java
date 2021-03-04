package com.example.myrecipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.myrecipe.command.CategoryCommand;
import com.example.myrecipe.domain.Category;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category>{

        public Category convert(CategoryCommand source) {
        if (source == null) {
            return null;
        }

        final Category category = new Category();
        category.setId(source.getId());
        category.setDescription(source.getDescription());
        return category;
    }
}
