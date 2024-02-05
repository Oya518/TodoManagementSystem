package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodoController {
	private static final int NUM_PER_PAGE = 10;
	
	@GetMapping("/listTodo")
	public String listTodo(
			@RequestParam(name = "page", defaultValue = "1")Integer page,
			Model model
			) throws Exception {
		//全体のページ数
		
		//現在のページ番号
		
		//todoリスト
		model.addAttribute("","");
		
		return "todo.html";
	}
}
