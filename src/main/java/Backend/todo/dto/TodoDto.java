package Backend.todo.dto;

import Backend.common.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = true)
public class TodoDto extends AbstractDto {
    private Long userId;
    private String content;
    private Timestamp startDateTime;
    private Timestamp endDateTime;
    private Timestamp alarmDateTime;
}
