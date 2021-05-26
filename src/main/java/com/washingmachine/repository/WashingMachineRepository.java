package com.washingmachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.washingmachine.model.WashingMachine;

public interface WashingMachineRepository extends JpaRepository<WashingMachine, Long>{

	@Query("select w from WashingMachine w where w.serial=:serial")
    WashingMachine findBySerial(String serial);
}
