package Backend.todo.conv;

import Backend.todo.dto.TodoDto;
import Backend.todo.entity.TodoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-30T19:25:56+0900",
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

        return todoDto;
    }

    @Override
    public TodoEntity toEntity(TodoDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        TodoEntity todoEntity = new TodoEntity();

        todoEntity.setId( arg0.getId() );

        return todoEntity;
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
    }
}
