package com.alkemy.disney.repository.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alkemy.disney.dto.CharacterFiltersDTO;
import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.entity.MovieEntity;

@Component
public class CharacterSpecification {

	public Specification<CharacterEntity> getByFilters(CharacterFiltersDTO filtersDTO){
		return (root, query, criteriaBuilder) -> {
			
			List<Predicate> predicates = new ArrayList<>();
			
			if(StringUtils.hasLength(filtersDTO.getName())) {
				predicates.add(
					criteriaBuilder.like(
						criteriaBuilder.lower(root.get("name")),
						"%" + filtersDTO.getName().toLowerCase() + "%"
					)
				);
			}
			
			if(filtersDTO.getAge()!=0) {
				predicates.add(criteriaBuilder.equal(root.get("age"),filtersDTO.getAge()));
			}
			
			
			if(filtersDTO.getWeight()!=0) {
				predicates.add(criteriaBuilder.equal(root.get("weight"),filtersDTO.getWeight()));
			}
			
			
			// seguir arreglando aca relacion con movie
			if(filtersDTO.getIdMovie()!=0) {
				Join<MovieEntity, CharacterEntity> join = root.join("movies", JoinType.INNER);
				Expression<String> moviesId = join.get("id");
				predicates.add(moviesId.in(filtersDTO.getIdMovie()));
			}
			
			
			// eliminar duplicados
			query.distinct(true);
			
			// ordenar
			criteriaBuilder.asc(root.get("name"));
			
			/*
			String orderByField = "name";
			query.orderBy(
					filtersDTO.isASC() ?
							criteriaBuilder.asc(root.get(orderByField)) :
							criteriaBuilder.desc(root.get(orderByField))
					);
			*/
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			
		};
		
	}
	
}
