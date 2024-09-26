package com.automacao.access_control.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.automacao.access_control.entities.StateData;
import com.automacao.access_control.repositories.StateDataRepository;

@Controller
@RequestMapping(value = "/statedata")
public class StateDataResource {
	
	@Autowired
	private StateDataRepository repository;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<StateData> findById(@PathVariable Long id){
		StateData obj = repository.findById(id).orElse(null);
		if(obj != null) {
			return ResponseEntity.ok().body(obj);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/")
	public ResponseEntity<Void> saveStateData(@RequestBody StateData data) {
		StateData obj = new StateData(data);
		repository.save(obj);
		return ResponseEntity.ok().build();
	}
}
