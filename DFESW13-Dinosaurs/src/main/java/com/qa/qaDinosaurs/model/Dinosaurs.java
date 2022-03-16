package com.qa.qaDinosaurs.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// Put in the annotation @Entity so Spring knows this is an entity - import javax.persistence
@Entity
public class Dinosaurs {

	// Tell spring what property is our primary key
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, length = 100)
	private String name;
	
	@Column(nullable = false, length = 100)
	private String type;
	
	@Column(nullable = false)
	private int height;
	
	@Column(nullable = false)
	private int length;
	
	@Column(nullable = false)
	private boolean carnivore;
	
	@Column(nullable = false)
	private boolean herbivore;
	
	@Column(nullable = false)
	private boolean omnivore;
	
	// When using request bodies with Spring you need to include a default constructor
	// Generate constructors from superclass
	public Dinosaurs() {
		super();
		
	}

	// With ID - retrieving data from DB
	public Dinosaurs(long id, String name, String type, int height, int length, boolean carnivore, boolean herbivore,
			boolean omnivore) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.height = height;
		this.length = length;
		this.carnivore = carnivore;
		this.herbivore = herbivore;
		this.omnivore = omnivore;
	}

	// Without ID - posting data into DB
	public Dinosaurs(String name, String type, int height, int length, boolean carnivore, boolean herbivore,
			boolean omnivore) {
		super();
		this.name = name;
		this.type = type;
		this.height = height;
		this.length = length;
		this.carnivore = carnivore;
		this.herbivore = herbivore;
		this.omnivore = omnivore;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public boolean isCarnivore() {
		return carnivore;
	}

	public void setCarnivore(boolean carnivore) {
		this.carnivore = carnivore;
	}

	public boolean isHerbivore() {
		return herbivore;
	}

	public void setHerbivore(boolean herbivore) {
		this.herbivore = herbivore;
	}

	public boolean isOmnivore() {
		return omnivore;
	}

	public void setOmnivore(boolean omnivore) {
		this.omnivore = omnivore;
	}

	@Override
	public int hashCode() {
		return Objects.hash(carnivore, height, herbivore, id, length, name, omnivore, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dinosaurs other = (Dinosaurs) obj;
		return carnivore == other.carnivore && height == other.height && herbivore == other.herbivore && id == other.id
				&& length == other.length && Objects.equals(name, other.name) && omnivore == other.omnivore
				&& Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "Dinosaurs [id=" + id + ", name=" + name + ", type=" + type + ", height=" + height + ", length=" + length
				+ ", carnivore=" + carnivore + ", herbivore=" + herbivore + ", omnivore=" + omnivore + "]";
	}

}
