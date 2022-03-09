package com.fastcampus.ch3.db;

import lombok.extern.log4j.Log4j;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Log4j
public class SpringJdbcConnectTest {

    public static void main(String[] args) throws SQLException {

        GenericXmlApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
        DataSource ds = ac.getBean(DataSource.class);

        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();

        String query = "SELECT now()";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            String curDate = rs.getString(1);
            log.info("[curDate] : " + curDate);
        }
    }
}
