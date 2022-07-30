package com.alkemy.disney.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.disney.dto.GenreDTO;
import com.alkemy.disney.entity.GenreEntity;
import com.alkemy.disney.mapper.GenreMapper;
import com.alkemy.disney.repository.GenreRepository;
import com.alkemy.disney.service.GenreService;

@Service
public class GenreServiceImpl implements GenreService {
	
	@Autowired
	private GenreMapper genreMapper;
	@Autowired
	private GenreRepository genreRepository;
	
	public GenreDTO save(GenreDTO dto) {
		// guardar
		GenreEntity entity = genreMapper.genreDTO2Entity(dto);
		GenreEntity entitySaved = genreRepository.save(entity);
		GenreDTO result = genreMapper.genreEntity2DTO(entitySaved);
		// devolver el genero guardado
		return result;
	}
	
	public GenreDTO getById(Long id) {
		Optional<GenreEntity> entity = genreRepository.findById(id);
		GenreDTO result = genreMapper.genreEntity2DTO(entity.get());
		return result;
	}

	public List<GenreDTO> getAll() {
		List<GenreEntity> entities = genreRepository.findAll();
		List<GenreDTO> result = genreMapper.genreEntityList2DTOList(entities);
		return result;
	}

	public void delete(Long id) {
		genreRepository.deleteById(id);		
	}

	
}
