package Backend.todo.dto;

import Backend.common.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TodoRequestDto extends AbstractDto {
    private String title;
    private String description;
    private int createdBy;
    private boolean completed;
} 