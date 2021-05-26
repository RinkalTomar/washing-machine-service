package com.washingmachine.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.washingmachine.exception.WasinghMachineNotFoundException;
import com.washingmachine.service.WashingMachineService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = "management.server.port=-1")
@AutoConfigureMockMvc
public class WashingMachineControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private WashingMachineService washingMachineService;
	
	@Test
	public void getWashingMachineById_whenFound_test() throws Exception {
		
		String expectJson = "{\"machineId\":101,\"model\":\"LG\",\"serial\":\"EW667QPNDPGX\",\"make\":\"LG 7 (P7020NGAY)\",\"status\":\"START\"}";
		
		this.mockMvc.perform(get("/washingmachines/101")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().string(expectJson));
	}
	
	@Test
	public void getWashingMachineById_whenNotFound_test() throws Exception {
		
		String expectJson = "{\"message\":\"This Washing Machine Not Found for this Id =1019\",\"details\":\"uri=/washingmachines/112\"}";
		
		when(washingMachineService.getWashingMachineById(any())).thenThrow(new WasinghMachineNotFoundException("This Washing Machine Not Found for this Id =1019"));
		
		this.mockMvc.perform(get("/washingmachines/112")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isNotFound())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().string(expectJson));
	}
	
}
