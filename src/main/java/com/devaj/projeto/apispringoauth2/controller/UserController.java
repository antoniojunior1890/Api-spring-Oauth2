package com.devaj.projeto.apispringoauth2.controller;

import com.devaj.projeto.apispringoauth2.entity.User;
import com.devaj.projeto.apispringoauth2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public Page<User> list(@PageableDefault(page = 0, size = 5)
                               @SortDefault.SortDefaults({
                                    @SortDefault(sort = "id", direction = Sort.Direction.ASC)
    })Pageable pageable) {

        return userRepository.findAll(pageable);
    }

    @PostMapping
    public User save(@RequestBody User user) {

        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User edit(@PathVariable("id") Long id, @RequestBody User user) {
        User userEdit = user;
        userEdit.setId(id);
        return userRepository.save(userEdit);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public Optional<User> detail(@PathVariable("id") Long id) {
        return userRepository.findById(id);
    }

}