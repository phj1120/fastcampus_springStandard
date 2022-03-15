package com.fastcampus.ch3.transcational;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;

public interface B1Service {
    void insertB1WithTx() throws Exception;
    void insertB1WithTx2();
}
