package com.qa.qaDinosaurs.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.qaDinosaurs.model.Dinosaurs;


// Telling Spring this class is a controller, meaning it take in HTTP requests
//@RestController
public class Controller {
	
	
	private ArrayList<Dinosaurs> dinoList = new ArrayList<>();
	
	// Method listens for /getDino
	// Returns our ArrayList
	@GetMapping("/getDino")
	public ArrayList<Dinosaurs> getDino() {
		for(Dinosaurs list : dinoList) {
			System.out.println(list);
		}
	
		return dinoList;
	}
	
	// Method to post data
	@PostMapping("/createSetDino")
	public boolean createSetDino() {
		dinoList.add(new Dinosaurs("Tyrannosaurus", "Theropod", 12, 40, true, false, false));
		return true;
	}
	
	// Method that creates a POST request that takes in information to add to the DB
	@PostMapping("/createDino")
	public boolean createDino(@RequestBody Dinosaurs dino) {
		dinoList.add(dino);
		return true;	
	}
	
	// Listens for a /get/<number> (Path variable)
	@GetMapping("/get/{index}")
	// Whatever the name of your path variable is, tell Spring to look for it
	public Dinosaurs getByIndex(@PathVariable("index") int index) {
		return dinoList.get(index);		
	}
	
	// Listens for /delete/<number> and deletes the object of that index
	@DeleteMapping("/delete/{index}")
	public boolean deleteByIndex(@PathVariable("index") int index) {
		dinoList.remove(index);
		return true;
	}
	
	// Listens for /deleteAll and clears all data from the ArrayList
	@DeleteMapping("/deleteAll")
	public boolean deleteAll() {
		dinoList.clear();
		return true;
	}
	
	// Update two parameters, id/index and the data we're replacing with
	// localhost:8080/update/1 AND we need to add a request body
	@PutMapping("/update/{index}")
	public boolean update(@PathVariable("index") int index, @RequestBody Dinosaurs dino){
		dinoList.set(index, dino);
		return true;
	}
	
	// Stretch goals:
	// Add An array list of objects
	@PostMapping("/postArray")
	public boolean addArrayDinosaur(@RequestBody Dinosaurs[] dinoArray) {
		for(Dinosaurs list : dinoArray) {
			dinoList.add(list);
		}
		return true;
	}
	

}
