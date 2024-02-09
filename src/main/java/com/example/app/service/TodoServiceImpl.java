package com.example.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.dao.TodoDao;
import com.example.app.domain.Todo;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	TodoDao todoDao;

	@Override
	public List<Todo> getTodoList() throws Exception {
		return todoDao.selectAll();
	}

	@Override
	public List<Todo> getTodoTitleList() throws Exception {
		return todoDao.getTitleList();
	}
	
	@Override
	public Todo getTodoById(Integer id) throws Exception {
		return todoDao.selectById(id);
	}

	@Override
	public void addTodo(Todo todo) throws Exception {
		todoDao.insert(todo);
	}

	@Override
	public void editTodo(Todo todo) throws Exception {
		todoDao.update(todo);
	}

	@Override
	public void deleteTodo(Integer id) throws Exception {
		todoDao.setDeleteById(id);
	}

	@Override
	public int getTotalPages(int numPerPage) throws Exception {
		double count = (double)todoDao.countAll();
		return (int)Math.ceil(count/numPerPage);
	}

	@Override
	public List<Todo> getTodoListPerPage(int page, int numPerPage) throws Exception {
		int offset = numPerPage * (page - 1);
		return todoDao.selectLimited(offset, numPerPage);
	}


	
	
	
}
