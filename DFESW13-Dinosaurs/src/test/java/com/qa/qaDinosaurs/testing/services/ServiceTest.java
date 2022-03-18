package com.qa.qaDinosaurs.testing.services;


import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.qaDinosaurs.model.Dinosaurs;
import com.qa.qaDinosaurs.repo.Repo;
import com.qa.qaDinosaurs.services.ServiceDB;

@SpringBootTest
public class ServiceTest {
	
	// Adding the class we are Mocking (Repo)
	// Creating a Mock bean object of our Repo, for us to plug into our service
	@MockBean
	private Repo repo;
	
	// Autowired - Spring automatically plugs in the beans into the object
	@Autowired
	private ServiceDB service; // Create a service object, plug in the mock repo automatically

	// Test Objects I can pass into methods and have my repo return them
	Dinosaurs dino1 = new Dinosaurs("Tyrannosaurus", "Theropod", 12, 31, true, false, false);
	Dinosaurs dino2 = new Dinosaurs("Velociraptor", "Theropod", 5, 6, true, false, false);
	// Objects with ID for when we return them from our repo
	Dinosaurs dino1ID = new Dinosaurs(1l, "Tyrannosaurus", "Theropod", 12, 31, true, false, false);
	Dinosaurs dino2ID = new Dinosaurs(2l, "Velociraptor", "Theropod", 5, 6, true, false, false);
	
	@Test
	public void testCreate () {
		
		// Arrange
		// When we tell our repo to save data, it should return the object with ID
		// When our Mock Repo runs the save method, take in dino1, it should return dino1ID
		Mockito.when(repo.save(dino1)).thenReturn(dino1ID);
		
		// Act
		// Our boolean result equals to what our createDino returns
		Dinosaurs result = service.createDino(dino1);
		
		// Assert
		Assertions.assertEquals(dino1ID, result);
		
		// Behaviour Testing
		// Verifying if the mock Repo was triggered (1) times, and what methods we're checking
		Mockito.verify(repo, Mockito.times(1)).save(dino1);
	}
	
	@Test
	public void testGetById() {
		
		// Arrange
		// Returns an Optional of an object, an object wrapped in up a 'box' (so we don't get null pointer exception)
		Mockito.when(repo.findById(1l)).thenReturn(Optional.of(dino1ID));
		
		// Act
		Dinosaurs result = service.getById(1l);
		
		// Assert
		Assertions.assertEquals(dino1ID, result);
		
		// Our mocked object NEVER runs the .count()
		Mockito.verify(repo, Mockito.never()).count();
	}
}


