package Backend.role.entity;

import Backend.common.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Role")
public class RoleEntity extends AbstractEntity {

    @Column(name = "url")
    private String url;

    @Column(name = "descript")
    private String descript;
}
