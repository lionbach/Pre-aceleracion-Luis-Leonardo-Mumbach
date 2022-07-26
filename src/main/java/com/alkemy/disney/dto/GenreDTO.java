package com.alkemy.disney.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter // automatizar getters con lombok
@Setter // automatizar setters  con lombok

public class GenreDTO {
	private Long id;
	private String name;
	private String img; //url de la imagen
	private List<MovieDTO> movies;
}
