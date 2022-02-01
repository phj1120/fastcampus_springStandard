package com.fastcampus.ch2;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {
	
//	컨트롤러 내에서만 적용됨
	@InitBinder
	public void toDate(WebDataBinder binder) {
//		User 클래스의 
//		@DateTimeFormat(pattern="yyyy-mm-dd")
//		private Date birth; 
//		로 대체 가능
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
//		String 을 구분자를 이용해 String 배열로
		binder.registerCustomEditor(String[].class, new StringArrayPropertyEditor("#"));
		
//		Converter 
//		스프링이 기본적으로 제공하는 컨버터 확인
		ConversionService conversionService = binder.getConversionService();
		System.out.println("[conversionService] : "+conversionService);
	}
	
	@RequestMapping(value = "/register/add", method = {RequestMethod.GET, RequestMethod.POST})
	public String register() {
		return "registerForm";
	}
	
//	@RequestMapping(value = "/register/save", method= {RequestMethod.GET, RequestMethod.POST})
	@PostMapping("/register/save") // spring 4.3 부터 적용 가능,
	public String save(User user, BindingResult result, Model m) throws Exception {
		System.out.println("[result] : "+result);
		System.out.println("[user] : "+user);
//		1. 유효성 검사
		if(!isValid(user)) {
			String msg = URLEncoder.encode("id를 잘 못 입력하셨습니다.", "utf-8");
			return "forward:/register/add"; // 
//			return "redirect:/register/add?msg="+msg; // url재작성(Rewriting)
		}
		
//		2. DB에 신규 회원 정보를 저장
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return true;
	}
}
