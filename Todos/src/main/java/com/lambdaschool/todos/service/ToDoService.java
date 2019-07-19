package com.lambdaschool.todos.service;

import com.lambdaschool.todos.model.Todo;

import java.util.List;

public interface ToDoService
{
    List<Todo> getAllTodos();

    Todo save(Todo todo);
}
