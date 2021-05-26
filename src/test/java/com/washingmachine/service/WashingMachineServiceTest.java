package com.washingmachine.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
		washingMachine.setMachineId(101);
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

}
