package Backend.role.entity;

import Backend.common.AbstractEntity;
import Backend.common.DirtyFlag;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Role")
@Data
public class RoleEntity extends AbstractEntity {

    @Column(name = "url")
    private String url;

    @Column(name = "descript")
    private String descript;

}
