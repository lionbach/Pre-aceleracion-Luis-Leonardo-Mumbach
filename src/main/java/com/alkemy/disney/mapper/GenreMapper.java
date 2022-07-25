package com.alkemy.disney.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alkemy.disney.dto.GenreDTO;
import com.alkemy.disney.entity.GenreEntity;

@Component
public class GenreMapper {
	
	public GenreEntity genreDTO2Entity(GenreDTO dto) {
		GenreEntity entity = new GenreEntity();
		entity.setName(dto.getName());
		entity.setImg(dto.getImg());
		return entity;
	}
	
	public GenreDTO genreEntity2DTO(GenreEntity entity) {
		GenreDTO dto = new GenreDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setImg(entity.getImg());
		return dto;
	}

	public List<GenreDTO> genreEntityList2DTOList(List<GenreEntity> entities) {
		List<GenreDTO> dtos = new ArrayList<>();
		for (GenreEntity entity: entities) {
			dtos.add(genreEntity2DTO(entity));
		}
		return dtos;
	}
	
}
