package com.automacao.acess_control.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automacao.acess_control.entities.StateData;
import com.automacao.acess_control.repositories.StateDataRepository;

@Service
public class StateDataServices {

	@Autowired
	private StateDataRepository repository;
	
	public StateData findById(Long id) {
		Optional<StateData> obj = repository.findById(id);
		return obj.get();
	}
}
