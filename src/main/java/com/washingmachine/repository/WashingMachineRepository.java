package com.washingmachine.repository;

import org.springframework.data.repository.CrudRepository;

import com.washingmachine.model.WashingMachine;

public interface WashingMachineRepository extends CrudRepository<WashingMachine, Integer>{

}
