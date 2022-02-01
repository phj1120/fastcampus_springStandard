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
	
//	��Ʈ�ѷ� �������� �����
	@InitBinder
	public void toDate(WebDataBinder binder) {
//		User Ŭ������ 
//		@DateTimeFormat(pattern="yyyy-mm-dd")
//		private Date birth; 
//		�� ��ü ����
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
//		String �� �����ڸ� �̿��� String �迭��
		binder.registerCustomEditor(String[].class, new StringArrayPropertyEditor("#"));
		
//		Converter 
//		�������� �⺻������ �����ϴ� ������ Ȯ��
		ConversionService conversionService = binder.getConversionService();
		System.out.println("[conversionService] : "+conversionService);
	}
	
	@RequestMapping(value = "/register/add", method = {RequestMethod.GET, RequestMethod.POST})
	public String register() {
		return "registerForm";
	}
	
//	@RequestMapping(value = "/register/save", method= {RequestMethod.GET, RequestMethod.POST})
	@PostMapping("/register/save") // spring 4.3 ���� ���� ����,
	public String save(User user, BindingResult result, Model m) throws Exception {
		System.out.println("[result] : "+result);
		System.out.println("[user] : "+user);
//		1. ��ȿ�� �˻�
		if(!isValid(user)) {
			String msg = URLEncoder.encode("id�� �� �� �Է��ϼ̽��ϴ�.", "utf-8");
			return "forward:/register/add"; // 
//			return "redirect:/register/add?msg="+msg; // url���ۼ�(Rewriting)
		}
		
//		2. DB�� �ű� ȸ�� ������ ����
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return true;
	}
}
