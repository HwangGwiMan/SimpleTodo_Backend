package Backend.todo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Backend.todo.dto.TodoRequestDto;
import Backend.todo.service.TodoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping("/create")
    public void createTodo(@RequestBody TodoRequestDto todoRequestDto) {
        todoService.createTodo(todoRequestDto);
    }

    @PutMapping("/update")
    public void updateTodo(@RequestBody TodoRequestDto todoRequestDto) {
        todoService.updateTodo(todoRequestDto);
    }

    @DeleteMapping("/delete")
    public void deleteTodo(@RequestBody TodoRequestDto todoRequestDto) {
        todoService.deleteTodo(todoRequestDto.getId());
    }

    @GetMapping("/get")
    public TodoRequestDto getTodo(@RequestBody TodoRequestDto todoRequestDto) {
        return todoService.getTodo(todoRequestDto.getId());
    }
} 