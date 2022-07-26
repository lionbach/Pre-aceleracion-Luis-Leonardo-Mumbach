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

import com.alkemy.disney.dto.MovieBasicDTO;
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
	
	@GetMapping("/{id}")
	public ResponseEntity<MovieDTO> getById(@PathVariable Long id) {
		// obtener pelicula
		MovieDTO savedMovie =  movieService.getById(id);
		// devolver 200(ok) y la pelicula
		return ResponseEntity.ok().body(savedMovie);
	}
	
	@GetMapping
	public ResponseEntity<List<MovieBasicDTO>> getByFilters(
			@RequestParam(required=false) String title,
			@RequestParam(required=false, defaultValue = "0") Long genreId,
			@RequestParam(required=false, defaultValue = "ASC") String order
			) {
		List<MovieBasicDTO> movies =  movieService.getByFilters(title, genreId, order);
		return ResponseEntity.ok().body(movies);
	}
	
	@PutMapping
	public ResponseEntity<MovieDTO> update(@RequestBody MovieDTO movie){
		MovieDTO savedMovie =  movieService.update(movie);
		return ResponseEntity.ok().body(savedMovie);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		movieService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PostMapping("/{idMovie}/characters/{idCharacter}")
	public ResponseEntity<MovieDTO> addCharacter(@PathVariable Long idMovie, @PathVariable Long idCharacter) {
		MovieDTO savedMovie =  movieService.modifyCharacter(idMovie, idCharacter, "add");
		return ResponseEntity.ok().body(savedMovie);
	}
	
	@DeleteMapping("/{idMovie}/characters/{idCharacter}")
	public ResponseEntity<MovieDTO> removeCharacter(@PathVariable Long idMovie, @PathVariable Long idCharacter) {
		MovieDTO savedMovie =  movieService.modifyCharacter(idMovie, idCharacter, "remove");
		return ResponseEntity.ok().body(savedMovie);
	}
	
}
