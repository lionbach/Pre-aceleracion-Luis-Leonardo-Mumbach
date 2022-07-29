package com.alkemy.disney.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.entity.MovieEntity;
import com.alkemy.disney.entity.CharacterEntity;


@Component
public class MovieMapper {
	@Autowired
	private GenreMapper genreMapper;
	@Autowired
	private CharacterMapper characterMapper;

	
 	public MovieEntity movieDTO2Entity(MovieDTO dto) {
		MovieEntity entity = new MovieEntity();

		entity.setImg(dto.getImg());
		entity.setTitle(dto.getTitle());
		entity.setRating(dto.getRating());
		entity.setReleaseDate(
				string2LocalDate(dto.getReleaseDate())
				);
		entity.setGenre(genreMapper.genreDTO2Entity(dto.getGenre()));
		entity.setGenreId(dto.getGenre().getId());
		List<CharacterEntity> characters = new ArrayList<>();
		for (CharacterDTO character : dto.getCharacters()) {
			characters.add(characterMapper.characterDTO2Entity(character));
		}
		entity.setCharacters(characters);
		return entity;
	}
 	




	public MovieDTO movieEntity2DTO(MovieEntity entity, boolean loadCharacters) {
		MovieDTO dto = new MovieDTO();
		dto.setId(entity.getId());
		dto.setImg(entity.getImg());
		dto.setTitle(entity.getTitle());
		dto.setRating(entity.getRating());
		dto.setReleaseDate(entity.getReleaseDate().toString());
		dto.setGenre(genreMapper.genreEntity2DTO(entity.getGenre()));
		dto.setGenreId(entity.getGenreId());
		if (loadCharacters) {
			List<CharacterDTO> characters = new ArrayList<>();
			for (CharacterEntity character : entity.getCharacters()) {
				characters.add(characterMapper.characterEntity2DTO(character, false));
			}
			dto.setCharacters(characters);
		}
		return dto;
	}

	public List<MovieDTO> movieEntityList2DTOList(List<MovieEntity> entities, boolean loadCharacters) {
		List<MovieDTO> dtos = new ArrayList<>();
		for (MovieEntity entity: entities) {
			dtos.add(movieEntity2DTO(entity, loadCharacters));
		}
		return dtos;
	}
	
 	private LocalDate string2LocalDate(String stringDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(stringDate, formatter);
		return date;
	}
 	
 	
	
}