package br.com.donizetti.todolist.task;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
    public void  create(@RequestBody taskmodel taskmodel, HttpServletRequest request){
        var iduser = (UUID) request.getAttribute("iduser");
        taskmodel.setUserid(null);
        var task = this.iTaskrepository.save(taskmodel);
        
    }
}
