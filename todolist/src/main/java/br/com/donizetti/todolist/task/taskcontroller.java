package br.com.donizetti.todolist.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class taskcontroller {

    @Autowired
    private taskrepository iTaskrepository;
    @PostMapping("/")
    public void  create(@RequestBody taskmodel taskmodel){
        System.out.println("chegou no controller");
        var task = this.iTaskrepository.save(taskmodel);
        
    }
}
