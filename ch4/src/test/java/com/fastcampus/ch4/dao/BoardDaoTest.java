package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.BoardDto;
import lombok.extern.log4j.Log4j;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardDaoTest {

    @Autowired
    BoardDao boardDao;

    @Test
    public void makeTestData(){
        boardDao.deleteAll();
        assertTrue(boardDao.count() == 0);

        for (int i = 1; i <= 220; i++) {
            BoardDto boardDto = new BoardDto("title " + i, "content", "asdf");
            boardDao.insert(boardDto);
        }
        assertTrue(boardDao.count() == 220);
    }

    @Test
    public void countTest(){
        boardDao.deleteAll();
        assertTrue(boardDao.count() == 0);
    }


    @Test
    public void insertTest(){
        boardDao.deleteAll();
        assertTrue(boardDao.count() == 0);

        BoardDto boardDto = new BoardDto("title", "content", "writer");
        int rows = boardDao.insert(boardDto);
        assertTrue(rows == 1);
    }

    @Test
    public void selectAllTest() throws Exception {
        boardDao.deleteAll();
        assertTrue(boardDao.count() == 0);

        for (int i = 1; i <= 10; i++) {
            BoardDto boardDto = new BoardDto("title " + i, "content", "writer");
            boardDao.insert(boardDto);
        }
        List<BoardDto> boardList = boardDao.selectAll();
        assertTrue(boardList.size() == 10);
    }

    @Test
    public void selectPageTest() throws Exception {
        boardDao.deleteAll();
        assertTrue(boardDao.count() == 0);

        for (int i = 1; i <= 23; i++) {
            BoardDto boardDto = new BoardDto("title " + i, "content", "writer");
            boardDao.insert(boardDto);
        }
        assertTrue(boardDao.count() == 23);

        HashMap<String, Integer> map = new HashMap<>();
        map.put("offset", 10);
        map.put("pageSize", 10);
        List<BoardDto> list = boardDao.selectPage(map);
        assertTrue(list.size() == 10);
    }

    @Test
    public void increaseViewCntTest() throws Exception {
        boardDao.deleteAll();
        assertTrue(boardDao.count()==0);

        BoardDto boardDto = new BoardDto("title", "content", "asdf");
        assertTrue(boardDao.insert(boardDto)==1);
        assertTrue(boardDao.count()==1);

        Integer bno = boardDao.selectAll().get(0).getBno();
        assertTrue(boardDao.increaseViewCnt(bno)==1);

        boardDto = boardDao.select(bno);
        System.out.println("boardDto = " + boardDto);
        assertTrue(boardDto!=null);
        assertTrue(boardDto.getView_cnt() == 1);

        assertTrue(boardDao.increaseViewCnt(bno)==1);
        boardDto = boardDao.select(bno);
        assertTrue(boardDto!=null);
        assertTrue(boardDto.getView_cnt() == 2);
    }

    @Test
    public void deleteTest() throws Exception {
        boardDao.deleteAll();
        assertTrue(boardDao.count() == 0);

        BoardDto boardDto = new BoardDto("title", "content", "asdf");
        boardDao.insert(boardDto);
        System.out.println(boardDao.count() == 1);
        assertTrue(boardDao.count() == 1);

        int bno = boardDao.selectAll().get(0).getBno();
        HashMap<String, String> map = new HashMap<>();
        map.put("bno", String.valueOf(bno));
        map.put("writer", boardDto.getWriter());
        boardDao.delete(map);
        assertTrue(boardDao.count() == 0);
    }

    @Test
    public void deleteAllTest() {
        boardDao.deleteAll();
        assertTrue(boardDao.count() == 0);
    }
}