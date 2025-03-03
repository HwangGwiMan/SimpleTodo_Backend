package Backend.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import Backend.todo.dto.TodoRequestDto;
import Backend.todo.dto.TodoSaveDto;
import Backend.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public void createTodo(TodoSaveDto todoSaveDto) {
        todoRepository.createTodo(todoSaveDto);
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

    public List<TodoRequestDto> getTodosByUserId(int userId) {
        return todoRepository.getTodosByUserId(userId);
    }
} 