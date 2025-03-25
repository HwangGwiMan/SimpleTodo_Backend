package Backend.role.conv;

import Backend.common.mapper.GenericMapper;
import Backend.role.dto.RoleDto;
import Backend.role.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleConv extends GenericMapper<RoleDto, RoleEntity> {
}
