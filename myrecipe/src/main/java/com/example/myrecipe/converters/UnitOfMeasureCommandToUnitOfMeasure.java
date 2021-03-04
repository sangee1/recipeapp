package com.example.myrecipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.myrecipe.command.UnitOfMeasureCommand;
import com.example.myrecipe.domain.UnitOfMeasure;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure
		implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

	@Override
	public UnitOfMeasure convert(UnitOfMeasureCommand source) {
		if(source == null)
		  return null;
		
		final UnitOfMeasure uom = new UnitOfMeasure();
		uom.setDescription(source.getDescription());
		uom.setId(source.getId());
		return uom;
	}

}
