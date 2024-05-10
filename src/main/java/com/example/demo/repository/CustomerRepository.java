package com.example.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.MyUser;



public interface CustomerRepository extends JpaRepository<MyUser, Long> {

	Optional<MyUser> findByUsername(String username);

}
