package com.alkemy.disney.service.impl;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alkemy.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.dto.CharacterFiltersDTO;
import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.mapper.CharacterMapper;
import com.alkemy.disney.repository.CharacterRepository;
import com.alkemy.disney.repository.specification.CharacterSpecification;
import com.alkemy.disney.service.CharacterService;

@Service
public class CharacterServiceImpl implements CharacterService {
	
	private CharacterRepository characterRepository;
	private CharacterSpecification characterSpecification;
	private CharacterMapper characterMapper;
	
	@Autowired
	public CharacterServiceImpl(
			CharacterRepository characterRepository,
			CharacterSpecification characterSpecification,
			CharacterMapper characterMapper
			) {
		this.characterRepository = characterRepository;
		this.characterSpecification = characterSpecification;
		this.characterMapper = characterMapper;
	}
	
	public CharacterDTO save(CharacterDTO dto) {
		// guardar
		CharacterEntity entity = characterMapper.characterDTO2Entity(dto);
		CharacterEntity entitySaved = characterRepository.save(entity);
		CharacterDTO result = characterMapper.characterEntity2DTO(entitySaved, true);
		// devolver el genero guardado
		return result;
	}
	
	public CharacterDTO update(CharacterDTO dto) {
		CharacterEntity entity = characterMapper.characterDTO2UpdateEntity(dto);
		CharacterEntity entityUpdated = characterRepository.save(entity);
		CharacterDTO result = characterMapper.characterEntity2DTO(entityUpdated, true);
		return result;
	}

	public List<CharacterDTO> getAll() {
		List<CharacterEntity> entities = characterRepository.findAll();
		List<CharacterDTO> result = characterMapper.characterEntityList2DTOList(entities, true);
		return result;
	}
	
	public List<CharacterBasicDTO> getByFilters(String name, int age, int weight, long idMovie) {
		CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, weight, idMovie);
		Specification<CharacterEntity> spec = characterSpecification.getByFilters(filtersDTO);
		List<CharacterEntity> entities = characterRepository.findAll(spec);
		List<CharacterBasicDTO> result = characterMapper.characterEntityList2BasicDTOList(entities);
		return result;
	}
	
	public CharacterDTO getById(Long id) {
		Optional<CharacterEntity> entity = characterRepository.findById(id);
		CharacterDTO result = characterMapper.characterEntity2DTO(entity.get(), true);
		return result;
	}

	public void delete(Long id) {
		characterRepository.deleteById(id);
	}	
	
}
