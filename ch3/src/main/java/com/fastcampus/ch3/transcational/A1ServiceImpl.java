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
public class A1ServiceImpl implements A1Service {

    @Autowired
    DataSource ds;

    @Autowired
    A1Dao a1Dao;

    @Autowired
    B1Dao b1Dao;

    @Autowired
    B1Service b1Service;

    @Override
    public void insertA1WithoutTx() throws Exception {
        a1Dao.insert(3, 100);
        a1Dao.insert(4, 200);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertA1WithTxSuccess() throws Exception {
        a1Dao.insert(1, 100);
        a1Dao.insert(2, 200);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertA1WithTxFail() throws Exception {
        a1Dao.insert(1, 100);
        a1Dao.insert(1, 200);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertA1WithTx() throws Exception{
        try {
            a1Dao.deleteAll();
            a1Dao.insert(1, 100);
            try {
                b1Service.insertB1WithTx();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            a1Dao.insert(1, 200);
        } catch (Exception e) {
            System.out.println("[A1ServiceImpl.insertA1WithTx] - "+e);
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void insertA1WithTx2(){
        DataSourceTransactionManager tm = new DataSourceTransactionManager(ds);
        DefaultTransactionDefinition txd = new DefaultTransactionDefinition();
        txd.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = tm.getTransaction(txd);

        try {
            a1Dao.deleteAll();
            a1Dao.insert(1, 100);
            b1Service.insertB1WithTx2();
            a1Dao.insert(2, 200);
            tm.commit(status);
        } catch (Exception e) {
            System.out.println("[A1ServiceImpl.insertA1WithTx] - "+e);
            e.printStackTrace();
            tm.rollback(status);
        }
    }
}

