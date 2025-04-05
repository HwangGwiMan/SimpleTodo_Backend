package Backend.user.conv;

import Backend.user.dto.UserDto;
import Backend.user.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-05T22:29:44+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.4.1 (Eclipse Adoptium)"
)
@Component
public class UserConvImpl implements UserConv {

    @Override
    public UserDto toDto(UserEntity arg0) {
        if ( arg0 == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( arg0.getId() );
        userDto.setCreatedAt( arg0.getCreatedAt() );
        userDto.setDirtyFlag( arg0.getDirtyFlag() );
        userDto.setUsername( arg0.getUsername() );
        userDto.setPassword( arg0.getPassword() );

        return userDto;
    }

    @Override
    public List<UserDto> toDtoList(List<UserEntity> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( arg0.size() );
        for ( UserEntity userEntity : arg0 ) {
            list.add( toDto( userEntity ) );
        }

        return list;
    }

    @Override
    public List<UserEntity> toEntityList(List<UserDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<UserEntity> list = new ArrayList<UserEntity>( arg0.size() );
        for ( UserDto userDto : arg0 ) {
            list.add( toEntity( userDto ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromDto(UserDto arg0, UserEntity arg1) {
        if ( arg0 == null ) {
            return;
        }

        if ( arg0.getId() != null ) {
            arg1.setId( arg0.getId() );
        }
        if ( arg0.getCreatedAt() != null ) {
            arg1.setCreatedAt( arg0.getCreatedAt() );
        }
        if ( arg0.getDirtyFlag() != null ) {
            arg1.setDirtyFlag( arg0.getDirtyFlag() );
        }
        if ( arg0.getUsername() != null ) {
            arg1.setUsername( arg0.getUsername() );
        }
        if ( arg0.getPassword() != null ) {
            arg1.setPassword( arg0.getPassword() );
        }
    }

    @Override
    public UserEntity toEntity(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( dto.getId() );
        userEntity.setCreatedAt( dto.getCreatedAt() );
        userEntity.setUsername( dto.getUsername() );
        userEntity.setPassword( dto.getPassword() );

        return userEntity;
    }
}
