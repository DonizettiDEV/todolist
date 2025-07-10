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
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;
    @Column(length = 50)
    private String title;
    private LocalDateTime startaT;
    private LocalDateTime endat;
    private String priority;
    
    private UUID userid;

    @CreationTimestamp
    private LocalDateTime createdat;
    

}
