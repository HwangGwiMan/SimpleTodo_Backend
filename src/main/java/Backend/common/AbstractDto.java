package Backend.common;

import lombok.Data;

@Data
public class AbstractDto {
    private Long id;
    private String createdAt;
    private String updatedAt;
}
