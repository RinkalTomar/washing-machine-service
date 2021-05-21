package com.washingmachine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
