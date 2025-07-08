package br.com.donizetti.todolist.user;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.var;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/users")
public class usercontroller {
    @Autowired
    private UserRepository userRepository;
    
@PostMapping("/")
    public usermodel create(@RequestBody usermodel usermodel){
        var userCreated = this.userRepository.save(usermodel);
            return userCreated;

    }
}
