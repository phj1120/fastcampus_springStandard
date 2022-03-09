package com.fastcampus.ch3.db.Dao;

public interface UserDao {
    //    C
    int insertUser(User user);

    //    R
    User select(String id);

    //    U
    int updateUser(User user);

    //    D
    int deleteUser(String id);

    int deleteAll();
}
