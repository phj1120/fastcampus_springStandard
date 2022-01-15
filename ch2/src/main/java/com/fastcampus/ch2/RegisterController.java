package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
	
//	@RequestMapping("/register/add")
	@GetMapping("/register/add")
	public String register() {
		return "registerForm";
	}
	
//	@RequestMapping(value = "/register/save", method= {RequestMethod.GET, RequestMethod.POST})
	@PostMapping("/register/save") // spring 4.3 부터,
	public String save() {
		return "registerInfo";
	}
}
