package Backend.common;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class AbstractDto {

    public AbstractDto() {
        this.dirtyFlag = new DirtyFlag();
    }

    // 공통 필드
    private Long id;
    private Timestamp createdAt;
//    private String updatedAt;

    // DirtyFlag 추가
    @JsonDeserialize(using = DirtyFlagDeserializer.class)
    private DirtyFlag dirtyFlag;
}
