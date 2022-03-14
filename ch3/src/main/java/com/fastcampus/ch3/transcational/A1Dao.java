package com.fastcampus.ch3.transcational;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class A1Dao {

    @Autowired
    DataSource ds;

    public int insert(int key, int value) throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;

        try
        {
//            conn = ds.getConnection();
            conn = DataSourceUtils.getConnection(ds);
            String sql = "insert into a1 value (?, ?)";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, key);
            psmt.setInt(2, value);

            return psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
//            오류를 던져줘야함 그래야 a1Dao 의 try-catch 에서 값을 잡을 수 있음
            throw e;
        } finally {
            close(psmt);
            DataSourceUtils.releaseConnection(conn, ds);
        }
    }

    public void deleteAll() throws SQLException {
        Connection conn = ds.getConnection();
        String sql = "delete from a1";
        PreparedStatement psmt = conn.prepareStatement(sql);
        close(conn, psmt);
    }


    private void close(AutoCloseable... acs) {
        for(AutoCloseable ac :acs)
            try { if(ac!=null) ac.close(); } catch(Exception e) { e.printStackTrace(); }
    }
}
