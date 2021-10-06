package com.example.demo.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.User;
@Repository
public interface UserRepo extends CrudRepository<User,Long>{
	User findByEmail(String email);
}
