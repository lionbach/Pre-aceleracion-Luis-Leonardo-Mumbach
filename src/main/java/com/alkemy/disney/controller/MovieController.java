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

import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.service.MovieService;

//indicamos que es un controler
@RestController
@RequestMapping("movies")

public class MovieController {
	
	@Autowired // para iniciar servicio
	private MovieService movieService;
	
	@PostMapping // ingresamos a este controller por metodo POST
	public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movie) {
		// guardar pelicula
		MovieDTO savedMovie =  movieService.save(movie);
		// devolver 201(created) y la pelicula creado
		return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
	}
	
	@GetMapping
	public ResponseEntity<List<MovieDTO>> getAll() {
		// obtener generos
		List<MovieDTO> movies =  movieService.getAll();
		// devolver 200(ok) y los generos
		return ResponseEntity.ok().body(movies);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		movieService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	
}
