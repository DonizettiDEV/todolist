package br.com.donizetti.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.donizetti.todolist.Utils.Utils;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/task")
public class taskcontroller {

    @Autowired
    private taskrepository taskRepository;

@PostMapping("/")
public ResponseEntity<?> create(@RequestBody taskmodel taskModel, HttpServletRequest request) {
    var idUser = (UUID) request.getAttribute("iduser");
    if (idUser == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado");
    }

    taskModel.setUserid(idUser); // ← ESSA LINHA É ESSENCIAL
    var task = this.taskRepository.save(taskModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(task);
}

    @GetMapping
    public ResponseEntity<?> list(HttpServletRequest request) {
        var idUser = (UUID) request.getAttribute("iduser");

        if (idUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado");
        }

        var tasks = this.taskRepository.findByUserid(idUser);
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody taskmodel taskModel, @PathVariable UUID id, HttpServletRequest request) {
        var idUser = (UUID) request.getAttribute("iduser");
        if (idUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado");
        }

        var task = this.taskRepository.findById(id).orElse(null);
        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada");
        }

        if (!task.getUserid().equals(idUser)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não autorizado a atualizar esta tarefa");
        }

        Utils.copyNonNullProperties(taskModel, task);
        var taskupdated = this.taskRepository.save(task);
        return ResponseEntity.ok().body (taskupdated);
    }    
}

