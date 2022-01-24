package com.fastcampus.ch2;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ExceptionController {
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String catcher(Exception ex, Model m) {
		System.out.println("[ExceptionHandler] ExceptionController.catcher");
		System.out.println("[ExceptionHandler model] :"+m);
		m.addAttribute("ex", ex);

		return "error";
	}
	
	@ExceptionHandler({NullPointerException.class, FileNotFoundException.class})
	public String catcher2(Exception ex, Model m) {
		System.out.println("[ExceptionHandler] ExceptionController.catcher2");
		m.addAttribute("ex", ex);
		return "error";
	}
	
	@RequestMapping("/ex")
	public String main(Model m) throws Exception{
		m.addAttribute("msg", "message from ExceptionController.main()");
		throw new Exception("에외가 발생했습니다.");
	}

	@RequestMapping("/ex2")
	public String main2() throws Exception{
		throw new NullPointerException("에외가 발생했습니다.");
	}
}
