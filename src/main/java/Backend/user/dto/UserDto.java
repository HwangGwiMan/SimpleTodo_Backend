package Backend.user.dto;

import Backend.common.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDto extends AbstractDto {
    private String username;
    private String password;
}
