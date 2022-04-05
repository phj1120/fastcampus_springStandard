package com.fastcampus.ch4.service;

import com.fastcampus.ch4.dao.BoardDao;
import com.fastcampus.ch4.domain.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Tx 처리할 것이 없어서 예외를 다 Controller 에 던짐
// Tx 처리를 한다면 try-catch 로 처리 해줘야함
@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardDao boardDao;

    @Override
    public int getCount() throws Exception {
        return boardDao.count();
    }

//  Create
    @Override
    public int write(BoardDto boardDto){
        return boardDao.insert(boardDto);
    }

    //  Read
    @Override
    public BoardDto read(Integer bno) throws Exception {
//      조회수 + 1
        BoardDto boardDto = boardDao.select(bno);
        boardDao.increaseViewCnt(bno);

        return boardDto;
    }

    @Override
    public List<BoardDto> getList() throws Exception {
        return boardDao.selectAll();
    }

    @Override
    public List<BoardDto> getPage(Map<String, Integer> map) throws Exception {
        return boardDao.selectPage(map);
    }

    @Override
    public int count() {
        return boardDao.count();
    }


    //  Update
    @Override
    public int modify(BoardDto boardDto) {
        return boardDao.update(boardDto);
    }

//  Delete
    @Override
    public int remove(Integer bno, String writer) {
        HashMap<String, String> map = new HashMap();
        map.put("bno", Integer.toString(bno));
        map.put("writer", writer);
        return boardDao.delete(map);
    }

}
