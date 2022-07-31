package com.alkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MovieFiltersDTO {
	
	private String title;
	private Long genreId;
	private String order;
	
	public MovieFiltersDTO(String title,Long genreId, String order) {
		this.title = title;
		this.genreId = genreId;
		this.order = order;
	}
}
