package com.alkemy.disney.service;

import java.util.List;

import com.alkemy.disney.dto.GenreDTO;

public interface GenreService {
	GenreDTO save(GenreDTO dto);
	List<GenreDTO> getAll();
	void delete(Long id);
}
