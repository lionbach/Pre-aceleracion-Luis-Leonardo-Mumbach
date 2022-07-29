package com.alkemy.disney.service;

import java.util.List;

import com.alkemy.disney.dto.MovieDTO;

public interface MovieService {
	MovieDTO save(MovieDTO dto); 
	List<MovieDTO> getAll();
	void delete(Long id);
	
}
