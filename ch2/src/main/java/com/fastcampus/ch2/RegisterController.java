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
	@PostMapping("/register/save") // spring 4.3 ���� ���� ����,
	public String save(User user, Model m) throws Exception {
//		1. ��ȿ�� �˻�
		if(!isValid(user)) {
			String msg = URLEncoder.encode("id�� �� �� �Է��ϼ̽��ϴ�.", "utf-8");
			return "redirect:/register/add?msg="+msg; // url���ۼ�(Rewriting)
		}
		
		
//		2. DB�� �ű� ȸ�� ������ ����
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return true;
	}
}
