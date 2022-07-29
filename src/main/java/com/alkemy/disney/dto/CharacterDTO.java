package com.alkemy.disney.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter // automatizar getters con lombok
@Setter // automatizar setters  con lombok

public class CharacterDTO {
	private Long id;
	private String img;
	private String name;
	private int weight;
	private int age;
	private String history;
	private List<MovieDTO> movies;
}
