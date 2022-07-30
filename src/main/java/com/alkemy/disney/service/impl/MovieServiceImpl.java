package com.alkemy.disney.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.dto.GenreDTO;
import com.alkemy.disney.entity.MovieEntity;
import com.alkemy.disney.mapper.MovieMapper;
import com.alkemy.disney.repository.MovieRepository;
import com.alkemy.disney.service.MovieService;
import com.alkemy.disney.service.GenreService;

@Service
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	private MovieMapper movieMapper;
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private GenreService genreService;
	
	public MovieDTO save(MovieDTO dto) {
		// Recuperamos el Genero
		GenreDTO savedGenre =  genreService.getById(dto.getGenreId());
		dto.setGenre(savedGenre);

		// guardardamos la pelicula
		MovieEntity entity = movieMapper.movieDTO2Entity(dto);
		MovieEntity entitySaved = movieRepository.save(entity);
		MovieDTO result = movieMapper.movieEntity2DTO(entitySaved, true);
		// devolver el movie guardado
		return result;
	}

	public List<MovieDTO> getAll() {
		List<MovieEntity> entities = movieRepository.findAll();
		List<MovieDTO> result = movieMapper.movieEntityList2DTOList(entities, true);
		return result;
	}
	

	public MovieDTO getById(Long id) {
		Optional<MovieEntity> entity = movieRepository.findById(id);
		MovieDTO result = movieMapper.movieEntity2DTO(entity.get(), true);
		return result;
	}
	
	public MovieDTO update(MovieDTO dto) {
		//actualizamos
		MovieEntity entity = movieMapper.movieDTO2UpdateEntity(dto);
		MovieEntity entityUpdated = movieRepository.save(entity);
		MovieDTO result = movieMapper.movieEntity2DTO(entityUpdated, true);
		return result;
	}

	public void delete(Long id) {
		movieRepository.deleteById(id);
	}

}
