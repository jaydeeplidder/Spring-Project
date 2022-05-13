package in.sts.springgradleproject.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.sts.springgradleproject.model.User;

@Controller
public class UserController {

	ArrayList<User> allUsers=new ArrayList<>();
	@GetMapping("/user/add")
	public String addUser(Model model){
		
		model.addAttribute("user",new User());
		return "add-user";
		
	}
	
	@PostMapping("/user/add")
	public String saveUser(@ModelAttribute User user,Model model){
		allUsers.add(user);
		model.addAttribute("users",allUsers);
		return "result";
		
	}
}
