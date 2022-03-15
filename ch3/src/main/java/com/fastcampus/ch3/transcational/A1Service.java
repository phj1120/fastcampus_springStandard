package com.fastcampus.ch3.transcational;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface A1Service {
    void insertA1WithoutTx() throws Exception;

    void insertA1WithTxSuccess() throws Exception;

    void insertA1WithTxFail() throws Exception;

    void insertA1WithTx() throws Exception;

    void insertA1WithTx2();
}
