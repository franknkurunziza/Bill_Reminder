package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.Bill;

@Repository
public interface BillRepo extends CrudRepository<Bill, Long> {
	List<Bill> findAll();
}
