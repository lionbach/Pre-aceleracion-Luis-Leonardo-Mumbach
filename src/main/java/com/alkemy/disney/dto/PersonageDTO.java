package com.alkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;

@Getter // automatizar getters con lombok
@Setter // automatizar setters  con lombok

public class PersonageDTO {
	private Long id;
	private String img;
	private String name;
	private int weight;
	private int age;
	private String history;
}
