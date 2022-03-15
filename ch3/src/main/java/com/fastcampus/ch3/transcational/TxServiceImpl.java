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
public class TxServiceImpl {
    @Autowired
    DataSource ds;

    @Autowired
    A1Dao a1Dao;

    @Autowired
    B1Dao b1Dao;

    public void insertA1WithoutTx() throws Exception {
        a1Dao.insert(3, 100);
        a1Dao.insert(4, 200);
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertA1WithTxSuccess() throws Exception {
        a1Dao.insert(1, 100);
        a1Dao.insert(2, 200);
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertA1WithTxFail() throws Exception {
        a1Dao.insert(1, 100);
        a1Dao.insert(1, 200);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void insertA1WithTx() throws Exception{
        try {
            a1Dao.deleteAll();
            a1Dao.insert(1, 100);
            try {
                insertB1WithTx();
            } catch (Exception e) {
                e.printStackTrace();
            }
            a1Dao.insert(1, 200);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void insertB1WithTx() throws Exception{
        try {
            b1Dao.deleteAll();
            b1Dao.insert(1, 100);
            b1Dao.insert(2, 200);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void insertA1WithTx2(){
        DataSourceTransactionManager tm = new DataSourceTransactionManager(ds);
        DefaultTransactionDefinition txd = new DefaultTransactionDefinition();
        txd.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = tm.getTransaction(txd);

        try {
            a1Dao.deleteAll();
            a1Dao.insert(1, 100);
            insertB1WithTx2();
            a1Dao.insert(2, 200);
            tm.commit(status);
        } catch (Exception e) {
            e.printStackTrace();
            tm.rollback(status);
        }
    }

//    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
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
            e.printStackTrace();
            tm.rollback(status);
        }

    }

}
