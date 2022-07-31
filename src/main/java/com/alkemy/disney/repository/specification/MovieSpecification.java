package com.alkemy.disney.repository.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alkemy.disney.dto.MovieFiltersDTO;
import com.alkemy.disney.entity.MovieEntity;

@Component
public class MovieSpecification {

	public Specification<MovieEntity> getByFilters(MovieFiltersDTO filtersDTO){
		return (root, query, criteriaBuilder) -> {
			
			List<Predicate> predicates = new ArrayList<>();
			
			if(StringUtils.hasLength(filtersDTO.getTitle())) {
				predicates.add(
					criteriaBuilder.like(
						criteriaBuilder.lower(root.get("title")),
						"%" + filtersDTO.getTitle().toLowerCase() + "%"
					)
				);
			}
			
			if(filtersDTO.getGenreId()!=0) {
				predicates.add(criteriaBuilder.equal(root.get("genreId"),filtersDTO.getGenreId()));
			}
			
			// eliminar duplicados
			query.distinct(true);
			// ordenar
			if (filtersDTO.getOrder().toLowerCase().equals("desc")) {
				query.orderBy(criteriaBuilder.desc(root.get("releaseDate")));
			} else {
				query.orderBy(criteriaBuilder.asc(root.get("releaseDate")));
			}
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
						
			
		};
		
	}
	
}
