package com.fastcampus.ch3.transcational;

import java.sql.SQLException;

public interface A1Dao {
    int insert(int key, int value) throws Exception;

    void deleteAll() throws SQLException;
}
