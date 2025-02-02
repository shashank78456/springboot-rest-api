package com.example.springbootrestapi.controller;

import com.example.springbootrestapi.model.Todo;
import com.example.springbootrestapi.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("todos")
    public ResponseEntity<List<Todo>> getAll() {
        List<Todo> todos = todoService.getAll();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping("todos/{id}")
    public ResponseEntity<Todo> getById(@PathVariable Long id) {
        Optional<Todo> todo = todoService.getById(id);
        if (todo.isPresent()){
            return new ResponseEntity<>(todo.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("todo")
    public ResponseEntity<Todo> create(@RequestBody Todo todo) {
        Todo createdTodo = todoService.create(todo);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }

    @PutMapping("todo/{id}")
    public ResponseEntity<Todo> update(@RequestBody Todo todo, @PathVariable Long id) {
        Todo updatedTodo = todoService.update(todo, id);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }

    @DeleteMapping("todo/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        todoService.delete(id);
        return new ResponseEntity<>("Todo Deleted Successfully", HttpStatus.OK);
    }
}
