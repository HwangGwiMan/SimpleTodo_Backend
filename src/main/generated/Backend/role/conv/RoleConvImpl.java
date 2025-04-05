package Backend.role.conv;

import Backend.role.dto.RoleDto;
import Backend.role.entity.RoleEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-05T22:29:43+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.4.1 (Eclipse Adoptium)"
)
@Component
public class RoleConvImpl implements RoleConv {

    @Override
    public RoleDto toDto(RoleEntity arg0) {
        if ( arg0 == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setId( arg0.getId() );
        roleDto.setCreatedAt( arg0.getCreatedAt() );
        roleDto.setDirtyFlag( arg0.getDirtyFlag() );
        roleDto.setUrl( arg0.getUrl() );
        roleDto.setDescript( arg0.getDescript() );

        return roleDto;
    }

    @Override
    public List<RoleDto> toDtoList(List<RoleEntity> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<RoleDto> list = new ArrayList<RoleDto>( arg0.size() );
        for ( RoleEntity roleEntity : arg0 ) {
            list.add( toDto( roleEntity ) );
        }

        return list;
    }

    @Override
    public List<RoleEntity> toEntityList(List<RoleDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<RoleEntity> list = new ArrayList<RoleEntity>( arg0.size() );
        for ( RoleDto roleDto : arg0 ) {
            list.add( toEntity( roleDto ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromDto(RoleDto arg0, RoleEntity arg1) {
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
        if ( arg0.getUrl() != null ) {
            arg1.setUrl( arg0.getUrl() );
        }
        if ( arg0.getDescript() != null ) {
            arg1.setDescript( arg0.getDescript() );
        }
    }

    @Override
    public RoleEntity toEntity(RoleDto dto) {
        if ( dto == null ) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setId( dto.getId() );
        roleEntity.setCreatedAt( dto.getCreatedAt() );
        roleEntity.setUrl( dto.getUrl() );
        roleEntity.setDescript( dto.getDescript() );

        return roleEntity;
    }
}
