package com.alkemy.disney.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.dto.GenreDTO;
import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.entity.MovieEntity;
import com.alkemy.disney.repository.CharacterRepository;
import com.alkemy.disney.service.CharacterService;
import com.alkemy.disney.service.GenreService;
import com.alkemy.disney.service.MovieService;
import com.alkemy.disney.entity.CharacterEntity;


@Component
public class MovieMapper {
	@Autowired
	private GenreMapper genreMapper;
	@Autowired
	private CharacterMapper characterMapper;
	@Autowired
	private CharacterRepository characterRepository;
	@Autowired
	private GenreService genreService;
	@Autowired
	private MovieService movieService;
	
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

	public MovieEntity movieDTO2UpdateEntity(MovieDTO dto) {
		// recuperamos la pelicula
		MovieDTO oldDTO = movieService.getById(dto.getId());
		// Recuperamos el Genero
		GenreDTO genre =  genreService.getById(dto.getGenreId());
		dto.setGenre(genre);
		
		MovieEntity entity = new MovieEntity();
		
		// datos que no se actualizan
		entity.setId(oldDTO.getId());
		List<CharacterEntity> characters = new ArrayList<>();
		for (CharacterDTO character : oldDTO.getCharacters()) {
			CharacterEntity Char = characterMapper.characterDTO2Entity(character);
			Char.setId(character.getId());
			characters.add(Char);
		}
		entity.setCharacters(characters);
		
		// datos que pueden actualisarce
		entity.setImg(dto.getImg());
		entity.setTitle(dto.getTitle());
		entity.setRating(dto.getRating());
		entity.setReleaseDate(
				string2LocalDate(dto.getReleaseDate())
				);
		entity.setGenre(genreMapper.genreDTO2Entity(dto.getGenre()));
		entity.setGenreId(dto.getGenre().getId());

		return entity;
	}

	public List<MovieBasicDTO> movieEntityList2BasicDTOList(List<MovieEntity> entities) {
		List<MovieBasicDTO> dtos = new ArrayList<>();
		for (MovieEntity entity: entities) {
			dtos.add(MovieEntity2BasicDTO(entity));
		}
		return dtos;
	}

	private MovieBasicDTO MovieEntity2BasicDTO(MovieEntity entity) {
		MovieBasicDTO dto = new MovieBasicDTO();
		dto.setImg(entity.getImg());
		dto.setTitle(entity.getTitle());
		dto.setReleaseDate(entity.getReleaseDate().toString());
		return dto;
	}

	public MovieEntity modifyCharacters2Entity(Long idMovie, Long idCharacter, String option) {
		// recuperamos la pelicula
		MovieDTO dto = movieService.getById(idMovie);
		// Recuperamos el Genero
		GenreDTO genre =  genreService.getById(dto.getGenreId());
		dto.setGenre(genre);
		
		MovieEntity entity = new MovieEntity();
		
		// datos que no se actualizan
		entity.setId(dto.getId());
		entity.setImg(dto.getImg());
		entity.setTitle(dto.getTitle());
		entity.setRating(dto.getRating());
		entity.setReleaseDate(
				string2LocalDate(dto.getReleaseDate())
				);
		entity.setGenre(genreMapper.genreDTO2Entity(dto.getGenre()));
		entity.setGenreId(dto.getGenre().getId());
		
		// modificacion de personajes
		List<CharacterEntity> characters = new ArrayList<>();
		// agregamos el nuevo personaje, si es requerido
		if (option.equals("add")) {
			//CharacterDTO newCharacterDTO = characterService.getByIdForAddCharacter(idCharacter);
			Optional<CharacterEntity> optionalEntity = characterRepository.findById(idCharacter); 
			//CharacterEntity newCharacterEntity = characterMapper.characterDTO2Entity(newCharacterDTO);
			//newCharacterEntity.setId(newCharacterDTO.getId());
			characters.add(optionalEntity.get());
		}
		// cargamos los viejos personajes, saltandonos el que se requiera eliminar
		for (CharacterDTO character : dto.getCharacters()) {
			if (!(option.equals("remove") && character.getId()==idCharacter)) {
				CharacterEntity Char = characterMapper.characterDTO2Entity(character);
				Char.setId(character.getId());
				characters.add(Char);
			}
		}
		entity.setCharacters(characters);
		
		

		return entity;
	}
 	
 	
	
}