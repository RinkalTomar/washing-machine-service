package com.washingmachine.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.washingmachine.exception.WasinghMachineAlreadyExistException;
import com.washingmachine.exception.WasinghMachineNotFoundException;
import com.washingmachine.model.WashingMachine;
import com.washingmachine.model.WashingMachineStatus;
import com.washingmachine.repository.WashingMachineRepository;

@Service
public class WashingMachineService {
	
	private WashingMachineRepository washingMachineRepository;
	
	@Autowired
	public WashingMachineService(WashingMachineRepository washingMachineRepository) {
		this.washingMachineRepository = washingMachineRepository;
	}

	public List<WashingMachine> getAllWashingMachine() {
		
		List<WashingMachine> washingMachines = new ArrayList<WashingMachine>();
		washingMachineRepository.findAll().forEach(machines -> washingMachines.add(machines));
		return washingMachines;
	}

	public WashingMachine getWashingMachineById(Long id) {

		Optional<WashingMachine> waMachine = washingMachineRepository.findById(id);
		if (!waMachine.isPresent()) {
			throw new WasinghMachineNotFoundException("This Washing Machine Not Found for this Id =" + id);
		}
		return waMachine.get();

	}
	
	public void save(WashingMachine washingMachine) throws WasinghMachineAlreadyExistException {
		//Check if WashingMachine with same serial number already exist
		WashingMachine waMachine = washingMachineRepository.findBySerial(washingMachine.getSerial());
		if(waMachine!=null) {
			throw new WasinghMachineAlreadyExistException("Washing Machine alredy exist for serial Id ="+waMachine.getSerial());
		}
		washingMachineRepository.save(washingMachine);
	}
	
	public void delete(Long id) {
		Optional<WashingMachine> waMachine = washingMachineRepository.findById(id);
		if (!waMachine.isPresent()) {
			throw new WasinghMachineNotFoundException("This Washing Machine Not Found for this Id =" + id);
		}
		washingMachineRepository.deleteById(id);
	}
	
	public void update(WashingMachine washingMachine,Long washingMachineId) {
		
		WashingMachine washingMachine2 = washingMachineRepository.findById(washingMachineId).get();
		
		if(washingMachine.getStatus().equals(WashingMachineStatus.WASHING)) {
			if(washingMachine2.getStatus().equals(WashingMachineStatus.STOP)) {
				throw new WasinghMachineNotFoundException("You can't wash because your machine is not start");
			}
		}else if(washingMachine.getStatus().equals(WashingMachineStatus.DRYING)) {
			if(washingMachine2.getStatus().equals(WashingMachineStatus.STOP)) {
				throw new WasinghMachineNotFoundException("You can't wash because your machine is not start");
			}
		}
		washingMachineRepository.save(washingMachine);
	}
}


