package com.devaj.projeto.apispringoauth2.config;

import com.devaj.projeto.apispringoauth2.entity.User;
import com.devaj.projeto.apispringoauth2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializr implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<User> users = userRepository.findAll();

        if(users.isEmpty()){
            this.createUser("Antonio", "antonio@email.com", "1234");
        }
    }

    public void createUser(String name, String email, String password){
        User newUser = new User(name, email, password);

        userRepository.save(newUser);
    }
}
