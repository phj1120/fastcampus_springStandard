package com.fastcampus.ch4.controller;

import com.fastcampus.ch4.dao.BoardDao;
import com.fastcampus.ch4.dao.UserDao;
import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

@Controller
@RequestMapping("board")
public class BoardController {

    @Autowired
    BoardDao boardDao;

    @Autowired
    UserDao userDao;

    @GetMapping("list")
    public String boardList(BoardDto board, Model m, HttpServletRequest request) {
        if (!loginCheck(request)) {
//            return "redirect:login/login"; // http://localhost/ch4/board/login/login 으로 이동
            return "redirect:/login/login"; // http://localhost/ch4/login/login 으로 이동
        }
        return "list";
    }

    private boolean loginCheck(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return session.getAttribute("id") != null;
    }
}