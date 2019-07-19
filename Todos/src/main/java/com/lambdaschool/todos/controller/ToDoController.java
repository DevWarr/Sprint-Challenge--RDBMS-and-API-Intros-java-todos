package com.lambdaschool.todos.controller;

import com.lambdaschool.todos.model.Todo;
import com.lambdaschool.todos.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class ToDoController
{
    @Autowired
    ToDoService todoService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/all", produces = {"application/json"})
    public ResponseEntity<?> getAllTodos()
    {
        return new ResponseEntity<>(todoService.getAllTodos(), HttpStatus.OK);
    }

    @PutMapping(value = "todoid/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> updateTodo(@RequestBody Todo updateTodo, @PathVariable long id)
    {

    }
}
