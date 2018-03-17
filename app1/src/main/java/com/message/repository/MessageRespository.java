package com.message.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.message.entity.Message;

@Repository
public interface MessageRespository extends JpaRepository<Message, Integer>{


}
