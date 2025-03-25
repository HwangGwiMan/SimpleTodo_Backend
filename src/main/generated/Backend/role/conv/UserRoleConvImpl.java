package Backend.role.conv;

import Backend.role.dto.UserRoleDto;
import Backend.role.entity.UserRoleEntity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-25T22:22:22+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.4.1 (Eclipse Adoptium)"
)
public class UserRoleConvImpl implements UserRoleConv {

    @Override
    public UserRoleDto toDto(UserRoleEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UserRoleDto userRoleDto = new UserRoleDto();

        userRoleDto.setId( entity.getId() );
        if ( entity.getCreatedAt() != null ) {
            userRoleDto.setCreatedAt( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( entity.getCreatedAt() ) );
        }
        userRoleDto.setRole_id( entity.getRole_id() );
        userRoleDto.setUser_id( entity.getUser_id() );

        return userRoleDto;
    }

    @Override
    public UserRoleEntity toEntity(UserRoleDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserRoleEntity userRoleEntity = new UserRoleEntity();

        userRoleEntity.setId( dto.getId() );
        if ( dto.getCreatedAt() != null ) {
            userRoleEntity.setCreatedAt( LocalDateTime.parse( dto.getCreatedAt() ) );
        }
        userRoleEntity.setRole_id( dto.getRole_id() );
        userRoleEntity.setUser_id( dto.getUser_id() );

        return userRoleEntity;
    }

    @Override
    public List<UserRoleDto> toDtoList(List<UserRoleEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UserRoleDto> list = new ArrayList<UserRoleDto>( entityList.size() );
        for ( UserRoleEntity userRoleEntity : entityList ) {
            list.add( toDto( userRoleEntity ) );
        }

        return list;
    }

    @Override
    public List<UserRoleEntity> toEntityList(List<UserRoleDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<UserRoleEntity> list = new ArrayList<UserRoleEntity>( dtoList.size() );
        for ( UserRoleDto userRoleDto : dtoList ) {
            list.add( toEntity( userRoleDto ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromDto(UserRoleDto dto, UserRoleEntity entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getCreatedAt() != null ) {
            entity.setCreatedAt( LocalDateTime.parse( dto.getCreatedAt() ) );
        }
        if ( dto.getRole_id() != null ) {
            entity.setRole_id( dto.getRole_id() );
        }
        if ( dto.getUser_id() != null ) {
            entity.setUser_id( dto.getUser_id() );
        }
    }
}
