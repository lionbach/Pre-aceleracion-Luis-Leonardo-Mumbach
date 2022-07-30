package com.alkemy.disney.service;

import java.util.List;

import com.alkemy.disney.dto.CharacterBasicDTO;

//import java.util.List;

import com.alkemy.disney.dto.CharacterDTO;

public interface CharacterService {
	CharacterDTO save(CharacterDTO dto);
	List<CharacterDTO> getAll();
	void delete(Long id);
	CharacterDTO update(CharacterDTO character);
	List<CharacterBasicDTO> getByFilters(String name, int age, int weight, long idMovie);
	CharacterDTO getById(Long id);
	//List<CharacterDTO> getByMovieId(Long id);
}
