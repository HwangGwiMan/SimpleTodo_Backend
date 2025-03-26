package Backend.role.repository;

import Backend.role.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    /**
     * 유저의 모든 권한을 조회하는 메서드
     * 
     * @param userId
     * @return
     */
    List<UserRoleEntity> findByUserId(Long userId);
}
