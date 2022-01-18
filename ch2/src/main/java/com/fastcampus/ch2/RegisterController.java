package com.fastcampus.ch2;

import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {
	
//	@RequestMapping("/register/add")
//	@GetMapping("/register/add")
//	public String register() {
//		return "registerForm";
//	}
	
//	@RequestMapping(value = "/register/save", method= {RequestMethod.GET, RequestMethod.POST})
	@PostMapping("/register/save") // spring 4.3 부터 적용 가능,
	public String save(User user, Model m) throws Exception {
//		1. 유효성 검사
		if(!isValid(user)) {
			String msg = URLEncoder.encode("id를 잘 못 입력하셨습니다.", "utf-8");
			return "redirect:/register/add?msg="+msg; // url재작성(Rewriting)
		}
		
		
//		2. DB에 신규 회원 정보를 저장
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return true;
	}
}
