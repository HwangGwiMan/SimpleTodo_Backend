package Backend.role.service;

import java.util.List;

import Backend.role.entity.RoleEntity;
import Backend.role.repository.RoleRepository;
import org.springframework.stereotype.Service;

// import Backend.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {
     private final RoleRepository roleRepository;

    public List<String> getRolesByUserId(int userId) {
        return null;
    }
    // 전체 권한 조회
    public List<RoleEntity> getAllRoles() {
        return roleRepository.findAll();
    }

    // 권한 부여
    public void assignRoleToUser(int userId, String roleName) {
        // roleRepository.assignRoleToUser(userId, roleName);
    }
}
