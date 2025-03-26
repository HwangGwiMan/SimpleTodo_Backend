package Backend.role.service;

import java.util.List;

import Backend.role.entity.RoleEntity;
import Backend.role.entity.UserRoleEntity;
import Backend.role.repository.RoleRepository;
import Backend.role.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

// import Backend.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;

    /**
     * 유저의 모든 권한을 조회하는 메서드
     * 
     * @param userId 유저의 아이디
     * @return 유저의 모든 권한
     */
    public List<UserRoleEntity> getRolesByUserId(Long userId) {
        return userRoleRepository.findByUserId(userId);
    }

    /**
     * 모든 권한을 조회하는 메서드
     * 
     * @return 모든 권한
     */
    public List<RoleEntity> getAllRoles() {
        return roleRepository.findAll();
    }

    // 권한 부여
    public void assignRoleToUser(int userId, String roleName) {
        // roleRepository.assignRoleToUser(userId, roleName);
    }
}
