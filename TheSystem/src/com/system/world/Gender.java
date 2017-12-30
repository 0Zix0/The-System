package com.system.world;

public enum Gender {

	MALE("male", 'M'),
	FEMALE("female", 'F');
	
	public String name;
	public char symbol;
	Gender(String name, char symbol) {
		this.name = name;
		this.symbol = symbol;
	}
}
