package com.lcwd.todo.controllers;

import com.lcwd.todo.models.Todo;
import com.lcwd.todo.services.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Random;


//this means this class will deal with all the web requests
@RestController
@RequestMapping("/todos")
public class TodoController {
    Logger logger = LoggerFactory.getLogger(TodoController.class);

       @Autowired
       private TodoService todoService;
       Random random =new Random();
       //create
       @PostMapping
       public ResponseEntity<Todo> createTodoHandler(@RequestBody Todo todo){
           //create todo
           int id=random.nextInt(999999);
           todo.setId(id);
           //create Date with system default current date
           Date currentDate=new Date();
           logger.info("current date: {} ",currentDate);
           todo.setAddedDate(currentDate);
           logger.info("create Todo");
           //call service to create todo

           Todo todo1 = todoService.createTodo(todo);
           return new ResponseEntity<>(todo1, HttpStatus.CREATED);
       }
       @GetMapping
       public ResponseEntity<List<Todo>> getAllTodoHAndler(){
           List<Todo> allTodos=todoService.getAllTodos();
           return new ResponseEntity<>(allTodos,HttpStatus.OK);
       }
       @GetMapping("/{todoId}")
       public ResponseEntity<Todo> getSingleTodoHandler(@PathVariable int todoId){
           Todo todo=todoService.getTodo(todoId);
           return ResponseEntity.ok(todo);
       }
       //update todo
       @PutMapping("/{todoId}")
       public ResponseEntity<Todo> updateTodoHandler(@RequestBody Todo todoWithNewDetails,@PathVariable int todoId){
           logger.info("Updating Todo with ID: {}", todoId);
        Todo todo= todoService.updateTodo(todoId,todoWithNewDetails);
        return ResponseEntity.ok(todo);
    }
    //delete todo
    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable int todoId){
        logger.info("Deleting Todo with ID: {}", todoId);
        todoService.deleteTodo(todoId);
        return new ResponseEntity<>("Todo successfully deleted", HttpStatus.OK);
    }

}
