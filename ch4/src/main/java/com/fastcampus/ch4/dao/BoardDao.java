package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.BoardDto;

import java.util.List;
import java.util.Map;

public interface BoardDao {
    int count();

    int insert(BoardDto boardDto);

    BoardDto select(int bno) throws Exception;

    List<BoardDto> selectAll() throws Exception;

    List<BoardDto> selectPage(Map<String, Integer> map) throws Exception;

    int update(BoardDto boardDto);

    int increaseViewCnt(int bno);

    int delete(Map<String, String> map);

    int deleteAll();
}
