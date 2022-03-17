package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.BoardDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//@Repository
public class BoardDaoImpl implements BoardDao {

    @Autowired
    SqlSession session;

    @Override
    public BoardDto select(int bno) throws Exception{
        System.out.println("[BoardDaoImpl.select]");
        return session.selectOne("select", bno);
    }

    @Override
    public int insert(BoardDto boardDto) {
        System.out.println("[BoardDaoImpl.insert]");
        return session.insert("insert", boardDto);
    }

    @Override
    public int update(BoardDto boardDto) {
        System.out.println("[BoardDaoImpl.update]");
        return session.update("update", boardDto);
    }

    @Override
    public int delete(BoardDto boardDto) {
        System.out.println("[BoardDaoImpl.delete]");
        return session.delete("delete", boardDto);
    }
}
