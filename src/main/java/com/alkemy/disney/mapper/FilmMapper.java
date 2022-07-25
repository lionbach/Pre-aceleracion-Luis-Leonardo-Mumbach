package com.alkemy.disney.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alkemy.disney.dto.FilmDTO;
import com.alkemy.disney.entity.FilmEntity;
import com.alkemy.disney.entity.GenreEntity;
import com.alkemy.disney.entity.PersonageEntity;
import com.alkemy.disney.repository.GenreRepository;
import com.alkemy.disney.repository.PersonageRepository;


@Component
public class FilmMapper {
	@Autowired
	private GenreRepository genreRepository;
	
	@Autowired
	private PersonageRepository personageRepository;
	
 	public FilmEntity filmDTO2Entity(FilmDTO dto) {
		FilmEntity entity = new FilmEntity();
		
		// mapeamos genre
		GenreEntity genreEntity = new GenreEntity();  
		genreEntity.setName(dto.getGenre().getName());
		//creamos el genero
		GenreEntity genreSaved = genreRepository.save(genreEntity);
		
		
		// mapeamos los personages
		Set<PersonageEntity> personages = new HashSet<>();
		for (PersonageEntity personage : dto.getPersonages()) 
		{ 
		    PersonageEntity newPersonage = new PersonageEntity();
		    newPersonage.setName(personage.getName());
		    // guardamos el personaje
		    PersonageEntity personageSaved = personageRepository.save(newPersonage);
			personages.add(personageSaved);
		}
		
		entity.setImg(dto.getImg());
		entity.setTitle(dto.getTitle());
		entity.setRating(dto.getRating());
		entity.setReleaseDate(dto.getReleaseDate());
		entity.setGenre(genreSaved); //este dato tiene que ser un objeto genre.
		entity.setGenreId(genreSaved.getId());
		entity.setPersonages(personages); //este dato tiene que ser un set de objetos personage
		return entity;
	}
 	

	public FilmDTO filmEntity2DTO(FilmEntity entity) {
		FilmDTO dto = new FilmDTO();
		dto.setId(entity.getId());
		dto.setImg(entity.getImg());
		dto.setTitle(entity.getTitle());
		dto.setRating(entity.getRating());
		dto.setReleaseDate(entity.getReleaseDate());
		dto.setGenre(entity.getGenre());
		dto.setGenreId(entity.getGenreId());
		dto.setPersonages(entity.getPersonages());
		return dto;
	}

	public List<FilmDTO> filmEntityList2DTOList(List<FilmEntity> entities) {
		List<FilmDTO> dtos = new ArrayList<>();
		for (FilmEntity entity: entities) {
			dtos.add(filmEntity2DTO(entity));
		}
		return dtos;
	}
	
}