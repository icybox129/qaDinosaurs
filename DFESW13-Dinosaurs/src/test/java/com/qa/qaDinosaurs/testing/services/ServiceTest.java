package com.qa.qaDinosaurs.testing.services;


import java.util.List;
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
	public void testGetDino() {
		
		List<Dinosaurs> allDinos = List.of(dino1ID, dino2ID);
		
		Mockito.when(repo.findAll()).thenReturn(allDinos);
		
		List<Dinosaurs> result = service.getDino();
		
		Assertions.assertEquals(allDinos, result);
		
		Mockito.verify(repo, Mockito.times(1)).findAll();
	}
	
	@Test
	public void testCreateDino () {
		
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
	
	@Test
	public void testDeleteById() {
		
		repo.deleteById(1l);
		
		boolean result = service.deleteById(1l);
		
		Assertions.assertTrue(result);
	}
	
	@Test
	public void testDeleteAll() {
		
		repo.deleteAll();
		
		boolean result = service.deleteAll();
		
		Assertions.assertTrue(result);
	}
	
	@Test
	public void testUpdateById() {
		
		Dinosaurs updatedDino = new Dinosaurs("T-Jeff", "Theropod", 1000, 300, true, true, true);
		
		Mockito.when(repo.findById(1l)).thenReturn(Optional.of(dino1ID));
		Mockito.when(repo.save(dino1ID)).thenReturn(updatedDino);
		
		boolean result = service.updateById(1l, updatedDino);
		
		Assertions.assertTrue(result);
	}
	
	@Test
	public void testGetByType() {
		
		List<Dinosaurs> allDinos = List.of(dino1ID, dino2ID);
		
		Mockito.when(repo.findByType("Theropod")).thenReturn(allDinos);
		
		List<Dinosaurs> result = service.getByType("Theropod");
		
		Assertions.assertEquals(result, allDinos);
	}
	
	@Test
	public void testGetByHeight() {
		
		List<Dinosaurs> allDinos = List.of(dino1ID, dino2ID);
		
		Mockito.when(repo.findByHeightGreaterThan(10)).thenReturn(allDinos);
		
		List<Dinosaurs> result = service.getByHeight(10);
		
		Assertions.assertEquals(result, allDinos);
	}
	
	@Test
	public void testGetByTypeHeightDesc() {
		
		List<Dinosaurs> allDinos = List.of(dino1ID, dino2ID);
		
		Mockito.when(repo.findByTypeOrderByHeightDesc("Theropod")).thenReturn(allDinos);
		
		List<Dinosaurs> result = service.getByTypeHeightDesc("Theropod");
		
		Assertions.assertEquals(result, allDinos);
	}
}


