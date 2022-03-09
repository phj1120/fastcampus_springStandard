package com.fastcampus.ch3.db.Dao;

import com.sun.source.tree.AssertTree;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class UserDaoImplTest {

    @Autowired
    UserDao userDao;

    @Before
    public void before(){
        userDao.deleteAll();
    }

    @Test
    public void insertUser() {
        User user = new User("asdf", "1234", "박현준", "ccc@ccc.com", new Date(getBirth(1998, Calendar.NOVEMBER, 20)), "fb", new Date());
        int rowCnt = userDao.insertUser(user);

        assertTrue(rowCnt == 1);
    }

    @Test
    public void select() {
        User user = new User("asdf", "1234", "박현준", "ccc@ccc.com", new Date(getBirth(1998, Calendar.NOVEMBER, 20)), "fb", new Date());
        int rowCnt = userDao.insertUser(user);
        assertTrue(rowCnt == 1);

        User user2 = userDao.select(user.getId());
        log.info("[user]" + user);
        log.info("[user2]" + user2);

        assertTrue(user.getName().equals(user2.getName()));
    }

    @Test
    public void updateUser() {
        User user = new User("asdf", "1234", "박현준", "ccc@ccc.com", new Date(getBirth(1998, Calendar.NOVEMBER, 20)), "fb", new Date());
        int rowCnt = userDao.insertUser(user);
        assertTrue(rowCnt == 1);

        user.setPwd("5678");
        user.setEmail("bbb@bbb.com");
        user.setBirth(new Date(getBirth(2000, Calendar.JANUARY, 1)));
        rowCnt = userDao.updateUser(user);
        assertTrue(rowCnt == 1);
    }

    @Test
    public void deleteUser() {
        User user = new User("asdf", "1234", "박현준", "ccc@ccc.com", new Date(getBirth(1998, Calendar.NOVEMBER, 20)), "fb", new Date());
        int rowCnt = userDao.insertUser(user);
        assertTrue(rowCnt == 1);

        rowCnt = userDao.deleteUser(user.getId());
        assertTrue(rowCnt == 1);
    }

    private long getBirth(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
//        month - 1 할 것 / 10 : NOVEMBER
        cal.set(year, month, day);
        return cal.getTimeInMillis();
    }

}