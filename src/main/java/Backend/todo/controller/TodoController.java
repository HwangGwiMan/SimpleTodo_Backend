package Backend.todo.controller;

import java.util.List;
import java.util.Map;

import Backend.todo.entity.TodoEntity;
import org.springframework.http.ResponseEntity;
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
    public void save(@RequestBody TodoSaveDto todoSaveDto) {
        todoService.save(todoSaveDto);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<Map<String, Object>> getTodoListByUserId(@PathVariable("userId") Long userId) {
        return todoService.getTodoListByUserId(userId);
    }
} 