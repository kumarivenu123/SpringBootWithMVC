package com.message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.message.entity.Register;
import com.message.entity.User;
import com.message.service.MessageService;

@RestController
public class MessageRestController {

	@Autowired
	private MessageService messageService;

	@GetMapping("/")
	public ModelAndView getHomePage() {
		return new ModelAndView("home");
	}

	@GetMapping("/register")
	public ModelAndView getRegisterPage() {
		return new ModelAndView("register");
	}

	@GetMapping("/login")
	public ModelAndView getLoginPage() {
		return new ModelAndView("login");
	}

	@PostMapping("/validate/register")
	public ModelAndView validateRegister(@RequestParam String name, @RequestParam String mailID,
			@RequestParam String password, @RequestParam String confirmPassword) {
		ModelAndView modelAndView = new ModelAndView("home");
		Register register = new Register();
		register.setName(name);
		register.setMailID(mailID);
		register.setPassword(password);
		register.setConfirmPassword(confirmPassword);

		if (password.equalsIgnoreCase(confirmPassword)) {
			boolean result = messageService.isValidUser(mailID);
			if (!result) {
				messageService.addRegisterUser(register);
				User user = new User();
				user.setUserName(name);
				user.setPassword(confirmPassword);
				messageService.addUser(user);
				modelAndView.addObject("status", user.getUserName() + " Register Successfully...");
			} else {
				modelAndView.addObject("status", mailID + " Already Used...");
			}
		} else {
			modelAndView.addObject("status", " Password and Confrim-Password not match..");
		}

		return modelAndView;
	}

	@PostMapping("validate/login")
	public ModelAndView validateLogin(@RequestParam String userName, @RequestParam String password) {
		ModelAndView modelAndView = null;
		if (userName != null && password != null) {
			boolean isValidUser = messageService.isValidUser(userName, password);
			if (isValidUser) {
				modelAndView = new ModelAndView("welcome");
				modelAndView.addObject("userName", userName);
			} else {
				modelAndView = new ModelAndView("login");
				modelAndView.addObject("status", " Invalid UserName/Password...");
			}
		} else {
			modelAndView = new ModelAndView("login");
			modelAndView.addObject("status", " UserName/Password Should Not Empty...");
		}
		return modelAndView;
	}

	@PostMapping("/message/send")
	public ModelAndView sendMessage(@RequestParam String userName, @RequestParam String message) {
		ModelAndView modelAndView = null;
		if (userName != null && message != null) {
			if (userName.length() != 0 && message.length() != 0) {
				messageService.saveMessage(userName, message);
			}
			modelAndView = new ModelAndView("welcome");
			modelAndView.addObject("statusMessage", "Message Sended Successfully...");
			modelAndView.addObject("userName", userName);
			modelAndView.addObject("messages", messageService.getAllMessages());
		} else {
			modelAndView = new ModelAndView("welcome");
			modelAndView.addObject("statusMessage", "Message Should Not Empty...");
			modelAndView.addObject("status", userName);
		}
		return modelAndView;
	}
}
