package Backend.role.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RoleRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<String> findRolesByUserId(int userId) {
        String sql = "SELECT r.name FROM roles r " +
                    "JOIN user_roles ur ON r.id = ur.role_id " +
                    "JOIN users u ON ur.user_id = u.id " +
                    "WHERE u.id = ?";
        
        return jdbcTemplate.query(sql, 
            (rs, rowNum) -> rs.getString("name"), 
            userId);
    }

    public void assignRoleToUser(int userId, String roleName) {
        String sql = "INSERT INTO user_roles (user_id, role_id) " +
                    "SELECT ?, id FROM roles WHERE name = ?";
        
        jdbcTemplate.update(sql, userId, roleName);
    }

    public void removeRoleFromUser(int userId, String roleName) {
        String sql = "DELETE FROM user_roles " +
                    "WHERE user_id = ? AND role_id IN (SELECT id FROM roles WHERE name = ?)";
        
        jdbcTemplate.update(sql, userId, roleName);
    }

    public boolean hasRole(int userId, String roleName) {
        String sql = "SELECT COUNT(*) FROM user_roles ur " +
                    "JOIN roles r ON ur.role_id = r.id " +
                    "WHERE ur.user_id = ? AND r.name = ?";
        
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userId, roleName);
        return count != null && count > 0;
    }

    public List<String> findAll() {
        String sql = "SELECT name FROM roles";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("name"));
    }

    // 권한 추가
    public void addRole(String roleName) {
        String sql = "INSERT INTO roles (name) VALUES (?)";
        jdbcTemplate.update(sql, roleName);
    }
    
    
} 