package com.qa.qaDinosaurs.testing.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.qaDinosaurs.model.Dinosaurs;

// Telling Spring this is a test class
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // Our tests will run on a random port, if the port is already in use choose a new one
// Set up our Mock MVC (replacing postman) to work with the project
@AutoConfigureMockMvc
public class ControllerResponseEntityTest {
	
	// Plugs our MockMvc into the spring app directly
	@Autowired
	private MockMvc mvc; // Object that performs a Mock request (like postman) imports in springFramework
	
	@Autowired // plugs into our spring app
	private ObjectMapper mapper; // Converts Java Objects to JSON Strings
	
	// Test Object
	Dinosaurs testDino = new Dinosaurs("testTyrannosaurus", "testTheropod", 12, 31, true, false, false);
	
	@Test
	public void testCreate() throws Exception {
		
		// Arrange
		// Converting our Test Object into a JSON string called bookingJson
		String bookingJson = mapper.writeValueAsString(testDino);
		
		// Import RequestBuilder from Spring framework
		// = Request Type (get, post, put)
		// ("/request path")
		// content() the body of data you are sending
		RequestBuilder req = post("/createDino").contentType(MediaType.APPLICATION_JSON).content(bookingJson);
		// Equivalent to writing our request in postman BEFORE clicking send
		
		// Act
		// Making a ResultMatcher object (through mvc) its either true or false
		// this depends on the status().isCreated()
		ResultMatcher checkStatus = status().isCreated(); // Is the status of our request created?
		ResultMatcher checkBody = content().string("Dinosaur testTyrannosaurus added!");
		
		// Assert
		
		// tell our mvc (postman mock) to run the request (click send)
		// run the request and checkStatus SHOULD be true (status code is correct) 
		// AND checkBody SHOULD Be true (response body is correct) 
		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);

	}

}
