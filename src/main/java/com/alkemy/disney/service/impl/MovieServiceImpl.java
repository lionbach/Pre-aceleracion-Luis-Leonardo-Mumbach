package com.alkemy.disney.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.dto.MovieFiltersDTO;
import com.alkemy.disney.dto.GenreDTO;
import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.entity.MovieEntity;
import com.alkemy.disney.mapper.MovieMapper;
import com.alkemy.disney.repository.MovieRepository;
import com.alkemy.disney.repository.specification.MovieSpecification;
import com.alkemy.disney.service.MovieService;
import com.alkemy.disney.service.GenreService;

@Service
public class MovieServiceImpl implements MovieService {
	
	private MovieMapper movieMapper;
	private MovieRepository movieRepository;
	private MovieSpecification movieSpecification;
	@Autowired
	private GenreService genreService;
	
	@Autowired
	public MovieServiceImpl(
			MovieRepository movieRepository,
			MovieSpecification movieSpecification,
			MovieMapper movieMapper
			) {
		this.movieRepository = movieRepository;
		this.movieSpecification = movieSpecification;
		this.movieMapper = movieMapper;
	}
	
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
		MovieEntity entity = movieMapper.movieDTO2UpdateEntity(dto);
		MovieEntity entityUpdated = movieRepository.save(entity);
		MovieDTO result = movieMapper.movieEntity2DTO(entityUpdated, true);
		return result;
	}

	public void delete(Long id) {
		movieRepository.deleteById(id);
	}

	public List<MovieBasicDTO> getByFilters(String title, Long genreId, String order) {
		MovieFiltersDTO filtersDTO = new MovieFiltersDTO(title, genreId, order);
		Specification<MovieEntity> spec = movieSpecification.getByFilters(filtersDTO);
		List<MovieEntity> entities = movieRepository.findAll(spec);
		List<MovieBasicDTO> result = movieMapper.movieEntityList2BasicDTOList(entities);
		return result;
	}

	public MovieDTO modifyCharacter(Long idMovie, Long idCharacter, String option) {
		MovieEntity entity = movieMapper.modifyCharacters2Entity(idMovie, idCharacter, option);
		MovieEntity entityUpdated = movieRepository.save(entity);
		MovieDTO result = movieMapper.movieEntity2DTO(entityUpdated, true);
		return result;
	}

}
