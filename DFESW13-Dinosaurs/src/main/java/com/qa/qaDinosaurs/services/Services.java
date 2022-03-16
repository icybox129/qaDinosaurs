package com.qa.qaDinosaurs.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.qa.qaDinosaurs.model.Dinosaurs;
import com.qa.qaDinosaurs.repo.Repo;

// This tells Spring this is our services class
// Services is the business logic, pushing data to DB / arrayList, updating, deleting etc.
@Service
public class Services {
	
	// Specify that we want to use our repo class
	
	private Repo repo;

	// When Spring creates the Server Object / bean, it takes in our repo class
	public Services(Repo repo) {
		super();
		this.repo = repo;
	}

	private ArrayList<Dinosaurs> dinoList = new ArrayList<>();
	
	public ArrayList<Dinosaurs> getDino() {
		
	ArrayList<Dinosaurs> result = new ArrayList<>();
		
		for(Dinosaurs list : dinoList) {
			result.add(list);
		}
		return result;
	}
	
	public boolean createDino(Dinosaurs dino) {
		
		dino.setId(dinoList.size() + 1);
		dinoList.add(dino);
		return true;
	}
	
	public Dinosaurs getByIndex(int index) {
		return dinoList.get(index);
	}
	
	public Dinosaurs deleteByIndex(int index) {
		return dinoList.remove(index);
	}
	
	public boolean deleteAll(){
		dinoList.clear();
		return true;
	}
	
	public Dinosaurs updateByIndex(int index, Dinosaurs dino) {
		
		return dinoList.set(index, dino);
	}
	
	public ArrayList<Dinosaurs> addArray(Dinosaurs[] dinoArray) {
		
//	ArrayList<Dinosaurs> result = new ArrayList<>();
		
		for(Dinosaurs list : dinoArray) {
			dinoList.add(list);
		}
		return dinoList;
	}
}
