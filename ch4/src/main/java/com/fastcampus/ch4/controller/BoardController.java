package com.fastcampus.ch4.controller;

import com.fastcampus.ch4.dao.BoardDao;
import com.fastcampus.ch4.dao.UserDao;
import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.PageHandler;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("board")
public class BoardController {

    @Autowired
    BoardDao boardDao;

    @Autowired
    UserDao userDao;

    @GetMapping("list")
//    int totalCount, int page -> cannot be translated into a null value
    public String boardList(Integer totalCount, Integer page, Model m, HttpServletRequest request) throws Exception {
        if (!loginCheck(request)) {
//            return "redirect:login/login"; // http://localhost/ch4/board/login/login 으로 이동
            return "redirect:/login/login"; // http://localhost/ch4/login/login 으로 이동
        }
        PageHandler ph = new PageHandler(totalCount, page, 10);

        HashMap<String, Integer> map = new HashMap<>();
        map.put("from", ph.getPage()*ph.getPageSize());
        map.put("size", ph.getPageSize());
        List<BoardDto> boardList = boardDao.selectPage(map);

        System.out.println("boardList = " + boardList);
        m.addAttribute("boardList", boardList);
        m.addAttribute("ph", ph);

        return "list";
//        return "list?page="+ph.getPage()+"&totalCount="+ph.getTotalCount();
    }

    private boolean loginCheck(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return session.getAttribute("id") != null;
    }
}