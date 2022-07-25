package com.alkemy.disney.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.disney.dto.PersonageDTO;
import com.alkemy.disney.entity.PersonageEntity;
import com.alkemy.disney.mapper.PersonageMapper;
import com.alkemy.disney.repository.PersonageRepository;
import com.alkemy.disney.service.PersonageService;

@Service
public class PersonageServiceImpl implements PersonageService {
	
	@Autowired
	private PersonageMapper personageMapper;
	@Autowired
	private PersonageRepository personageRepository;
	
	public PersonageDTO save(PersonageDTO dto) {
		// guardar
		PersonageEntity entity = personageMapper.personageDTO2Entity(dto);
		PersonageEntity entitySaved = personageRepository.save(entity);
		PersonageDTO result = personageMapper.personageEntity2DTO(entitySaved);
		// devolver el genero guardado
		return result;
	}
}
