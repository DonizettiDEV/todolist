package br.com.donizetti.todolist.task;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface taskrepository extends JpaRepository<taskmodel, UUID>{
    List<taskmodel> findByUserid(UUID userid);
    
}
