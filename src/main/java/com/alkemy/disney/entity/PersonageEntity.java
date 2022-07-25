package com.alkemy.disney.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity // indicamos que el objeto es una entidad
@Table(name = "personage") // indicamos que tabla va a utilizar
@Getter // automatizar getters con lombok
@Setter // automatizar setters  con lombok
@SQLDelete(sql = "UPDATE genre SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")

public class PersonageEntity {
	// columna ID
	@Id //indicamos que es un id
	@GeneratedValue(strategy = GenerationType.SEQUENCE) // se genera secuencialmente (autoincremental)
	private Long id;
	
	// columnas sensillas
	private String img; // url
	private String name;
	private int weight;
	private int age;
	private String history;
	private Boolean deleted = Boolean.FALSE;
	
	//definimos la relacion film-personage
	@ManyToMany(mappedBy = "personages")
	private Set<FilmEntity> films = new HashSet<>();
}
