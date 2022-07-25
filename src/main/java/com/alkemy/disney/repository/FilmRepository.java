package com.alkemy.disney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.disney.entity.FilmEntity;

@Repository
public interface FilmRepository extends JpaRepository<FilmEntity, Long> {

}
