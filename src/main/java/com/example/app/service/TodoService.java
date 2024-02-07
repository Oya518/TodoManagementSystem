package com.example.app.service;

import java.util.List;

import com.example.app.domain.Todo;

public interface TodoService {
	
	//すべて取得
	List<Todo> getTodoList() throws Exception;
	
	//タイトル表示
	List<Todo> getTodoTitleList() throws Exception;
	
	//ID取得
	public Todo getTodoById(Integer id) throws Exception;
	
	//新規作成
	void addTodo(Todo todo) throws Exception;
}
