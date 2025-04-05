package Backend.todo.conv;

import Backend.todo.dto.TodoDto;
import Backend.todo.entity.TodoEntity;
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
public class TodoConvImpl implements TodoConv {

    @Override
    public TodoDto toDto(TodoEntity arg0) {
        if ( arg0 == null ) {
            return null;
        }

        TodoDto todoDto = new TodoDto();

        todoDto.setId( arg0.getId() );
        todoDto.setCreatedAt( arg0.getCreatedAt() );
        todoDto.setDirtyFlag( arg0.getDirtyFlag() );
        todoDto.setUserId( arg0.getUserId() );
        todoDto.setContent( arg0.getContent() );
        todoDto.setStartDateTime( arg0.getStartDateTime() );
        todoDto.setEndDateTime( arg0.getEndDateTime() );
        todoDto.setAlarmDateTime( arg0.getAlarmDateTime() );

        return todoDto;
    }

    @Override
    public List<TodoDto> toDtoList(List<TodoEntity> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<TodoDto> list = new ArrayList<TodoDto>( arg0.size() );
        for ( TodoEntity todoEntity : arg0 ) {
            list.add( toDto( todoEntity ) );
        }

        return list;
    }

    @Override
    public List<TodoEntity> toEntityList(List<TodoDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<TodoEntity> list = new ArrayList<TodoEntity>( arg0.size() );
        for ( TodoDto todoDto : arg0 ) {
            list.add( toEntity( todoDto ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromDto(TodoDto arg0, TodoEntity arg1) {
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
        if ( arg0.getUserId() != null ) {
            arg1.setUserId( arg0.getUserId() );
        }
        if ( arg0.getContent() != null ) {
            arg1.setContent( arg0.getContent() );
        }
        if ( arg0.getStartDateTime() != null ) {
            arg1.setStartDateTime( arg0.getStartDateTime() );
        }
        if ( arg0.getEndDateTime() != null ) {
            arg1.setEndDateTime( arg0.getEndDateTime() );
        }
        if ( arg0.getAlarmDateTime() != null ) {
            arg1.setAlarmDateTime( arg0.getAlarmDateTime() );
        }
    }

    @Override
    public TodoEntity toEntity(TodoDto dto) {
        if ( dto == null ) {
            return null;
        }

        TodoEntity todoEntity = new TodoEntity();

        todoEntity.setId( dto.getId() );
        todoEntity.setCreatedAt( dto.getCreatedAt() );
        todoEntity.setUserId( dto.getUserId() );
        todoEntity.setContent( dto.getContent() );
        todoEntity.setStartDateTime( dto.getStartDateTime() );
        todoEntity.setEndDateTime( dto.getEndDateTime() );
        todoEntity.setAlarmDateTime( dto.getAlarmDateTime() );

        return todoEntity;
    }
}
