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

import com.alkemy.disney.dto.FilmDTO;
import com.alkemy.disney.service.FilmService;

//indicamos que es un controler
@RestController
@RequestMapping("film")

public class FilmController {
	@Autowired // para que spring inicialice el servicio
	private FilmService filmService;
	
	@GetMapping
	public ResponseEntity<List<FilmDTO>> getAll() {
		// obtener generos
		List<FilmDTO> films =  filmService.getAll();
		// devolver 200(ok) y los generos
		return ResponseEntity.ok().body(films);
	}
	
	@PostMapping // ingresamos a este controller por metodo POST
	public ResponseEntity<FilmDTO> save(@RequestBody FilmDTO film) {
		// guardar genero
		FilmDTO savedFilm =  filmService.save(film);
		// devolver 201(created) y el genero creado
		return ResponseEntity.status(HttpStatus.CREATED).body(savedFilm);
	}
}
