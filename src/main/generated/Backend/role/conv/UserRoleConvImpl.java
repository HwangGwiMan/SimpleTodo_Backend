package Backend.role.conv;

import Backend.role.dto.UserRoleDto;
import Backend.role.entity.UserRoleEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-05T22:29:43+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.4.1 (Eclipse Adoptium)"
)
public class UserRoleConvImpl implements UserRoleConv {

    @Override
    public UserRoleDto toDto(UserRoleEntity arg0) {
        if ( arg0 == null ) {
            return null;
        }

        UserRoleDto userRoleDto = new UserRoleDto();

        userRoleDto.setId( arg0.getId() );
        userRoleDto.setCreatedAt( arg0.getCreatedAt() );
        userRoleDto.setDirtyFlag( arg0.getDirtyFlag() );
        userRoleDto.setRoleId( arg0.getRoleId() );
        userRoleDto.setUserId( arg0.getUserId() );

        return userRoleDto;
    }

    @Override
    public List<UserRoleDto> toDtoList(List<UserRoleEntity> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<UserRoleDto> list = new ArrayList<UserRoleDto>( arg0.size() );
        for ( UserRoleEntity userRoleEntity : arg0 ) {
            list.add( toDto( userRoleEntity ) );
        }

        return list;
    }

    @Override
    public List<UserRoleEntity> toEntityList(List<UserRoleDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<UserRoleEntity> list = new ArrayList<UserRoleEntity>( arg0.size() );
        for ( UserRoleDto userRoleDto : arg0 ) {
            list.add( toEntity( userRoleDto ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromDto(UserRoleDto arg0, UserRoleEntity arg1) {
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
        if ( arg0.getRoleId() != null ) {
            arg1.setRoleId( arg0.getRoleId() );
        }
        if ( arg0.getUserId() != null ) {
            arg1.setUserId( arg0.getUserId() );
        }
    }

    @Override
    public UserRoleEntity toEntity(UserRoleDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserRoleEntity userRoleEntity = new UserRoleEntity();

        userRoleEntity.setId( dto.getId() );
        userRoleEntity.setCreatedAt( dto.getCreatedAt() );
        userRoleEntity.setRoleId( dto.getRoleId() );
        userRoleEntity.setUserId( dto.getUserId() );

        return userRoleEntity;
    }
}
