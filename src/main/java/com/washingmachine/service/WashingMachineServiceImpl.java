package com.washingmachine.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.washingmachine.model.WashingMachine;
import com.washingmachine.repository.WashingMachineRepository;

@Service
public class WashingMachineServiceImpl {
	
	@Autowired
	WashingMachineRepository washingMachineRepository;

	public List<WashingMachine> getAllWashingMachine() {
		
		List<WashingMachine> washingMachines = new ArrayList<WashingMachine>();
		washingMachineRepository.findAll().forEach(machines -> washingMachines.add(machines));
		return washingMachines;
	}

	public WashingMachine getWashingMachineById(int id) {
		
		return washingMachineRepository.findById(id).get();
	}
	
	public void save(WashingMachine washingMachine) {
		washingMachineRepository.save(washingMachine);
	}
	
	public void delete(int id) {
		washingMachineRepository.deleteById(id);
	}
}
