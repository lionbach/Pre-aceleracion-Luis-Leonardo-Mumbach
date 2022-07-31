package com.alkemy.disney.service;

import java.util.List;

import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.dto.MovieDTO;

public interface MovieService {
	MovieDTO save(MovieDTO dto); 
	List<MovieDTO> getAll();
	MovieDTO getById(Long id);
	MovieDTO update(MovieDTO movie);
	void delete(Long id);
	List<MovieBasicDTO> getByFilters(String title, Long genreId, String order);
	
}
