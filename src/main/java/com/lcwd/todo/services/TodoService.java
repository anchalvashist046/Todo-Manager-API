package com.lcwd.todo.services;

import com.lcwd.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoService {
    Logger logger = LoggerFactory.getLogger(TodoService.class);
    List<Todo> todos= new ArrayList<>();


    //create todo method
    public Todo createTodo(Todo todo){
        //create..
        //change the logic
        todos.add(todo);
        logger.info("Todos {}" ,this.todos);
        return todo;
    }

    public List<Todo> getAllTodos() {
        return todos;
    }

    public Todo getTodo(int todoId) {
        Todo todo=todos.stream().filter(t -> todoId== t.getId()).findAny().get();
        logger.info("TODO : {}",todo );
         return todo;
    }
    public Todo updateTodo(int todoId,Todo todo){
        List<Todo> newUpdateList = todos.stream().map(t -> {
            if (t.getId() == todoId) {
                t.setTitle(todo.getTitle());
                t.setContent(todo.getContent());
                t.setStatus(todo.getStatus());
                return t;
            } else {
                return t;
            }
        }).collect(Collectors.toList());

        todos = newUpdateList;
        todo.setId(todoId);
        return todo;
        //return getTodo(todoId);
    }
    // Delete a todo
    public void deleteTodo(int todoId) {
        todos = todos.stream().filter(t -> t.getId() != todoId).collect(Collectors.toList());
        logger.info("Todo with ID: {} has been deleted", todoId);
    }
}
