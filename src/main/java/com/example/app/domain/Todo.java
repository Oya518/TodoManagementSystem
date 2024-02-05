package com.example.app.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Todo {
	private Integer id;
	private String title;
	private String note;
	private Date limit;
	private Integer important;
}
