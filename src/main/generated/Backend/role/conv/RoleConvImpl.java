package Backend.role.conv;

import Backend.role.dto.RoleDto;
import Backend.role.entity.RoleEntity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-25T22:22:22+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.4.1 (Eclipse Adoptium)"
)
@Component
public class RoleConvImpl implements RoleConv {

    @Override
    public RoleDto toDto(RoleEntity entity) {
        if ( entity == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setId( entity.getId() );
        if ( entity.getCreatedAt() != null ) {
            roleDto.setCreatedAt( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( entity.getCreatedAt() ) );
        }

        return roleDto;
    }

    @Override
    public RoleEntity toEntity(RoleDto dto) {
        if ( dto == null ) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setId( dto.getId() );
        if ( dto.getCreatedAt() != null ) {
            roleEntity.setCreatedAt( LocalDateTime.parse( dto.getCreatedAt() ) );
        }

        return roleEntity;
    }

    @Override
    public List<RoleDto> toDtoList(List<RoleEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<RoleDto> list = new ArrayList<RoleDto>( entityList.size() );
        for ( RoleEntity roleEntity : entityList ) {
            list.add( toDto( roleEntity ) );
        }

        return list;
    }

    @Override
    public List<RoleEntity> toEntityList(List<RoleDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<RoleEntity> list = new ArrayList<RoleEntity>( dtoList.size() );
        for ( RoleDto roleDto : dtoList ) {
            list.add( toEntity( roleDto ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromDto(RoleDto dto, RoleEntity entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getCreatedAt() != null ) {
            entity.setCreatedAt( LocalDateTime.parse( dto.getCreatedAt() ) );
        }
    }
}
