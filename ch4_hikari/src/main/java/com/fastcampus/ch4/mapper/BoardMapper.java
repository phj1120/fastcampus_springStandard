package com.fastcampus.ch4.mapper;

import com.fastcampus.ch4.dao.BoardDao;
import com.fastcampus.ch4.domain.BoardDto;

public interface BoardMapper extends BoardDao {
    public BoardDto select(int bno);

    public int insert(BoardDto boardDto);

    public int update(BoardDto boardDto);

    public int delete(BoardDto boardDto);
}
