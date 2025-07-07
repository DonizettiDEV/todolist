package br.com.donizetti.todolist.user;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/users")
public class usercontroller {
    
@PostMapping("/")
    public void create(@RequestBody usermodel usermodel){
        System.out.println(usermodel.getUsername());

    }
}
