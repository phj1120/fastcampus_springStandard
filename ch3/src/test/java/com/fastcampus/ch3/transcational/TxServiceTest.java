package com.fastcampus.ch3.transcational;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class TxServiceTest {

    @Autowired
    TxServiceImpl txService;

    @Autowired
    A1Service a1Service;

    @Autowired
    B1Service b1Service;

    @Test
    public void insertA1WithoutTxTest() throws Exception {
        a1Service.insertA1WithoutTx();
    }

    @Test
    public void insertA1WithTxSuccessTest() throws Exception {
        a1Service.insertA1WithTxSuccess();
    }

    @Test
    public void insertA1WithTxFailTest() throws Exception {
        a1Service.insertA1WithTxFail();
    }

    @Test
    public void insertA1AndB1WithTx() throws Exception{
        a1Service.insertA1WithTx();
    }

    @Test
    public void insertB1WithTxFail() throws Exception {
        b1Service.insertB1WithTx();
        a1Service.insertA1WithTx();
    }

    @Test
    public void TxTest() throws Exception {
        a1Service.insertA1WithTx();
        b1Service.insertB1WithTx();
        txService.insertB1WithTx();
        txService.insertA1WithTx();
    }

    @Test
    public void insertA1AndB1WithTx2() {
        a1Service.insertA1WithTx2();
    }
}
