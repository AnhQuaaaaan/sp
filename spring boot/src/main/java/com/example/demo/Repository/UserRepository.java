package com.example.demo.Repository;

import com.example.demo.Entity.Cart;
import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserByUsernameAndPassword(String username, String password);
    User findUserById(int id);
}
