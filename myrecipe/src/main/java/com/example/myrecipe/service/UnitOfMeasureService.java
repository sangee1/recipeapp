package com.example.myrecipe.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.myrecipe.command.UnitOfMeasureCommand;
import com.example.myrecipe.domain.UnitOfMeasure;

@Service
public interface UnitOfMeasureService {
	public Set<UnitOfMeasureCommand> listAllUoms();

}
