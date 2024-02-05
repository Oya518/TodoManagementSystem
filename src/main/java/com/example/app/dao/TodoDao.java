package com.example.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.app.domain.Todo;

@Mapper
public interface TodoDao {
	
	List<Todo> selectAll() throws Exception; 
	
	Todo selectById(Integer id) throws Exception;
	
	void setDeleteById() throws Exception;
	
	void insert(Todo todo) throws Exception;
	
	void update(Todo todo) throws Exception;
	
	//pagenation
	List<Todo> selectLimited(@Param("offset")int offset, @Param("num")int num) throws Exception;
	
	long countActive() throws Exception;
	
	
}
