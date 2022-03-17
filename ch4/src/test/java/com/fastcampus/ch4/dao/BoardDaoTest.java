package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.BoardDto;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardDaoTest {

    @Autowired
    BoardDao boardDao;

    @Test
    public void totalTest() throws Exception {
        assertTrue(boardDao != null);

        BoardDto board = new BoardDto("제목", "글", "hj");
        assertTrue(boardDao.insert(board) != 0);

//        이건..... no 가져오는거 한 다음에 해야 제대로된 테스트로 쓸 수 있을 듯
        BoardDto board2 = boardDao.select(1);
        assertTrue("hj".equals(board2.getWriter()));
    }
}