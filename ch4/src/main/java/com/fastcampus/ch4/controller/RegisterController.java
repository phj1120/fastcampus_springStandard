package com.fastcampus.ch4.controller;

import com.fastcampus.ch4.domain.User;
import com.fastcampus.ch4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    UserService userService;

    @GetMapping("/add")
    public String singIn() {
        return "register/signInForm";
    }


//    왜 회원 가입이 안되나... postman으로는 여기 호출 되는데
//    JSP 에서 버튼 누르면 여기로 와야되는데 안오네
//    birth 가 있으면 오류 -> 타입 변환 문제 -> 자동 아니었나? 아니었음
//    @DateTimeFormat(pattern = "yyyy-mm-dd")
    
    @PostMapping("/add")
    public String addUser(User user) throws Exception {
        System.out.println("RegisterController.addUser");
        System.out.println("[register/add] : user = " + user);
        userService.write(user);
        return "redirect:/login/login";
    }
}
