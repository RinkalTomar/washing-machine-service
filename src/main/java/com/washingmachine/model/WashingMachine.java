package com.washingmachine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

//mark class as an Entity 
@Entity
//defining class name as Table name
@Table
public class WashingMachine {

	// Defining book id as primary key
	@Id
	@Column
	private int machineId;
	@Column
	private String model;
	@Column
	private String serial;
	@Column
	private String make;
	@Column
	@Enumerated(EnumType.STRING)
	private MachineStatus status;
	
	public int getMachineId() {
		return machineId;
	}
	public void setMachineId(int machineId) {
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
	public MachineStatus getStatus() {
		return status;
	}
	public void setStatus(MachineStatus status) {
		this.status = status;
	}
	
	
	
	

}
