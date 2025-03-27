package Backend.todo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Backend.todo.dto.TodoSaveDto;
import Backend.todo.service.TodoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping("/save")
    public void createTodo(@RequestBody TodoSaveDto todoSaveDto) {
        todoService.createTodo(todoSaveDto);
    }

    @DeleteMapping("/delete")
    public void deleteTodo(@RequestBody TodoRequestDto todoRequestDto) {
        todoService.deleteTodo(todoRequestDto.getId());
    }
        
    @PostMapping("/get")
    public TodoRequestDto getTodo(@RequestBody TodoRequestDto todoRequestDto) {
        return todoService.getTodo(todoRequestDto.getId());
    }

    @GetMapping("/get/{userId}")
    public List<TodoRequestDto> getTodosByUserId(@PathVariable int userId) {
        return todoService.getTodosByUserId(userId);
    }
} 