package com.example.app.domain;

import java.util.Date;

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
	private Date limit;
	private Integer important;
}
