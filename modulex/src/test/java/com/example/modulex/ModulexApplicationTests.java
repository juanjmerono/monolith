package com.example.modulex;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.modulex.service.UsersService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ModulexApplicationTests {

	@MockitoSpyBean
	private UsersService usersService;

	@Autowired
	private MockMvc mockMvc;

	/*@Autowired
	private com.fasterxml.jackson.databind.ObjectMapper objectMapper;*/

	@Test
	void userGetShouldReturn() throws Exception {
		//when(usersService.getUserById("12")).thenReturn("{");
		mockMvc.perform(get("/user/12"))
			.andExpect(status().isOk())
			.andExpect(content().string("{ userId: 12 }"));
		verify(usersService).getUserById("12");
	}

	@Test
	void userPostShouldLaunchEvent() throws Exception {
		//when(usersService.getUserById("12")).thenReturn("{");
		mockMvc.perform(post("/user/")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"name\": \"John Doe\" }"))
			.andExpect(status().isCreated())
			.andExpect(content().string("{ \"name\": \"John Doe\" }"));
		verify(usersService).newUser("{ \"name\": \"John Doe\" }");
	}

}
