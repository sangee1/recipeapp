package com.example.myrecipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.myrecipe.command.UnitOfMeasureCommand;
import com.example.myrecipe.domain.UnitOfMeasure;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

	@Override
	public UnitOfMeasureCommand convert(UnitOfMeasure source) {
		if(source == null)
			return null;
		UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
		uomCommand.setDescription(source.getDescription());
		uomCommand.setId(source.getId());
		return uomCommand;
	}

}
