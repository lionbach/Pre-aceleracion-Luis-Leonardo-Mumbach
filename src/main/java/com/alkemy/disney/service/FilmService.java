package com.alkemy.disney.service;

import java.util.List;

import com.alkemy.disney.dto.FilmDTO;

public interface FilmService {
	FilmDTO save(FilmDTO dto); 
	List<FilmDTO> getAll();
}
