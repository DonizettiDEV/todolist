package br.com.donizetti.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/task")
public class taskcontroller {

    @Autowired
    private taskrepository iTaskrepository;
    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody taskmodel taskmodel, HttpServletRequest request) {
        var iduser = (UUID) request.getAttribute("iduser");
        taskmodel.setUserid(iduser); // Corrige para setar o id do usuário

        var currentDate = LocalDateTime.now();
        if (currentDate.isAfter(taskmodel.getStartaT())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(taskmodel);
        }
        if (currentDate.isAfter(taskmodel.getEndat())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(taskmodel);
        }

        var task = this.iTaskrepository.save(taskmodel);
        return ResponseEntity.status(HttpStatus.OK).body(task);

    }
    @GetMapping("/")
    public List<taskmodel> list(HttpServletRequest request){
        var idUser = request.getAttribute("iduser");
        var tasks = this.iTaskrepository.findByUserid((UUID) idUser);
        return tasks;
    }
    public void update(@RequestBody taskmodel taskmodel, HttpServletRequest request) {
    
    }

}

