package com.washingmachine.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.washingmachine.exception.WasinghMachineNotFoundException;
import com.washingmachine.model.WashingMachine;
import com.washingmachine.model.WashingMachineStatus;
import com.washingmachine.service.WashingMachineService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = "management.server.port=-1")
@AutoConfigureMockMvc
public class WashingMachineControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	private WashingMachineService washingMachineService;

	@Test
	public void getWashingMachineById_whenFound_test() throws Exception {

		String expectJson = "{\"machineId\":101,\"model\":\"LG\",\"serial\":\"EW667QPNDPGX\",\"make\":\"LG-7P7020NGAY\",\"status\":\"START\"}";
		WashingMachine washingMachine = new WashingMachine();
		washingMachine.setMachineId(101L);
		washingMachine.setMake("LG-7P7020NGAY");
		washingMachine.setModel("LG");
		washingMachine.setSerial("EW667QPNDPGX");
		washingMachine.setStatus(WashingMachineStatus.START);

		when(washingMachineService.getWashingMachineById(any())).thenReturn(washingMachine);

		this.mockMvc.perform(get("/washingmachines/101").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(content().string(expectJson));
	}

	@Test
	public void getWashingMachineById_whenNotFound_test() throws Exception {

		String expectJson = "{\"message\":\"This Washing Machine Not Found for this Id =1019\",\"details\":\"uri=/washingmachines/112\"}";

		when(washingMachineService.getWashingMachineById(any()))
				.thenThrow(new WasinghMachineNotFoundException("This Washing Machine Not Found for this Id =1019"));

		this.mockMvc.perform(get("/washingmachines/112").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isNotFound()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().string(expectJson));
	}

	@Test
	public void getAllWashingMachine_whenFound_test() throws Exception {

		WashingMachine washingMachine = new WashingMachine();
		washingMachine.setMachineId(101L);
		washingMachine.setMake("LG-7P7020NGAY");
		washingMachine.setModel("LG");
		washingMachine.setSerial("EW667QPNDPGX");
		washingMachine.setStatus(WashingMachineStatus.START);
		List<WashingMachine> listOfWahingMachine = new ArrayList<>();
		listOfWahingMachine.add(washingMachine);
		when(washingMachineService.getAllWashingMachine()).thenReturn(listOfWahingMachine);

		this.mockMvc.perform(get("/washingmachines").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(content()
						.string(objectMapper.writeValueAsString(listOfWahingMachine)));
	}
	
	@Test
	public void washingMachine_Create_test() throws Exception {
		WashingMachine washingMachine = new WashingMachine();
		washingMachine.setMachineId(101L);
		washingMachine.setMake("LG-7P7020NGAY");
		washingMachine.setModel("LG");
		washingMachine.setSerial("EW667QPNDPGX");
		washingMachine.setStatus(WashingMachineStatus.START);
		
		this.mockMvc.perform(post("/washingmachines")
				.content(objectMapper.writeValueAsString(washingMachine))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void washingMachine_NotCreate_test() throws Exception {
		WashingMachine washingMachine = new WashingMachine();
		washingMachine.setMachineId(101L);
		washingMachine.setMake("LG-7P7020NGAY");
		washingMachine.setModel("LG");
		washingMachine.setSerial("EW667QPNDPGX");
		washingMachine.setStatus(WashingMachineStatus.DRYING);
		
		Mockito.doNothing()
        .when(washingMachineService).save(ArgumentMatchers.any(WashingMachine.class));
		
		doThrow(new WasinghMachineNotFoundException())
        .when(washingMachineService).save(ArgumentMatchers.any(WashingMachine.class));
		
		
		this.mockMvc.perform(post("/washingmachines")
				.content(objectMapper.writeValueAsString(washingMachine))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void removeWashingMachineById_whenFound_test() throws Exception {

		this.mockMvc.perform(delete("/washingmachines/101").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void removeWashingMachineById_whenNotFound_test() throws Exception {

		doThrow(new WasinghMachineNotFoundException())
        .when(washingMachineService).delete(ArgumentMatchers.any());
		
		this.mockMvc.perform(delete("/washingmachines/112").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isNotFound());
	}

	@Test
	public void washingMachine_Update_test() throws Exception {
		WashingMachine washingMachine = new WashingMachine();
		washingMachine.setMachineId(101L);
		washingMachine.setMake("LG-7P7020NGAY");
		washingMachine.setModel("LG");
		washingMachine.setSerial("EW667QPNDPGX");
		washingMachine.setStatus(WashingMachineStatus.START);
		
		this.mockMvc.perform(put("/washingmachines/101")
				.content(objectMapper.writeValueAsString(washingMachine))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
