package com.alkemy.disney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.disney.entity.PersonageEntity;

@Repository
public interface PersonageRepository extends JpaRepository<PersonageEntity, Long>{

}
