package com.fastcampus.ch3.transcational;

import org.checkerframework.checker.units.qual.A;
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
public class A1DaoTest2 {

    @Autowired
    A1Dao a1Dao;

    @Autowired
    DataSource ds;

    @Autowired
    DataSourceTransactionManager tm;

    @Test
    public void insertTest2() throws Exception {
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

}