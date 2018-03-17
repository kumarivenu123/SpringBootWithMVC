package com.message.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.message.entity.Register;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Integer>{
	
	public Register findByMailID(String mailID);
	
}
