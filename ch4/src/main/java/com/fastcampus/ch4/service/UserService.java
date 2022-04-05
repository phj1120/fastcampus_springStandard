package com.fastcampus.ch4.service;

import com.fastcampus.ch4.dao.UserDao;
import com.fastcampus.ch4.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

//    create
    public int write(User user) throws Exception {
        return userDao.insertUser(user);
    }

//    read
    public User read(String id) throws Exception {
        return userDao.selectUser(id);
    }

//    update
    public int modify(User user) throws Exception {
        return userDao.updateUser(user);
    }

//    delete
    public int remove(String id) throws Exception {
        return userDao.deleteUser(id);
    }

    public void removeAll() throws Exception {
        userDao.deleteAll();
    }

//    총 유저 수 확인
    public int count() throws Exception {
        return userDao.count();
    }
}
