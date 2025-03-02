package Backend.role.service;

import java.util.List;

import org.springframework.stereotype.Service;

import Backend.role.repository.RoleRepository;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<String> getRolesByUserId(int userId) {
        return roleRepository.findRolesByUserId(userId);
    }
}
