package Backend.role.entity;

import Backend.common.AbstractEntity;
import Backend.common.DirtyFlag;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "User_Role")
@Data
public class UserRoleEntity extends AbstractEntity {
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "user_id")
    private Long userId;

}
