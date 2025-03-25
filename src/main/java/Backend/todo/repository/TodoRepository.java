// package Backend.todo.repository;

// import java.util.List;

// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.stereotype.Repository;

// import Backend.todo.dto.TodoRequestDto;
// import Backend.todo.dto.TodoSaveDto;
// import lombok.RequiredArgsConstructor;

// @Repository
// @RequiredArgsConstructor
// public class TodoRepository {
//     private final JdbcTemplate jdbcTemplate;


//     public void createTodo(TodoSaveDto todoSaveDto) {
//         for (TodoRequestDto todoRequestDto : todoSaveDto.getTodos()) {
//             String sql = "INSERT INTO todos (title, description, completed, created_by) VALUES (?, ?, ?, ?)";
//             jdbcTemplate.update(sql, todoRequestDto.getTitle(), todoRequestDto.getDescription(), todoRequestDto.isCompleted(), todoRequestDto.getCreatedBy());
//         }
//     }

//     public void updateTodo(TodoRequestDto todoRequestDto) {
//         String sql = "UPDATE todos SET title = ?, description = ?, completed = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
//         jdbcTemplate.update(sql, todoRequestDto.getTitle(), todoRequestDto.getDescription(), todoRequestDto.isCompleted(), todoRequestDto.getId());
//     }

//     public void deleteTodo(int id) {
//         String sql = "DELETE FROM todos WHERE id = ?";
//         jdbcTemplate.update(sql, id);
//     }

//     public TodoRequestDto getTodo(int id) {
//         String sql = "SELECT * FROM todos WHERE id = ?";
//         return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
//             TodoRequestDto todoRequestDto = new TodoRequestDto();
//             todoRequestDto.setTitle(rs.getString("title"));
//             todoRequestDto.setDescription(rs.getString("description"));
//             todoRequestDto.setCompleted(rs.getBoolean("completed"));
//             todoRequestDto.setCreatedBy(rs.getInt("created_by"));
//             return todoRequestDto;
//         }, id);
//     }       

//     public List<TodoRequestDto> getTodosByUserId(int userId) {
//         String sql = "SELECT * FROM todos WHERE created_by = ?";
//         return jdbcTemplate.query(sql, (rs, rowNum) -> {
//             TodoRequestDto todoRequestDto = new TodoRequestDto();
//             todoRequestDto.setId(rs.getInt("id"));
//             todoRequestDto.setTitle(rs.getString("title"));
//             todoRequestDto.setDescription(rs.getString("description"));
//             todoRequestDto.setCompleted(rs.getBoolean("completed"));
//             todoRequestDto.setCreatedBy(rs.getInt("created_by"));
//             return todoRequestDto;
//         });
//     }
// } 