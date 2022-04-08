package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.BoardDto;
import org.apache.ibatis.session.SqlSession;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardDaoImpl implements BoardDao {

    @Autowired
    SqlSession session;

    // BoardMapper.xml 파일 하나로 @Mapper 와 @Repository 로 적용 가능하도록 하기 위해서
    // namespace 이렇게 지정
    String namespace = "com.fastcampus.ch4.mapper.BoardMapper.";

    @Override
    public int count() {
        return session.selectOne(namespace + "count");
    }

    @Override
    public int insert(BoardDto boardDto) {
        return session.insert(namespace+"insert", boardDto);
    }

    @Override
    public BoardDto select(int bno) throws Exception{
        Map<String, Integer> map = new HashMap<>();
        map.put("bno", bno);
        BoardDto boardDto = session.selectOne(namespace + "select", map);
        return boardDto;
//        return session.selectOne(namespace+"select", bno);
    }

    @Override
    public List<BoardDto> selectAll() throws Exception {
        return session.selectList(namespace + "select");
    }

    @Override
    public List<BoardDto> selectPage(Map<String, Integer> map) throws Exception {
        return session.selectList(namespace + "select", map);
    }

    @Override
    public int update(BoardDto boardDto) {
        return session.update(namespace+"update", boardDto);
    }

    @Override
    public int increaseViewCnt(int bno) {
        return session.update(namespace + "increaseViewCnt", bno);
    }

    @Override
    public int delete(Map<String, String> map) {
        return session.delete(namespace+"delete", map);
    }

    @Override
    public int deleteAll() {
        return session.delete(namespace + "delete");
    }

}
