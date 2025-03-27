package Backend.todo.conv;


import Backend.common.mapper.GenericMapper;
import Backend.todo.dto.TodoDto;
import Backend.todo.entity.TodoEntity;
import Backend.user.dto.UserDto;
import Backend.user.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TodoConv extends GenericMapper<TodoDto, TodoEntity> {
}
