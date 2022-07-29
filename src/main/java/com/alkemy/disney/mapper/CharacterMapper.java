package com.alkemy.disney.mapper;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alkemy.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.CharacterEntity;

@Component
public class CharacterMapper {
	
	@Autowired
	private MovieMapper movieMapper;

	public CharacterEntity characterDTO2Entity(CharacterDTO dto) {
		CharacterEntity entity = new CharacterEntity();
		entity.setImg(dto.getImg());
		entity.setName(dto.getName());
		entity.setWeight(dto.getWeight());
		entity.setAge(dto.getAge());
		entity.setHistory(dto.getHistory());
		return entity;
	}

	public CharacterDTO characterEntity2DTO(CharacterEntity entity, boolean loadMovies) {
		CharacterDTO dto = new CharacterDTO();
		dto.setId(entity.getId());
		dto.setImg(entity.getImg());
		dto.setName(entity.getName());
		dto.setWeight(entity.getWeight());
		dto.setAge(entity.getAge());
		dto.setHistory(entity.getHistory());
		if (loadMovies) {
			//aca si va falso, asi no carga los personages de las pelis
			List<MovieDTO> moviesDTO = movieMapper.movieEntityList2DTOList(entity.getMovies(), false);
			dto.setMovies(moviesDTO);
		}
		return dto;
	}

	public List<CharacterDTO> characterEntityList2DTOList(List<CharacterEntity> entities, boolean loadMovies) {
		List<CharacterDTO> dtos = new ArrayList<>();
		for (CharacterEntity entity: entities) {
			dtos.add(characterEntity2DTO(entity, loadMovies));
		}
		return dtos;
	}

	public CharacterEntity characterUpdateDTO2Entity(CharacterDTO dto) {
		CharacterEntity entity = characterDTO2Entity(dto);
		entity.setId(dto.getId());
		return entity;
	}

	public List<CharacterBasicDTO> characterEntityList2BasicDTOList(List<CharacterEntity> entities) {
		List<CharacterBasicDTO> dtos = new ArrayList<>();
		for (CharacterEntity entity: entities) {
			dtos.add(characterEntity2BasicDTO(entity));
		}
		return dtos;
	}

	private CharacterBasicDTO characterEntity2BasicDTO(CharacterEntity entity) {
		CharacterBasicDTO dto = new CharacterBasicDTO();
		dto.setImg(entity.getImg());
		dto.setName(entity.getName());
		return dto;
	}
	
	/*
	
	public void characterEntityRefreshValues(CharacterEntity entity, CharacterDTO dto) {
		entity.setImg(dto.getImg());
		entity.setName(dto.getName());
		entity.setWeight(dto.getWeight());
		entity.setAge(dto.getAge());
		entity.setHistory(dto.getHistory());
	}
	*/


}
