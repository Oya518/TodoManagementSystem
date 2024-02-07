package com.example.app.domain;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Todo {
	
	private Integer id;
	@NotBlank
	@Size(max = 45)
	private String title;
	
	@Size(max = 300)
	private String note;
	private LocalDate limit;
	private Integer important;
}
