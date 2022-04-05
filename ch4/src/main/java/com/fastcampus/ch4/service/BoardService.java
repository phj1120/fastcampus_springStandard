package com.fastcampus.ch4.service;

import com.fastcampus.ch4.domain.BoardDto;

import java.util.List;
import java.util.Map;

public interface BoardService {
    int getCount() throws Exception;

    //  Create
    int write(BoardDto boardDto);

    //  Read
    BoardDto read(Integer bno) throws Exception;

    List<BoardDto> getList() throws Exception;

    List<BoardDto> getPage(Map<String, Integer> map) throws Exception;

    int count();

    //  Update
    int modify(BoardDto boardDto);

    //  Delete
    int remove(Integer bno, String writer);
}
