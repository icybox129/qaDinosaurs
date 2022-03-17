package com.qa.qaDinosaurs.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.qaDinosaurs.model.Dinosaurs;

// The repo class has all of ther basic methods stored we may need to access our database
// repo.findAll() - returns all data
// repo.save(Object) - Create an object and save it into the DB

// JpaRepository takes in our object type (entity) and data type for our id
public interface Repo extends JpaRepository<Dinosaurs, Long> {
	
	public List<Dinosaurs> findByType(String type);
	
	public List<Dinosaurs> findByHeightGreaterThan(int height);
	
	public List<Dinosaurs> findByTypeOrderByHeightDesc(String type);
	

}
