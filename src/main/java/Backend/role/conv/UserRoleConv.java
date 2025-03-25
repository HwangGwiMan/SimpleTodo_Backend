package Backend.role.conv;

import Backend.common.mapper.GenericMapper;
import Backend.role.dto.UserRoleDto;
import Backend.role.entity.UserRoleEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserRoleConv extends GenericMapper<UserRoleDto, UserRoleEntity> {
}
