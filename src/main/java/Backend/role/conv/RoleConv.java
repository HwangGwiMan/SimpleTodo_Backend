package Backend.role.conv;

import Backend.common.mapper.GenericMapper;
import Backend.role.dto.RoleDto;
import Backend.role.entity.RoleEntity;
import Backend.todo.dto.TodoDto;
import Backend.todo.entity.TodoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleConv extends GenericMapper<RoleDto, RoleEntity> {
    @Mapping(target = "dirtyFlag", ignore = true)
    @Override
    RoleEntity toEntity(RoleDto dto);
}
