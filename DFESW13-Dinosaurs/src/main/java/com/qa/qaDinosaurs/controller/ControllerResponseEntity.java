package com.qa.qaDinosaurs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.qaDinosaurs.model.Dinosaurs;
import com.qa.qaDinosaurs.services.ServiceDB;
import com.qa.qaDinosaurs.services.Services;

@RestController
public class ControllerResponseEntity {

	private ServiceDB services;

	public ControllerResponseEntity(ServiceDB services) {
		super();
		this.services = services;
	}

	// Being used by services now
//	private ArrayList<Dinosaurs> dinoList = new ArrayList<>();
	
	
	// OLD RESPONSE ENTITIES
	// ==============================================================
	// returns a response entity<data it contains>
	// ResponseEntity<String> response = new ResponseEntity<String>("Dinosaur " + dino.getName() + " added!",
	// HttpStatus.CREATED);
	
	// Making a ResponseEntity that contains the data we're sending
	// ResponseEntity<Dinosaurs> response = new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	
	// ResponseEntity<String> response = new ResponseEntity<String>("Dinosaur " + index + " removed!",
	// HttpStatus.ACCEPTED);
	
	// ResponseEntity<String> response = new ResponseEntity<String>("All dinosaurs removed!", HttpStatus.ACCEPTED);
	
	// ResponseEntity<String> response = new ResponseEntity<String>("Dinosaur " + dino.getName() + " updated!", HttpStatus.ACCEPTED);
	// ==============================================================
	
	// Response Entities offer more info when sending a response back
	// Response includes a message AND a status code we can specify AND the data it
	// requested

	@GetMapping("/getDino")
	public ResponseEntity<List<Dinosaurs>> getDino() {

		List<Dinosaurs> result = services.getDino();

		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}

	// Method that creates a POST request that takes in information to add to the DB
	@PostMapping("/createDino")
	public ResponseEntity<String> createDino(@RequestBody Dinosaurs dino) {

		services.createDino(dino);
		String response = "Dinosaur " + dino.getName() + " added!";
		return new ResponseEntity<String>(response, HttpStatus.CREATED);
	}

	// Listens for a /get/<number> (Path variable)
	@GetMapping("/get/{id}") // Path variable
	// Whatever the name of your path variable is, tell Spring to look for it
	public ResponseEntity<Dinosaurs> getById(@PathVariable("id") long id) {

		// Making an object variable called result = the data we're retrieving
		Dinosaurs result = services.getById(id);
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}

	// Listens for /delete/<number> and deletes the object of that index
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteByid(@PathVariable("id") long id) {

		services.deleteById(id);
		String response = "Dinosaur " + id + " removed!";
		return new ResponseEntity<String>(response, HttpStatus.ACCEPTED);
	}

	// Listens for /deleteAll and clears all data from the ArrayList
	@DeleteMapping("/deleteAll")
	public ResponseEntity<String> deleteAll() {

		services.deleteAll();
		String response = "All dinosaurs removed!";
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateById(@PathVariable("id") long id, @RequestBody Dinosaurs dino) {

		services.updateById(id, dino);
		String response = "Dinosaur " + dino.getName() + " updated!";
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

	@PostMapping("/postArray")
	public ResponseEntity<ArrayList<Dinosaurs>> addArray(@RequestBody Dinosaurs[] dinoArray) {

		ArrayList<Dinosaurs> result = services.addArray(dinoArray);
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
}
