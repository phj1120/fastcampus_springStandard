package com.fastcampus.ch3.transcational;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;

@Service
public class B1ServiceImpl implements B1Service {
    @Autowired
    DataSource ds;

    @Autowired
    B1Dao b1Dao;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void insertB1WithTx() throws Exception{
        try {
            b1Dao.deleteAll();
            b1Dao.insert(1, 100);
            b1Dao.insert(2, 200);
        } catch (Exception e) {
            System.out.println("[B1ServiceImpl.insertB1WithTx] - "+e);
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void insertB1WithTx2(){
        DataSourceTransactionManager tm = new DataSourceTransactionManager(ds);
        DefaultTransactionDefinition txd = new DefaultTransactionDefinition();
        txd.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = tm.getTransaction(txd);
        try {
            b1Dao.deleteAll();
            b1Dao.insert(1, 100);
            b1Dao.insert(1, 200);
            tm.commit(status);
        } catch (Exception e) {
            System.out.println("[B1ServiceImpl.insertB1WithTx] - "+e);
            e.printStackTrace();
            tm.rollback(status);
        }
    }
}
