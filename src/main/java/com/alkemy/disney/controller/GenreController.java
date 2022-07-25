package com.alkemy.disney.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.disney.dto.GenreDTO;
import com.alkemy.disney.service.GenreService;

//indicamos que es un controler
@RestController
@RequestMapping("genre")

public class GenreController {
	@Autowired // para que spring inicialice el servicio
	private GenreService genreService;
	
	@GetMapping
	public ResponseEntity<List<GenreDTO>> getAll() {
		// obtener generos
		List<GenreDTO> genres =  genreService.getAll();
		// devolver 200(ok) y los generos
		return ResponseEntity.ok().body(genres);
	}
	
	@PostMapping // ingresamos a este controller por metodo POST
	public ResponseEntity<GenreDTO> save(@RequestBody GenreDTO genre) {
		// guardar genero
		GenreDTO savedGenre =  genreService.save(genre);
		// devolver 201(created) y el genero creado
		return ResponseEntity.status(HttpStatus.CREATED).body(savedGenre);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		genreService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	} 
}

