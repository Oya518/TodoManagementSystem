package com.example.app.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Member {
	private Integer id;
	
	@NotBlank
	@Size(max = 45)
	private String name;
}
