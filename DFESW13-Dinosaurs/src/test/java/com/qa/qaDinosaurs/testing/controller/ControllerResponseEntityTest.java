package com.qa.qaDinosaurs.testing.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
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
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Allows us to use @BeforeAll in a NON static way
@Sql(scripts = {"classpath:test-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("dev") // When running tests it will use the dev profiles
public class ControllerResponseEntityTest {
	
	// Plugs our MockMvc into the spring app directly
	@Autowired
	private MockMvc mvc; // Object that performs a Mock request (like postman) imports in springFramework
	
	@Autowired // plugs into our spring app
	private ObjectMapper mapper; // Converts Java Objects to JSON Strings
	
	// Test Object
	Dinosaurs testDino = new Dinosaurs("testTyrannosaurus", "testTheropod", 1, 1, true, false, false);
	Dinosaurs testDinoID = new Dinosaurs(1l, "Tyrannosaurus", "Theropod", 12, 31, true, false, false);
	Dinosaurs testDinoID2 = new Dinosaurs(2l, "Velociraptor", "Theropod", 5, 6, true, false, false);
	
//	// This will run before all of the other tests once
//	@BeforeAll
//	public void setup() throws Exception {
//		// Before any tests run, I want there to be at least one test object in the database
//		String bookingJson = mapper.writeValueAsString(testDino);
//		RequestBuilder req = post("/createDino").contentType(MediaType.APPLICATION_JSON).content(bookingJson);
//		mvc.perform(req);
//	}
	
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
	
	@Test
	public void testGetAll() throws Exception {
		
		// Make a list of the testData to check the response
		List<Dinosaurs> allDinos = List.of(testDinoID, testDinoID2);
		
		// Covert my list to JSON
		String allDinosJson = mapper.writeValueAsString(allDinos);
		
		// Our request doesn't take in any other params
		RequestBuilder req = get("/getDino");
		
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(allDinosJson);
		
		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	public void testGetId() throws Exception {
		
		// Arrange
//		Dinosaurs testDinoId = testDino; // Making a new dino object equal to my testDino object
//		testDinoId.setId(8l); // Setting the id of our object to be 8, identical to how it will be when we get it from the DB
		
		String testDinoIdJson = mapper.writeValueAsString(testDinoID2);
		
		// Act
		// Passing in the path variable 1 but is added as part of the string
		RequestBuilder req = get("/getId/2");
		
		ResultMatcher checkStatus = status().isAccepted(); // is the response status code accepted?
		ResultMatcher checkBody = content().json(testDinoIdJson); // Is the response content a Json string of our object?
		
		// Assert
		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}


	@Test
	public void testDeleteById() throws Exception {
		
		// Arrange
		
		// Act
		RequestBuilder req = delete("/delete/2");
		
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().string("Dinosaur 2 removed!");
		
		// Assert
		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);	
	}
	
	@Test
	public void testDeleteAll() throws Exception {
		
		// Arrange
		
		
		// Act
		RequestBuilder req = delete("/deleteAll");
		
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().string("All dinosaurs removed!");
		
		// Assert
		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	public void testUpdateById() throws Exception {
		
		// Arrange
		// The updated object we'll be passing in
		Dinosaurs updatedDino = new Dinosaurs("T-Jeff", "Theropod", 1000, 300, true, true, true);
		
		String updatedDinoJson = mapper.writeValueAsString(updatedDino);
	
		
		// Act
		RequestBuilder req = put("/update/2").contentType(MediaType.APPLICATION_JSON).content(updatedDinoJson);
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().string("Dinosaur T-Jeff updated!");
		
		// Assert
		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	public void testGetByType() throws Exception {
		
		List<Dinosaurs> allDinos = List.of(testDinoID, testDinoID2);
		String allDinosJson = mapper.writeValueAsString(allDinos);
		
		RequestBuilder req = get("/getType/Theropod");
		
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(allDinosJson);
		
		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);	
		
	}
	
	@Test
	public void testGetByHeight() throws Exception {
		
		List<Dinosaurs> allDinos = List.of(testDinoID, testDinoID2);
		String allDinosJson = mapper.writeValueAsString(allDinos);
		
		RequestBuilder req = get("/getHeight/2");
		
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(allDinosJson);
		
		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);	
		
	}
	
	@Test
	public void testGetByHeightDesc() throws Exception {
		
		List<Dinosaurs> allDinos = List.of(testDinoID, testDinoID2);
		String allDinosJson = mapper.writeValueAsString(allDinos);
		
		RequestBuilder req = get("/getTypeHeightDesc/Theropod");
		
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(allDinosJson);
		
		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);	
		
	}


}
