package com.fastcampus.ch3;

import org.springframework.context.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.*;
import org.springframework.jdbc.datasource.*;

import javax.sql.*;
import java.sql.*;

public class DBConnectionTest2 {
    public static void main(String args[]) throws Exception {
        
//      하드 코딩
//        // 스키마의 이름(springbasic)이 다른 경우 알맞게 변경
//        String DB_URL = "jdbc:mysql://localhost:3306/springbasic?useUnicode=true&characterEncoding=utf8";
//
//        // DB의 userid와 pwd를 알맞게 변경
//        String DB_USER = "castello";
//        String DB_PASSWORD = "castello";
//        String DB_DRIVER = "com.mysql.jdbc.Driver";
//        
//          얘를 property 태그로 처리하고 Bean 에 등록 해서 코드 생략할 예정
//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setDriverClassName(DB_DRIVER);
//        ds.setUrl(DB_URL);
//        ds.setUsername(DB_USER);
//        ds.setPassword(DB_PASSWORD);
	    
        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
        DataSource ds = ac.getBean(DataSource.class);

        Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.

        System.out.println("conn = " + conn);
    }
}

/* [root-context.xml]
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	   xsi:schemaLocation="http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd">

	<!-- Root Context: defines shared resources visible to all other web components -->
	<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
		<property name="driverClassName" value="com.mysql.jdbc.Driver"></property>
		<property name="url" value="jdbc:mysql://localhost:3306/springbasic?useUnicode=true&amp;characterEncoding=utf8"></property>
		<property name="username" value="asdf"></property>
		<property name="password" value="1234"></property>
	</bean>
</beans>
*/
