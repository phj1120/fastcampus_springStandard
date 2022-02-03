package com.fastcampus.ch2;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
//		if(target instanceof User) return; // 위의 supports 에서 걸러주므로 생략
		System.out.println("UserValidator.validate() is called");
		User user = (User)target;
		
		String id = user.getId();
		
		if(id==null || "".equals(id.trim())) {
//			일단 에러코드를 지정해주고 메시지는 파일에 따로 저장
			errors.rejectValue("id", "required");
		}


//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "required", "pwd 값이 잘 못 되었습니다");
		
		if(id==null || id.length() < 5 || id.length() > 12) {
			errors.rejectValue("id", "invalidLength");
		}
		
		
	}

}
