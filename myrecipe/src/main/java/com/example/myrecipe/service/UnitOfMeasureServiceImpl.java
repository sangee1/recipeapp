package com.example.myrecipe.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.example.myrecipe.command.UnitOfMeasureCommand;
import com.example.myrecipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.example.myrecipe.domain.UnitOfMeasure;
import com.example.myrecipe.repositories.UnitOfMeasureRepository;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {
	
	UnitOfMeasureRepository uomRepo;
	UnitOfMeasureToUnitOfMeasureCommand uomCommand;
	
	
	public UnitOfMeasureServiceImpl(UnitOfMeasureRepository uomRepo, UnitOfMeasureToUnitOfMeasureCommand uomCommand) {
		super();
		this.uomRepo = uomRepo;
		this.uomCommand = uomCommand;
	}



	@Override
	public Set<UnitOfMeasureCommand> listAllUoms() {
		 //Set<UnitOfMeasure> result = (Set<UnitOfMeasure>) uomRepo.findAll();
		 //return result.stream().map(uom -> uomCommand.convert(uom)).collect(Collectors.toSet());
		return StreamSupport.stream(uomRepo.findAll().spliterator(),false).map(uomCommand::convert).collect(Collectors.toSet());
	}

}
