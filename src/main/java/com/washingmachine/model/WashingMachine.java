package com.washingmachine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//mark class as an Entity 
@Entity
//defining class name as Table name
@Table
public class WashingMachine {

	// Defining book id as primary key
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
	private Long machineId;
	@Column
	private String model;
	@Column
	private String serial;
	@Column
	private String make;
	@Column
	@Enumerated(EnumType.STRING)
	private WashingMachineStatus status;
	
	public Long getMachineId() {
		return machineId;
	}
	public void setMachineId(Long machineId) {
		this.machineId = machineId;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public WashingMachineStatus getStatus() {
		return status;
	}
	public void setStatus(WashingMachineStatus status) {
		this.status = status;
	}
	
}
