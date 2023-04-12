package com.example.book_club.users;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    
    boolean existsByUserNameIgnoreCase(String userName);
    boolean existsByUserEmailIgnoreCase(String userEmail);
    boolean existsByUserNameAndUserEmailIgnoreCase(String userName, String userEmail);
    Optional<User> findByUserName(String userName);
    
}
