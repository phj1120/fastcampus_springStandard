package com.fastcampus.ch3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class UserDaoImplTest {

    @Autowired
    ApplicationContext ac;

    @Autowired
    UserDao userDao;

    @Test
    public void deleteUser() {
    }

    @Test
    public void selectUser() {
    }

    @Test
    public void insertUser() {
    }

    @Test
//    여러 가지 경우를 만들어서 테스트 철저하게 하기.
    public void updateUser() throws Exception {
//        Java 의 util Date 는 날짜, 시간 다 들어감
//        MySQL에서 Date 타입은 날짜만 들어감
//        SQL 에 시간 정보가 잘려서 들어감 -> 불러올 때도 시간 정보가 잘려서 불러와짐
//        user.setBirth(new Date(rs.getDate(5).getTime()));
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(1998, 11, 20);

        userDao.deleteAll();
        User user = new User("abcd", "1234", "abc", "aaa@aaa.com", new Date(cal.getTimeInMillis()), "fb", new Date());
        assertTrue(userDao.insertUser(user) == 1);

        user.setPwd("5678");
        user.setEmail("bbb@bbb.com");
        assertTrue(userDao.updateUser(user) == 1);

        User user2 = userDao.selectUser(user.getId());
        System.out.println("user = " + user);
        System.out.println("user2 = " + user2);
        assertTrue(user.equals(user2));
    }

    @Test
    public void deleteAll() {
    }
}