package Backend.common;

import lombok.Data;

@Data
public class AbstractDto {

    public AbstractDto() {
        this.dirtyFlag = new DirtyFlag();
    }

    // 공통 필드
    private Long id;
    private String createdAt;
    private String updatedAt;

    // DirtyFlag 추가
    private DirtyFlag dirtyFlag;
}
