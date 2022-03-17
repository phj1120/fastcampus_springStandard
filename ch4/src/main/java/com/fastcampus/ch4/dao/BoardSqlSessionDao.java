package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.BoardDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardSqlSessionDao implements BoardDao {

    @Autowired
    SqlSession session;

    // BoardMapper.xml 파일 하나로 @Mapper 와 @Repository 로 적용 가능하도록 하기 위해서
    // namespace 이렇게 지정
    String namespace = "com.fastcampus.ch4.mapper.BoardMapper.";

    @Override
    public BoardDto select(int bno) throws Exception{
        System.out.println("[BoardDaoImpl.select]");
        return session.selectOne(namespace+"select", bno);
    }

    @Override
    public int insert(BoardDto boardDto) {
        System.out.println("[BoardDaoImpl.insert]");
        return session.insert(namespace+"insert", boardDto);
    }

    @Override
    public int update(BoardDto boardDto) {
        System.out.println("[BoardDaoImpl.update]");
        return session.update(namespace+"update", boardDto);
    }

    @Override
    public int delete(BoardDto boardDto) {
        System.out.println("[BoardDaoImpl.delete]");
        return session.delete(namespace+"delete", boardDto);
    }
}
