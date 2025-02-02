package com.example.springbootrestapi.service;

import com.example.springbootrestapi.model.Todo;
import com.example.springbootrestapi.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo create(Todo todo) {
        return todoRepository.save(todo);
    }

    public List<Todo> getAll() {
        return todoRepository.findAll();
    }

    public Optional<Todo> getById(Long id) {
        return todoRepository.findById(id);
    }

    public Todo update(Todo newTodo, Long id) {
        Optional<Todo> existingTodo = todoRepository.findById(id);
        if(existingTodo.isPresent()) {
            Todo todo = existingTodo.get();
            todo.setTitle(newTodo.getTitle());
            todo.setDescription(newTodo.getDescription());
            todo.setCompleted(newTodo.getCompleted());
            return todoRepository.save(todo);
        } else {
            throw new RuntimeException("Todo not Found");
        }
    }

    public void delete(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        if(todo.isPresent()) {
            todoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Todo not Found");
        }
    }
}
