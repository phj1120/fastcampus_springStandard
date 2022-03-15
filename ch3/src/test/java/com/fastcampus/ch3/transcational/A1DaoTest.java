package com.fastcampus.ch3.transcational;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class A1DaoTest {

    @Autowired
    A1Dao a1Dao;

    @Autowired
    DataSource ds;

    @Autowired
    DataSourceTransactionManager tm;

    @Test
    public void insertTestSuccess() throws Exception {
//        TxManager 생성
        DataSourceTransactionManager tm = new DataSourceTransactionManager(ds);
        TransactionStatus status = tm.getTransaction(new DefaultTransactionDefinition());

        try {
            a1Dao.deleteAll();
            a1Dao.insert(1, 100);
            a1Dao.insert(2, 100);
            tm.commit(status);
        } catch (SQLException e) {
            e.printStackTrace();
            tm.rollback(status);
        }
    }

    @Test
    public void insertTestFail() throws Exception {
//        TxManager bean 에 등록후 자동 주입
        TransactionStatus status = tm.getTransaction(new DefaultTransactionDefinition());
        try {
            a1Dao.deleteAll();
            a1Dao.insert(1, 100);
            a1Dao.insert(1, 100);
            tm.commit(status);
        } catch (SQLException e) {
            e.printStackTrace();
            tm.rollback(status);
        }
    }

    @Test
    public void deleteAllTest() throws Exception{
        a1Dao.deleteAll();
    }

}