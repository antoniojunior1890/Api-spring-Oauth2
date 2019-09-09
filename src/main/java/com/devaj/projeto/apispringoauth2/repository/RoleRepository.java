package com.devaj.projeto.apispringoauth2.repository;

import com.devaj.projeto.apispringoauth2.entity.Role;
import com.devaj.projeto.apispringoauth2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
