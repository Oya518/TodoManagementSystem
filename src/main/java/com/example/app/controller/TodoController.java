package com.example.app.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.domain.Todo;
import com.example.app.service.TodoService;

import jakarta.validation.Valid;

@Controller
public class TodoController {
	
	private static final int NUM_PER_PAGE = 5;
	
	@Autowired
	TodoService todoService;
	
	@GetMapping("/listTodo")
	public String listTodo(
			@RequestParam(name = "page", defaultValue = "1")Integer page,
			Model model
			) throws Exception {
		
		//全体のページ数
		int totalPages = todoService.getTotalPages(NUM_PER_PAGE);
		model.addAttribute("totalPages", totalPages);
		//現在のページ番号
		model.addAttribute("currentPage", page);
		model.addAttribute("todoList",todoService.getTodoListPerPage(page, NUM_PER_PAGE));
		
		// 日付チェック用
		LocalDate currentDate = LocalDate.now();
		model.addAttribute("currentDate", currentDate);

		
		//todoリスト
		model.addAttribute("titleList",todoService.getTodoTitleList());
		
		return "listTodo.html";
	}
	
	@GetMapping("/show/{id}")
	public String showTodo(
			@PathVariable Integer id,
			Model model) throws Exception {
		model.addAttribute("todo", todoService.getTodoById(id));
		return "showTodo.html";
	}
	
	@GetMapping("/editTodo/{id}")
	public String editTodo(
			@PathVariable Integer id,
			Model model) throws Exception{
		model.addAttribute("todo",todoService.getTodoById(id));
		return "editTodo.html";
	}
	
	@PostMapping("/editTodo/{id}")
	public String editTodo(
			@PathVariable Integer id,
			@Valid Todo todo,
			Errors errors,
			RedirectAttributes redirectAttributes,
			Model model) throws Exception {
		
		if(errors.hasErrors()) {
			model.addAttribute("","");
			return "/editTodo";
		}
		todoService.editTodo(todo);
		redirectAttributes.addFlashAttribute("message","Todoを編集しました");
		return "redirect:/listTodo";
	}
	
	@GetMapping("/addTodo")
	public String addTodo(
			Model model) throws Exception{
		model.addAttribute("todo",new Todo());
		return "addTodo.html";
	}
	
	@PostMapping("/addTodo")
	public String addTodo(
			@Valid Todo todo,
			Errors errors,
			RedirectAttributes redirectAttributes,
			Model model
			) throws Exception {
		
		if(errors.hasErrors()) {
			model.addAttribute("","");
			return "/addTodo";
		}
		
		todoService.addTodo(todo);
		redirectAttributes.addFlashAttribute("message","Todoを追加しました");
		return "redirect:/listTodo";
	}
	
	@GetMapping("/deleteTodo/{id}")
	public String deleteTodo(
			@PathVariable Integer id,
			RedirectAttributes redirectAttributes,
			Model model) throws Exception {
		
		todoService.deleteTodo(id);
		redirectAttributes.addFlashAttribute("message","削除しました");
		return "redirect:/listTodo";
	}
}
