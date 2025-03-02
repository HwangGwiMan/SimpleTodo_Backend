package Backend.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import Backend.todo.dto.TodoRequestDto;
import Backend.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public void createTodo(TodoRequestDto todoRequestDto) {
        todoRepository.createTodo(todoRequestDto);
    }

    public void updateTodo(TodoRequestDto todoRequestDto) {
        todoRepository.updateTodo(todoRequestDto);
    }

    public void deleteTodo(int id) {
        todoRepository.deleteTodo(id);
    }

    public TodoRequestDto getTodo(int id) {
        return todoRepository.getTodo(id);
    }

    public List<TodoRequestDto> getTodos(int createdBy) {
        return todoRepository.getTodos(createdBy);
    }
} 