package com.lambdaschool.todos.service;

import com.lambdaschool.todos.model.Todo;

import java.util.List;

public interface ToDoService
{
    List<Todo> getAllTodos();

    Todo getTodoById(long id);

    Todo save(Todo todo, String name);

    Todo update(Todo todo, long id);
}
