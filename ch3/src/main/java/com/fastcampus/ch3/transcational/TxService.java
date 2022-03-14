package com.fastcampus.ch3.transcational;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TxService {
    @Autowired
    A1Dao a1Dao;

    @Autowired
    B1Dao b1Dao;

    @Transactional
    public void insertA1WithoutTx() throws Exception {
        a1Dao.insert(1, 100);
        a1Dao.insert(1, 100);
    }




}
