package com.qa.qaDinosaurs.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.qaDinosaurs.model.Dinosaurs;
import com.qa.qaDinosaurs.repo.Repo;

@Service
public class ServiceDB {
	
	private Repo repo;

	public ServiceDB(Repo repo) {
		super();
		this.repo = repo;
	}

	// Returns all objects as a list
	public List<Dinosaurs> getDino() {
		return repo.findAll();
	}

	public boolean createDino(Dinosaurs dino) {
		repo.save(dino);
		return true;
	}

	public Dinosaurs getById(long id) {
		// .get() if there is a value return it, otherwise throw an exception
		return repo.findById(id).get();
	}

	public boolean deleteById(long id) {
		repo.deleteById(id);
		return true;
		
	}

	public boolean deleteAll() {
		 repo.deleteAll();
		 return true;
		
	}

	// Update takes in an ID and a request body
	public boolean updateById(long id, Dinosaurs dino) {
		// Find the object we want to update
		Dinosaurs dinoOld = getById(id);
		
		// Update the properties of this object
		// running the setProperty method of the old dino and replacing with new values
		dinoOld.setName(dino.getName());
		dinoOld.setType(dino.getType());
		dinoOld.setHeight(dino.getHeight());
		dinoOld.setLength(dino.getLength());;
		dinoOld.setCarnivore(dino.isCarnivore());
		dinoOld.setHerbivore(dino.isHerbivore());
		dinoOld.setOmnivore(dino.isOmnivore());
		
		// Creating a new dino with the value of dinoOld
		Dinosaurs updatedDino = dinoOld;
		
		// Saving the old dino into the DB
		repo.save(updatedDino);
		return true;
	}

	public ArrayList<Dinosaurs> addArray(Dinosaurs[] dinoArray) {
		// TODO Auto-generated method stub
		return null;
	}

}
