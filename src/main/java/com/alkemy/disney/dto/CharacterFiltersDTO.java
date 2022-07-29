package com.alkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CharacterFiltersDTO {

	private String name;
	private int age;
	private int weight;
	private long idMovie;
	//private String order;
	
	public CharacterFiltersDTO(String name, int age, int weight, long idMovie) {
		this.name = name;
		this.age = age;
		this.weight = weight;
		this.idMovie = idMovie;
		//this.order = order;
	}
	
	//public boolean isASC() {return this.order.compareToIgnoreCase("ASC") == 0;}
	
	//public boolean isDESC() {return this.order.compareToIgnoreCase("DESC") == 0;}
	
}
