package com.alkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;

@Getter // automatizar getters con lombok
@Setter // automatizar setters  con lombok

public class MovieBasicDTO {
	private String img;
	private String title;
	private String releaseDate;
}
