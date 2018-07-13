package io.rive.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public @ResponseBody String createUser(@RequestParam String name, @RequestParam String email) {

		User user = new User(name, email);
		userRepository.save(user);
		return String.format("%s %s %s",  "User", name, ",created!");
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public @ResponseBody Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
}