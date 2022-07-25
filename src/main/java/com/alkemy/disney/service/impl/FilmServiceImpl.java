package com.alkemy.disney.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.disney.dto.FilmDTO;
import com.alkemy.disney.entity.FilmEntity;
import com.alkemy.disney.mapper.FilmMapper;
import com.alkemy.disney.repository.FilmRepository;
import com.alkemy.disney.service.FilmService;

@Service
public class FilmServiceImpl implements FilmService {
	
	@Autowired
	private FilmMapper filmMapper;
	@Autowired
	private FilmRepository filmRepository;
	
	public FilmDTO save(FilmDTO dto) {
		// guardar
		FilmEntity entity = filmMapper.filmDTO2Entity(dto);
		FilmEntity entitySaved = filmRepository.save(entity);
		FilmDTO result = filmMapper.filmEntity2DTO(entitySaved);
		// devolver el film guardado
		return result;
	}

	public List<FilmDTO> getAll() {
		List<FilmEntity> entities = filmRepository.findAll();
		List<FilmDTO> result = filmMapper.filmEntityList2DTOList(entities);
		return result;
	}

}
