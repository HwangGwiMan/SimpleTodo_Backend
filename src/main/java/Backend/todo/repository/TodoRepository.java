package Backend.todo.repository;// package Backend.todo.repository;

// import java.util.List;

// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.stereotype.Repository;

// import Backend.todo.dto.TodoRequestDto;
// import Backend.todo.dto.TodoSaveDto;
// import lombok.RequiredArgsConstructor;

import Backend.todo.entity.TodoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
}