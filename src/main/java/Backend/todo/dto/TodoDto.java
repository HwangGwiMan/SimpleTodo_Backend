package Backend.todo.dto;

import Backend.common.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = true)
public class TodoDto extends AbstractDto {
    private Long user_id;
    private String content;
    private Timestamp start_date_time;
    private Timestamp end_date_time;
    private Timestamp AlarmDateTime;
}
