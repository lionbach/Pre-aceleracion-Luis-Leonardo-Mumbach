package com.alkemy.disney.entity;

import javax.persistence.*;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity // indicamos que el objeto es una entidad
@Table(name = "genre") // indicamos que tabla va a utilizar
@Getter // automatizar getters con lombok
@Setter // automatizar setters  con lombok
@SQLDelete(sql = "UPDATE genre SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")

public class GenreEntity {
	// columna ID
	@Id //indicamos que es un id
	@GeneratedValue(strategy = GenerationType.SEQUENCE) // se genera secuencialmente (autoincremental)
	private Long id;
	
	private String name;
	private String img; //url de la imagen
	private Boolean deleted = Boolean.FALSE;
	
	// relacion genre-film, no lo declaramos, se declara la relacion en FilmEntity
	
	/*
	Esto es necesario? ventajas y desventajas?
	opcional, sirve si necesitos obtener las peliculas desde el genero.
	(la relacion se creo pensado para que dese la pelicula se obtine el genero)
	@OneToMany(mappedBy = "genero", fetch = FetchType.LAZY)
	private Set<FilmEntity> films = new HashSet<>();
	*/
}
