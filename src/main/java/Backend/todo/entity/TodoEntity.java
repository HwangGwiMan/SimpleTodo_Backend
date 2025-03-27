package Backend.todo.entity;

import Backend.common.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.sql.Timestamp;

@Entity
public class TodoEntity extends AbstractEntity {

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "content")
    private String content;

    @Column(name = "start_date_time")
    private Timestamp start_date_time;

    @Column(name = "end_date_time")
    private Timestamp end_date_time;

    @Column(name = "alarm_date_time")
    private Timestamp AlarmDateTime;

}
