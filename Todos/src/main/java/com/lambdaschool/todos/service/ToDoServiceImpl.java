package com.lambdaschool.todos.service;

import com.lambdaschool.todos.model.Todo;
import com.lambdaschool.todos.model.User;
import com.lambdaschool.todos.repository.ToDoRepository;
import com.lambdaschool.todos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "todoService")
public class ToDoServiceImpl implements ToDoService
{
    @Autowired
    ToDoRepository todoRepo;

    @Autowired
    UserRepository userrepos;

    @Override
    public List<Todo> getAllTodos()
    {
        List<Todo> list = new ArrayList<>();
        todoRepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Todo getTodoById(long id) throws EntityNotFoundException
    {
        return todoRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Transactional
    @Override
    public Todo save(Todo todo, String name) throws EntityNotFoundException
    {
        User currentUser = userrepos.findByUsername(name);

        if (currentUser == null)
        {
            throw new EntityNotFoundException(name);
        }

        Todo newTodo = new Todo();
        newTodo.setDescription(todo.getDescription());
        newTodo.setUser(todo.getUser());


        return todoRepo.save(newTodo);
    }

    @Transactional
    @Override
    public Todo update(Todo todo, long id) throws EntityNotFoundException
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userrepos.findByUsername(authentication.getName());
        System.out.println(currentUser);
        Todo updateTodo = getTodoById(id);

        // No current user? Error!
        if (currentUser == null)
        {
            throw new EntityNotFoundException(authentication.getName());
        }

        // If there is a todoItem in the database, does it belong to our user? No? Error!
        if (currentUser.getUserid() != updateTodo.getUser().getUserid())
        {
            throw new EntityNotFoundException(Long.toString(id) + " does not belong to current user");
        }
        if (todo.getDescription() != null)
        {
            updateTodo.setDescription(todo.getDescription());
        }
        updateTodo.setCompleted(todo.isCompleted());

        return todoRepo.save(updateTodo);
    }
}
