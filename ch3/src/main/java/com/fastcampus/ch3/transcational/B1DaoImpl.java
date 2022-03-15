package com.fastcampus.ch3.transcational;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class B1DaoImpl implements B1Dao {

    @Autowired
    DataSource ds;

    @Override
    public int insert(int key, int value) throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;

        try
        {
            conn = DataSourceUtils.getConnection(ds);
            System.out.println("[B1Dao] conn = " + conn);
            String sql = "insert into b1 value (?, ?)";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, key);
            psmt.setInt(2, value);

            return psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
//            오류를 던져줘야함 그래야 a1Dao 의 try-catch 에서 값을 잡을 수 있음
            throw e;
//            throw new Exception();
        } finally {
            close(psmt);
            DataSourceUtils.releaseConnection(conn, ds);
        }
    }

    @Override
    public void deleteAll() throws SQLException {
        Connection conn = ds.getConnection();
        String sql = "delete from b1";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.executeUpdate();
        close(conn, psmt);
    }


    private void close(AutoCloseable... acs) {
        for(AutoCloseable ac :acs)
            try { if(ac!=null) ac.close(); } catch(Exception e) { e.printStackTrace(); }
    }
}
