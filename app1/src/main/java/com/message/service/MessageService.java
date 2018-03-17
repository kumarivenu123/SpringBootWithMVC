package com.message.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.message.entity.Message;
import com.message.entity.Register;
import com.message.entity.User;
import com.message.repository.MessageRespository;
import com.message.repository.RegisterRepository;
import com.message.repository.UserRespository;

@Service
public class MessageService {

	@Autowired
	private UserRespository userRespository;

	@Autowired
	private RegisterRepository registerRepository;

	@Autowired
	private MessageRespository messageRespository;

	public void addUser(User user) {
		userRespository.save(user);
	}

	public void addRegisterUser(Register register) {
		registerRepository.save(register);
	}

	public boolean isValidUser(String mailID) {
		return registerRepository.findByMailID(mailID) != null ? true : false;
	}

	public boolean isValidUser(String userName, String password) {
		boolean flag = false;
		User user = userRespository.findByUserName(userName);
		if (user.getUserName().equalsIgnoreCase(userName) && user.getPassword().equalsIgnoreCase(password)) {
			flag = true;
		}
		return flag;
	}

	public void saveMessage(String userName, String message) {
		Message msg = new Message();
		msg.setName(userName);
		msg.setMessage(message);
		msg.setSendDate(LocalDateTime.now());
		messageRespository.save(msg);
	}

	public List<Message> getAllMessages() {
		return messageRespository.findAll();
	}

}
