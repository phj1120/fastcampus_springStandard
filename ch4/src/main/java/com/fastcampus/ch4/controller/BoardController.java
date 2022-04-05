package com.fastcampus.ch4.controller;

import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.PageHandler;
import com.fastcampus.ch4.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping("list")
//    int totalCount, int page -> cannot be translated into a null value 에러 발생
//    public String boardList(Integer page, Integer totalCount, Model m, HttpServletRequest request) throws Exception {
    public String boardList(Integer page, Integer pageSize, Model m, HttpServletRequest request) throws Exception {
        if (!loginCheck(request)) {
//            return "redirect:login/login"; // http://localhost/ch4/board/login/login 으로 이동
            return "redirect:/login/login?toURL="+request.getRequestURL(); // http://localhost/ch4/login/login 으로 이동
        }

//        Integer 로 하면 error 는 안나지만 아무 데이터도 안나옴
        if (page == null) page = 1;
        if (pageSize == null) pageSize = 10;

//        그지 이렇게 주는 것 보다 page 랑 pageSize 주는게 낫지, totalCount 계속 주는 건 하면서 이게 맞나 싶긴 했음
//        -> 는 구멍가게 코딩단 책에서는 amount 로 총 글 수 넘겨줬었음.
//        PageHandler ph = new PageHandler(totalCount, page, 10);
//        HashMap<String, Integer> map = new HashMap<>();
//        map.put("offset", ph.getPage()*ph.getPageSize());
//        map.put("pageSize", ph.getPageSize());

        int totalCount = boardService.count();
        PageHandler ph = new PageHandler(totalCount, page, pageSize);

        HashMap map = new HashMap();
        map.put("offset", (page - 1) * pageSize);
        map.put("pageSize", pageSize);

        List<BoardDto> boardList = boardService.getPage(map);
        m.addAttribute("pageHandler", ph);
        m.addAttribute("list", boardList);
        return "boardList";
    }

    private boolean loginCheck(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return session.getAttribute("id") != null;
    }
}