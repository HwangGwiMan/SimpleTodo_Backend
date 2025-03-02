package Backend.user.dto;

import Backend.common.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserRequestDto extends AbstractDto {
    private String username;
    private String password;
} 