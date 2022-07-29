package com.alkemy.disney.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter // automatizar getters con lombok
@Setter // automatizar setters  con lombok

public class MovieDTO {
	private Long id;
	private String img;
	private String title;
	private Short rating;
	private String releaseDate;
	//private LocalDate releaseDate;
	private GenreDTO genre;
	private Long genreId;
	private List<CharacterDTO> characters;
}
