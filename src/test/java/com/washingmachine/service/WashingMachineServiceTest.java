package com.washingmachine.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.washingmachine.exception.WasinghMachineAlreadyExistException;
import com.washingmachine.exception.WasinghMachineNotFoundException;
import com.washingmachine.model.WashingMachine;
import com.washingmachine.model.WashingMachineStatus;
import com.washingmachine.repository.WashingMachineRepository;

@ExtendWith(SpringExtension.class)
public class WashingMachineServiceTest {

	@Mock
	private WashingMachineRepository washingMachineRepository;
	
	private WashingMachineService washingMachineService;
	
	@BeforeEach
	void setUp() {
		initMocks(this);
		washingMachineService = new WashingMachineService(washingMachineRepository);
	}
	
	@Test
	public void getWashingMachineById_whenFound() {
		WashingMachine washingMachine = new WashingMachine();
		washingMachine.setMachineId(101L);
		washingMachine.setMake("LG-3434JKJI");
		washingMachine.setModel("LG-MYK898");
		washingMachine.setSerial("12312312");
		washingMachine.setStatus(WashingMachineStatus.START);
		
		when(washingMachineRepository.findById(any())).thenReturn(Optional.of(washingMachine));
		assertNotNull(washingMachineService.getWashingMachineById(101L));
	}
	
	@Test
	public void getWashingMachineById_whenNotFound() {
		
		when(washingMachineRepository.findById(any())).thenReturn(Optional.empty());
		Exception exception = assertThrows(WasinghMachineNotFoundException.class, () -> {
			washingMachineService.getWashingMachineById(101L);
	    });

	    String expectedMessage = "This Washing Machine Not Found for this Id =101";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void removeWashingMachineById_WhenNotFound() {
		
		when(washingMachineRepository.findById(any())).thenReturn(Optional.empty());
		Exception exception = assertThrows(WasinghMachineNotFoundException.class, () -> {
			washingMachineService.delete(101L);
	    });

	    String expectedMessage = "This Washing Machine Not Found for this Id =101";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void removeWashingMachineById_WhenFound() {
		
		WashingMachine washingMachine = new WashingMachine();
		washingMachine.setMachineId(101L);
		washingMachine.setMake("LG-3434JKJI");
		washingMachine.setModel("LG-MYK898");
		washingMachine.setSerial("12312312");
		washingMachine.setStatus(WashingMachineStatus.START);
		
		when(washingMachineRepository.findById(any())).thenReturn(Optional.of(washingMachine));
		
		Mockito.doNothing()
        .when(washingMachineRepository).delete(ArgumentMatchers.any(WashingMachine.class));
	}
	
	
	@Test
	public void updateWashingMachineById_whenFound() {
		WashingMachine washingMachineen = new WashingMachine();
		washingMachineen.setMachineId(103L);
		washingMachineen.setMake("LG-3434JKJI");
		washingMachineen.setModel("LG-MYK898");
		washingMachineen.setSerial("12312312");
		washingMachineen.setStatus(WashingMachineStatus.STOP);
		//washingMachineService.update(washingMachineen, 102L);
	}
	
	@Test
	public void updateWashingMachineById_StatusWashing() {
		WashingMachine washingMachine = new WashingMachine();
		washingMachine.setMachineId(101L);
		washingMachine.setMake("LG-3434JKJI");
		washingMachine.setModel("LG-MYK898");
		washingMachine.setSerial("12312312");
		washingMachine.setStatus(WashingMachineStatus.WASHING);
		
		WashingMachine washingMachine2 = new WashingMachine();
		washingMachine2.setMachineId(101L);
		washingMachine2.setMake("LG-3434JKJI");
		washingMachine2.setModel("LG-MYK898");
		washingMachine2.setSerial("12312312");
		washingMachine2.setStatus(WashingMachineStatus.STOP);
		
		when(washingMachineRepository.findById(any())).thenReturn(Optional.of(washingMachine2));
		
		when(washingMachineRepository.save(any())).thenReturn(Optional.of(washingMachine));
		
		Exception exception = assertThrows(WasinghMachineNotFoundException.class, () -> {
			washingMachineService.update(washingMachine, 101L);
	    });

	    String expectedMessage = "You can't wash because your machine is not start";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
		
	}
	
	@Test
	public void updateWashingMachineById_StatusDrying() {
		WashingMachine washingMachine = new WashingMachine();
		washingMachine.setMachineId(101L);
		washingMachine.setMake("LG-3434JKJI");
		washingMachine.setModel("LG-MYK898");
		washingMachine.setSerial("12312312");
		washingMachine.setStatus(WashingMachineStatus.DRYING);
		
		WashingMachine washingMachine2 = new WashingMachine();
		washingMachine2.setMachineId(101L);
		washingMachine2.setMake("LG-3434JKJI");
		washingMachine2.setModel("LG-MYK898");
		washingMachine2.setSerial("12312312");
		washingMachine2.setStatus(WashingMachineStatus.STOP);
		
		when(washingMachineRepository.findById(any())).thenReturn(Optional.of(washingMachine2));
		
		when(washingMachineRepository.save(any())).thenReturn(Optional.of(washingMachine));
		
		Exception exception = assertThrows(WasinghMachineNotFoundException.class, () -> {
			washingMachineService.update(washingMachine, 101L);
	    });

	    String expectedMessage = "You can't wash because your machine is not start";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
		
	}
	
	@Test
	public void createWashingMachine_WhenNotFound() {
		WashingMachine washingMachine = new WashingMachine();
		washingMachine.setMachineId(101L);
		washingMachine.setMake("LG-3434JKJI");
		washingMachine.setModel("LG-MYK898");
		washingMachine.setSerial("12312312");
		washingMachine.setStatus(WashingMachineStatus.DRYING);
		
		WashingMachine washingMachine2 = new WashingMachine();
		washingMachine2.setMachineId(101L);
		washingMachine2.setMake("LG-3434JKJI");
		washingMachine2.setModel("LG-MYK898");
		washingMachine2.setSerial("12312312");
		washingMachine2.setStatus(WashingMachineStatus.DRYING);
		
		when(washingMachineRepository.findBySerial("12312312")).thenReturn(washingMachine);
		
		Exception exception = assertThrows(WasinghMachineAlreadyExistException.class, () -> {
			washingMachineService.save(washingMachine2);
	    });

	    String expectedMessage = "Washing Machine alredy exist for serial Id =12312312";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void createWashingMachine_WhenFound() {
		
		WashingMachine washingMachine = new WashingMachine();
		washingMachine.setMachineId(101L);
		washingMachine.setMake("LG-3434JKJI");
		washingMachine.setModel("LG-MYK898");
		washingMachine.setSerial("12312312");
		washingMachine.setStatus(WashingMachineStatus.START);
		
		when(washingMachineRepository.findById(any())).thenReturn(Optional.of(washingMachine));
		
		when(washingMachineRepository.save(any())).thenReturn(Optional.of(washingMachine));
	}
	
	@Test
	public void getListOfWashingMachine_WhenFound() {
		
		WashingMachine washingMachine = new WashingMachine();
		washingMachine.setMachineId(101L);
		washingMachine.setMake("LG-3434JKJI");
		washingMachine.setModel("LG-MYK898");
		washingMachine.setSerial("12312312");
		washingMachine.setStatus(WashingMachineStatus.START);
		List<WashingMachine> washingMachineList = new ArrayList<>();
		washingMachineList.add(washingMachine);
		
		when(washingMachineRepository.findAll()).thenReturn(washingMachineList);
		assertNotNull(washingMachineService.getAllWashingMachine());
		
	}

}
