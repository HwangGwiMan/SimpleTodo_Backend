package Backend.common;

import lombok.Data;

@Data
public class AbstractDto {
    private int id;
    private String createdAt;
    private String updatedAt;
}
