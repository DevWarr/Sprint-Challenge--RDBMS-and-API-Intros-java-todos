package com.lambdaschool.todos.service;

import com.lambdaschool.todos.model.Todo;
import com.lambdaschool.todos.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "todoService")
public class ToDoServiceImpl implements ToDoService
{
    @Autowired
    ToDoRepository todoRepo;

    @Override
    public List<Todo> getAllTodos()
    {
        List<Todo> list = new ArrayList<>();
        todoRepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public Todo save(Todo todo)
    {
        Todo newTodo = new Todo();
        newTodo.setDescription(todo.getDescription());
        newTodo.setDateStarted(todo.getDateStarted());
        newTodo.setUser(todo.getUser());


        return todoRepo.save(newTodo);
    }
}
