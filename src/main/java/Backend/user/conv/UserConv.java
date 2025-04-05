package Backend.user.conv;


import Backend.common.mapper.GenericMapper;
import Backend.todo.dto.TodoDto;
import Backend.todo.entity.TodoEntity;
import Backend.user.dto.UserDto;
import Backend.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserConv extends GenericMapper<UserDto, UserEntity> {
    @Mapping(target = "dirtyFlag", ignore = true)
    @Override
    UserEntity toEntity(UserDto dto);
}
