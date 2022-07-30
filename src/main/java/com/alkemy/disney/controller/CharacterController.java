package com.alkemy.disney.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.service.CharacterService;

//indicamos que es un controler
@RestController
@RequestMapping("characters")

public class CharacterController {
	
	@Autowired // para iniciar servicio
	private CharacterService characterService;
	
	@PostMapping // ingresamos a este controller por metodo POST
	public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO character) {
		// guardar Personaje
		CharacterDTO savedCharacter =  characterService.save(character);
		// devolver 201(created) y el personaje creado
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCharacter);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CharacterDTO> getById(@PathVariable Long id) {
		// obtener personage
		CharacterDTO character =  characterService.getById(id);
		// devolver 200(ok) y el personage
		return ResponseEntity.ok().body(character);
	}
	
	@GetMapping
	public ResponseEntity<List<CharacterBasicDTO>> getByFilters(
			@RequestParam(required=false) String name,
			@RequestParam(required=false, defaultValue = "0") int age,
			@RequestParam(required=false, defaultValue = "0") int weight,
			@RequestParam(required=false, defaultValue = "0") long movieId
			) {
		List<CharacterBasicDTO> characters =  characterService.getByFilters(name, age, weight, movieId);
		return ResponseEntity.ok().body(characters);
	}
	
	@PutMapping
	public ResponseEntity<CharacterDTO> update(@RequestBody CharacterDTO character){
		CharacterDTO savedCharacter =  characterService.update(character);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCharacter);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		characterService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
