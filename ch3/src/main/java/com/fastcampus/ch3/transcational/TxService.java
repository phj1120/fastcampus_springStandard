//package com.fastcampus.ch3.transcational;
//
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//public interface TxService {
//    void insertA1WithoutTx() throws Exception;
//
////    @Transactional(rollbackFor = Exception.class)
//    void insertA1WithTxSuccess() throws Exception;
//
////    @Transactional(rollbackFor = Exception.class)
//    void insertA1WithTxFail() throws Exception;
//
////    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
//    void insertA1WithTx() throws Exception;
//
////    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
//    void insertB1WithTx() throws Exception;
//
//    //    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
//    void insertA1WithTx2();
//
//    //    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
//    void insertB1WithTx2();
//}
