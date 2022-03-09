package com.fastcampus.ch3.db;

import lombok.extern.log4j.Log4j;

import java.sql.*;

@Log4j
public class jdbcConnectTest {

    public static void main(String[] args) throws SQLException {
        String DB_URL = "jdbc:mysql://localhost:3306/springbasic?useUnicode=true&characterEncoding=utf8";
        String DB_USER = "castello";
        String DB_PASSWORD = "castello";

        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        Statement stmt = conn.createStatement();

        String query = "SELECT now()";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            String curDate = rs.getString(1);
            log.info("[curDate] : " + curDate);
        }
    }
}
