package Backend.user.conv;


import Backend.common.mapper.GenericMapper;
import Backend.user.dto.UserDto;
import Backend.user.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserConv extends GenericMapper<UserDto, UserEntity> {
}
