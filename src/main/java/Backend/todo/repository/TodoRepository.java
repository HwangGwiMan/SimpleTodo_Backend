package Backend.todo.repository;// package Backend.todo.repository;

// import java.util.List;

// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.stereotype.Repository;

// import Backend.todo.dto.TodoRequestDto;
// import Backend.todo.dto.TodoSaveDto;
// import lombok.RequiredArgsConstructor;

import Backend.todo.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

    /**
     * 유저 아이디로 Todo 조회
     * 
     * @param userId
     * @return
     */
    List<TodoEntity> findByUserId(Long userId);

}