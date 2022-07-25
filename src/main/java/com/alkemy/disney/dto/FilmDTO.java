package com.alkemy.disney.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.alkemy.disney.entity.GenreEntity;
import com.alkemy.disney.entity.PersonageEntity;

import lombok.Getter;
import lombok.Setter;

@Getter // automatizar getters con lombok
@Setter // automatizar setters  con lombok

public class FilmDTO {
	private Long id;
	private String img;
	private String title;
	private Short rating;
	private LocalDate releaseDate;
	private GenreEntity genre;
	private Long genreId;
	private Set<PersonageEntity> personages = new HashSet<>();
}
