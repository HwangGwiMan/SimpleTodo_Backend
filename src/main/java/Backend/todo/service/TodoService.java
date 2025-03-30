package Backend.todo.service;

import java.util.Map;
import java.util.Optional;
import java.util.List;

import Backend.common.AbstractDto;
import Backend.common.AbstractEntity;
import Backend.common.DirtyFlag;
import Backend.common.mapper.GenericMapper;
import Backend.common.util.CommonJPA;
import Backend.config.ResponseCode;
import Backend.todo.conv.TodoConv;
import Backend.todo.dto.TodoDto;
import Backend.todo.dto.TodoSaveDto;
import Backend.todo.dto.TodoSearchDto;
import Backend.todo.repository.TodoRepository;
import Backend.todo.entity.TodoEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
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

    /**
     * Todo 저장
     * 
     * @param dto
     */
    @Transactional
    public void save(TodoSaveDto dto) {
        Optional.ofNullable(dto.getTodoList())
            .ifPresent(todoDtoList -> CommonJPA.saveEntity(todoDtoList, todoRepository, todoConv));
    }

    /**
     * 유저 아이디로 Todo 조회
     * 
     * @param userId
     * @return
     */
    @Transactional(readOnly = true)
    public ResponseEntity<Map<String, Object>> getTodoListByUserId(Long userId) {
        List<TodoEntity> todoList = todoRepository.findByUserId(userId);
        return ResponseEntity.status(200).body(Map.of("code", ResponseCode.SUCCESS.getCode(), "message", ResponseCode.SUCCESS.getMessage(), "data", todoList));
    }

} 