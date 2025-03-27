package Backend.todo.service;

import java.util.Optional;
import java.util.List;

import Backend.common.DirtyFlag;
import Backend.todo.conv.TodoConv;
import Backend.todo.dto.TodoDto;
import Backend.todo.dto.TodoSaveDto;
import Backend.todo.repository.TodoRepository;
import Backend.todo.entity.TodoEntity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final TodoConv todoConv;

    @Transactional
    public void save(TodoSaveDto dto) {
        Optional.ofNullable(dto.getTodoList())
            .ifPresent(this::processTodoList);
    }

    private void processTodoList(List<TodoDto> todoList) {
        for (TodoDto todo : todoList) {
            try {
                processTodo(todo);
            } catch (Exception e) {
                log.error("Todo 처리 중 오류 발생: {}", e.getMessage(), e);
                throw new RuntimeException("Todo 처리 중 오류가 발생했습니다.", e);
            }
        }
    }

    private void processTodo(TodoDto todo) {
        if (todo == null || todo.getDirtyFlag() == null) {
            log.warn("유효하지 않은 Todo 데이터: {}", todo);
            return;
        }

        DirtyFlag dirtyFlag = todo.getDirtyFlag();
        TodoEntity entity = todoConv.toEntity(todo);

        switch (dirtyFlag.getFlag()) {
            case INSERT, UPDATE -> todoRepository.save(entity);
            case DELETE -> todoRepository.delete(entity);
        }
    }

//    public TodoRequestDto getTodo(Long id) {
//        // return todoRepository.getTodo(id);
//        return null;
//    }
//
//    public List<TodoRequestDto> getTodosByUserId(int userId) {
//        // return todoRepository.getTodosByUserId(userId);
//        return null;
//    }
} 