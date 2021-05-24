package com.washingmachine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.washingmachine.model.WashingMachine;
import com.washingmachine.service.WashingMachineServiceImpl;

@RestController
public class WashingMachineController {

	@Autowired
	WashingMachineServiceImpl washingMachineService;

	@GetMapping("/washingmachine")
	private List<WashingMachine> getAllWashingMachine() {
		return washingMachineService.getAllWashingMachine();
	}
	
	@GetMapping("/washingmachine/{id}")
	private WashingMachine getWashingMachine(@PathVariable("id") int id ) {
		return washingMachineService.getWashingMachineById(id);
	}
	
	@PostMapping("/washingmachine")
	private void addWashingMachine(@RequestBody WashingMachine washingMachine) {
		washingMachineService.save(washingMachine);
	}
	
	@DeleteMapping("/washingmachine/{id}")
	private void deleteWashingMachine(@PathVariable("id") int id) {
		washingMachineService.delete(id);
	}
	
}
