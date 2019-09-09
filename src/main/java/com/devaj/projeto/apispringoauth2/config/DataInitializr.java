package com.devaj.projeto.apispringoauth2.config;

import com.devaj.projeto.apispringoauth2.entity.Role;
import com.devaj.projeto.apispringoauth2.entity.User;
import com.devaj.projeto.apispringoauth2.repository.RoleRepository;
import com.devaj.projeto.apispringoauth2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializr implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<User> users = userRepository.findAll();

        if(users.isEmpty()){
            this.createUser("Antonio", "antonio@email.com", passwordEncoder.encode("1234"), "ROLE_ADMIN");
            this.createUser("Junior", "junior@email.com", passwordEncoder.encode("1234"), "ROLE_SIMPLE");
        }
    }

    public void createUser(String name, String email, String password, String role){

        Role roleObj =  new Role();
        roleObj.setName(role);

        this.roleRepository.save(roleObj);

        User newUser = new User(name, email, password, Arrays.asList(roleObj));

        userRepository.save(newUser);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
