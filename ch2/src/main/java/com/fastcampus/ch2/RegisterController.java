package com.fastcampus.ch2;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
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
		
//		자동 검증
//		binder.setValidator(new UserValidator()); // UserValidator 를 WebDataBinder의 로컬 Validator 로 등록
		
//		GlobalValidator
//		binder.addValidators(new UserValidator()); // GlobalValidator 는 servlet-context.xml 에서 등록
		List<Validator> validatorList = binder.getValidators();
		System.out.println("[validatorList] "+validatorList);
		
//		Converter 
//		스프링이 기본적으로 제공하는 컨버터 확인
		ConversionService conversionService = binder.getConversionService();
//		System.out.println("[conversionService] : "+conversionService);
	}
	
//	Post도 등록되어있으면 registerFoem.jsp 에서 회원가입 버튼을 눌러도 여기로.
//	이렇게 등록해 줘야함 <form:form modelAttribute="user" action="save">
//	@RequestMapping(value = "/register/add", method = {RequestMethod.GET, RequestMethod.POST})
	@GetMapping("/register/add")
	public String register() {
		return "registerForm";
	}
	
	@PostMapping("/register/add")
	public String registerSave() {
		return "forward:/register/save";
	}
	
//	@RequestMapping(value = "/register/save", method= {RequestMethod.GET, RequestMethod.POST})
	@PostMapping("/register/save") // spring 4.3 부터 적용 가능,
	public String save(@Valid User user, BindingResult result, Model m) throws Exception {
		System.out.println("[result] : "+result);
		System.out.println("[user] : "+user);

		
//		수동 검증 : Validator 를 직접 생성하고 validate()를 직접 호출
//		UserValidator userValidator = new UserValidator();
//		userValidator.validate(user, result); // BindingResult 는 Errors의 자손
		
//		User 객체 검증한 결과 에러가 있으면 registerForm 으로 반환, 에러 출력
		if(result.hasErrors()) {
			return "registerForm";
		}

////		1. 유효성 검사
//		if(!isValid(user)) {
//			String msg = URLEncoder.encode("id를 잘 못 입력하셨습니다.", "utf-8");
//			return "forward:/register/add"; // 
////			return "redirect:/register/add?msg="+msg; // url재작성(Rewriting)
//		}
		
//		2. DB에 신규 회원 정보를 저장
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return true;
	}
}
