package com.alkemy.disney.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity // indicamos que el objeto es una entidad
@Table(name = "characters") // indicamos que tabla va a utilizar
@Getter // automatizar getters con lombok
@Setter // automatizar setters  con lombok
@SQLDelete(sql = "UPDATE characters SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@DynamicUpdate

public class CharacterEntity {
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
	
	//definimos la relacion movie-character
	@ManyToMany(mappedBy = "characters")
	private List<MovieEntity> movies = new ArrayList<>();
}
