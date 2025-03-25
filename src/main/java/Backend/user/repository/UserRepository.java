package Backend.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Backend.user.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
    public UserEntity findByUsername(String username);

    // private final JdbcTemplate jdbcTemplate;

    // public UserRepository(JdbcTemplate jdbcTemplate) {
    //     this.jdbcTemplate = jdbcTemplate;
    // }
    
    // public void save(UserRequestDto user) {
    //     String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
    //     jdbcTemplate.update(sql, user.getUsername(), user.getPassword());
    // }

    // public UserRequestDto findByUsername(String username) {
    //     String sql = "SELECT * FROM user WHERE username = ?";
    //     try {
    //         return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
    //             UserRequestDto userRequestDto = new UserRequestDto(); 
    //             userRequestDto.setUsername(rs.getString("username"));
    //             userRequestDto.setPassword(rs.getString("password"));
    //             userRequestDto.setId(rs.getInt("id"));
    //             return userRequestDto;
    //         }, username);
    //     } catch (EmptyResultDataAccessException e) {
    //         return null;
    //     } catch (IncorrectResultSizeDataAccessException e) {
    //         throw new RuntimeException(e.getMessage());
    //     }
    // }

    // // username 중복 체크
    // public boolean existsByUsername(String username) {
    //     String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
    //     Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username);
    //     return count != null && count > 0;
    // }
}
