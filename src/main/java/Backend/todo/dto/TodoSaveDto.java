package Backend.todo.dto;

import java.util.List;

import lombok.Data;

@Data
public class TodoSaveDto {
    private List<TodoDto> todoList;
}
