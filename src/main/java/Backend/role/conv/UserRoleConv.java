package Backend.role.conv;

import Backend.common.mapper.GenericMapper;
import Backend.role.dto.RoleDto;
import Backend.role.dto.UserRoleDto;
import Backend.role.entity.RoleEntity;
import Backend.role.entity.UserRoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserRoleConv extends GenericMapper<UserRoleDto, UserRoleEntity> {
    @Mapping(target = "dirtyFlag", ignore = true)
    @Override
    UserRoleEntity toEntity(UserRoleDto dto);
}
