package com.washingmachine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.washingmachine.exception.WasinghMachineAlreadyExistException;
import com.washingmachine.model.WashingMachine;
import com.washingmachine.service.WashingMachineService;

@RestController
public class WashingMachineController {

	private WashingMachineService washingMachineService;
	
	@Autowired
	public WashingMachineController(WashingMachineService washingMachineService) {
		this.washingMachineService = washingMachineService;
	}

	@GetMapping("/washingmachines")
	private List<WashingMachine> getAllWashingMachine() {
		return washingMachineService.getAllWashingMachine();
	}
	
	@GetMapping("/washingmachines/{id}")
	private WashingMachine getWashingMachine(@PathVariable("id") Long id ) {
		return washingMachineService.getWashingMachineById(id);
	}
	
	@PostMapping("/washingmachines")
	@ResponseStatus(HttpStatus.CREATED)
	private void addWashingMachine(@RequestBody WashingMachine washingMachine)throws WasinghMachineAlreadyExistException  {
		washingMachineService.save(washingMachine);
	}
	
	@DeleteMapping("/washingmachines/{id}")
	private void deleteWashingMachine(@PathVariable("id") Long id) {
		washingMachineService.delete(id);
	}
	
	@PutMapping("/washingmachines/{id}")
	private void updateWashingMachine(@RequestBody WashingMachine washingMachine,@PathVariable("id") Long washingMachineId) {
		washingMachineService.update(washingMachine,washingMachineId);
	}
	
}
