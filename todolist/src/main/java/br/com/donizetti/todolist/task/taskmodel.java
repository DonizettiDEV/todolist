package br.com.donizetti.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity(name = "tb_task")
public class taskmodel {
    
    @Id
    @GeneratedValue
    private UUID id;

    private UUID userid;
    private String title;
    private String description;
    @Column(length = 50)
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    
    @CreationTimestamp
    private LocalDateTime createdat;
    
}