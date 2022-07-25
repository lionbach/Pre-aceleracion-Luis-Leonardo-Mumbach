package com.alkemy.disney.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.disney.dto.PersonageDTO;
//import com.alkemy.disney.service.PersonageService;
import com.alkemy.disney.service.PersonageService;

//indicamos que es un controler
@RestController
@RequestMapping("personage")

public class PersonageController {
	@Autowired // para que spring inicialice el servicio
	private PersonageService personageService;
	
	@PostMapping // ingresamos a este controller por metodo POST
	public ResponseEntity<PersonageDTO> save(@RequestBody PersonageDTO personage) {
		// guardar Personaje
		PersonageDTO savedPersonage =  personageService.save(personage);
		// devolver 201(created) y el personaje creado
		return ResponseEntity.status(HttpStatus.CREATED).body(savedPersonage);
	}
}
