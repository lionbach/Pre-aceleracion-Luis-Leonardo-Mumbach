package com.alkemy.disney.mapper;

import org.springframework.stereotype.Component;

import com.alkemy.disney.dto.PersonageDTO;
import com.alkemy.disney.entity.PersonageEntity;

@Component
public class PersonageMapper {

	public PersonageEntity personageDTO2Entity(PersonageDTO dto) {
		PersonageEntity entity = new PersonageEntity();
		entity.setImg(dto.getImg());
		entity.setName(dto.getName());
		entity.setWeight(dto.getWeight());
		entity.setAge(dto.getAge());
		entity.setHistory(dto.getHistory());
		return entity;
	}

	public PersonageDTO personageEntity2DTO(PersonageEntity entity) {
		PersonageDTO dto = new PersonageDTO();
		dto.setId(entity.getId());
		dto.setImg(entity.getImg());
		dto.setName(entity.getName());
		dto.setWeight(entity.getWeight());
		dto.setAge(entity.getAge());
		dto.setHistory(entity.getHistory());
		return dto;
	}

}
