package Backend.common;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 객체의 변경 상태를 추적하는 DirtyFlag 클래스
 * I: 입력(Insert)
 * D: 삭제(Delete)
 * U: 수정(Update)
 */
public final class DirtyFlag {
    /**
     * 객체의 변경 상태를 나타내는 enum
     */
    @Getter
    public enum Flag {
        INSERT("I"),    // 입력
        DELETE("D"),    // 삭제
        UPDATE("U");    // 수정

        private final String value;

        Flag(String value) {
            this.value = value;
        }

        public static Flag fromValue(String value) {
            for (Flag flag : Flag.values()) {
                if (flag.value.equals(value)) {
                    return flag;
                }
            }
            return null;
        }
    }

    @Getter
    private Flag flag;
    private final List<FlagHistory> history;
    @Getter
    private LocalDateTime lastModifiedAt;

    public DirtyFlag() {
        this.flag = null;
        this.history = new ArrayList<>();
        this.lastModifiedAt = null;
    }

    /**
     * 객체가 변경되었는지 여부를 반환합니다.
     * @return 변경 여부
     */
    public boolean isDirty() {
        return flag != null;
    }

    /**
     * 상태 변경 이력을 반환합니다.
     * @return 상태 변경 이력
     */
    public List<FlagHistory> getHistory() {
        return new ArrayList<>(history);
    }

    /**
     * 객체를 입력 상태로 표시합니다.
     * @throws IllegalStateException 이미 삭제 상태인 경우
     */
    public void markAsInsert() {
        validateStateTransition(Flag.INSERT);
        updateFlag(Flag.INSERT);
    }

    /**
     * 객체를 삭제 상태로 표시합니다.
     */
    public void markAsDelete() {
        updateFlag(Flag.DELETE);
    }

    /**
     * 객체를 수정 상태로 표시합니다.
     * @throws IllegalStateException 삭제 상태인 경우
     */
    public void markAsUpdate() {
        validateStateTransition(Flag.UPDATE);
        updateFlag(Flag.UPDATE);
    }

    /**
     * 객체를 초기 상태로 되돌립니다.
     */
    public void reset() {
        if (flag != null) {
            history.add(new FlagHistory(flag, lastModifiedAt));
        }
        this.flag = null;
        this.lastModifiedAt = null;
    }

    /**
     * 상태 변경 이력을 저장하는 내부 클래스
     */
    private void updateFlag(Flag newFlag) {
        if (flag != null) {
            history.add(new FlagHistory(flag, lastModifiedAt));
        }
        this.flag = newFlag;
        this.lastModifiedAt = LocalDateTime.now();
    }

    /**
     * 삭제 상태인 경우 예외 발생
     */
    private void validateStateTransition(Flag newFlag) {
        if (flag == Flag.DELETE) {
            throw new IllegalStateException("삭제된 객체는 " + newFlag + " 상태로 변경할 수 없습니다.");
        }
    }

    /**
     * 입력 상태인지 확인하는 메서드
     */
    public boolean isInsert() {
        return flag == Flag.INSERT;
    }

    /**
     * 삭제 상태인지 확인하는 메서드
     */
    public boolean isDelete() {
        return flag == Flag.DELETE;
    }

    /**
     * 수정 상태인지 확인하는 메서드
     */
    public boolean isUpdate() {
        return flag == Flag.UPDATE;
    }

    /**
     * 상태 변경 이력을 저장하는 내부 클래스
     */
    public record FlagHistory(Flag flag, LocalDateTime timestamp) {}
    
    @Override
    public String toString() {
        return String.format("DirtyFlag{flag=%s, lastModifiedAt=%s, historySize=%d}",
                flag, lastModifiedAt, history.size());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirtyFlag dirtyFlag = (DirtyFlag) o;
        return flag == dirtyFlag.flag &&
                Objects.equals(lastModifiedAt, dirtyFlag.lastModifiedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flag, lastModifiedAt);
    }
} 