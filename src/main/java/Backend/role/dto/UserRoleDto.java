package Backend.role.dto;

import Backend.common.AbstractDto;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserRoleDto extends AbstractDto {
    private Long roleId;
    private Long userId;
}
