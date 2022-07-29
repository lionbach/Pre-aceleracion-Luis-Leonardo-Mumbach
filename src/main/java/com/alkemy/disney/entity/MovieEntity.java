package com.alkemy.disney.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.Setter;

@Entity // indicamos que el objeto es una entidad
@Table(name = "movies") // indicamos que tabla va a utilizar
@Getter // automatizar getters con lombok
@Setter // automatizar setters  con lombok
@SQLDelete(sql = "UPDATE movies SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@DynamicUpdate

public class MovieEntity {
	// columna ID
	@Id //indicamos que es un id
	@GeneratedValue(strategy = GenerationType.SEQUENCE) // se genera secuencialmente (autoincremental)
	private Long id;
	
	//columnas sencillas
	private String img;
	private String title;
	private Short rating; //entero del 0 al 5
	private Boolean deleted = Boolean.FALSE;
	
	@Column(name = "release_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate releaseDate;
	
	// definos la relacion movie-genre
	// no se va a crear esta columna en mysql
	// la columna de la relacion la definimos despues
	// tambien sirve para obtener, en java, los datos del genero en MovieEntity
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(
			name = "genre_id", // indica con que columna vamos a relacionar en genero
			insertable = false, updatable = false // esto permite que la columna no se cree
	)
	private GenreEntity genre;
	
	// columna de la relacion genre_id
	@Column(name = "genre_id", nullable = false)
	private Long genreId;
	
	//definimos la relacion movie-character, se creara tabla intermedia
	@ManyToMany(
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			})
	@JoinTable(
			name = "character_movie", // nombre de la tabla intermedia
			joinColumns = @JoinColumn(name = "movie_id"),
			inverseJoinColumns = @JoinColumn(name = "character_id"))
	private List<CharacterEntity> characters = new ArrayList<>();
}
