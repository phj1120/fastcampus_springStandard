package com.fastcampus.ch3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.assertTrue;

// Spring Test 라이브러리 추가 후 사용 가능
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class DBConnectionTest2Test {

//    인스턴스 변수는 원래 같은 클래스내에 있는 메서드들이 값을 공유하지만
//    JUnit의 Test의 경우 그러지 않음
    @Autowired
    DataSource ds;

    @Autowired
    ApplicationContext ac;

    @Test
    public void contextLoads() throws Exception {
        if (ac != null) {
            String[] beans = ac.getBeanDefinitionNames();

            for (String bean : beans) {
                System.out.println("bean : " + bean);
            }
        }
    }
    @Test
    public void insertUserTest() throws SQLException {
        User user = new User("abcd", "1234", "abc", "aaa@aaa.com", new Date(), "fb", new Date());
        deleteAll(); // 여러번 테스트 하기 위해서.
        int rowCnt = insertUser(user);
        assertTrue(rowCnt==1);
    }

    @Test
    public void selectUserTest() throws SQLException{
//        테스트는 여러번 실행해도 똑같은 결과가 나오도록 해야함
        deleteAll();
        User user = new User("abcd", "1234", "abc", "aaa@aaa.com", new Date(), "fb", new Date());
        insertUser(user);
        User user2 = selectUser("abcd");
        System.out.println("user = " + user);
        System.out.println("user2 = " + user2);
//        user 와 user2 를 비교하면 실패
//        assertTrue(user.equals(user2));
//        user = User(id=abcd, pwd=1234, name=abc, email=aaa@aaa.com, birth=Sun Mar 06 21:52:44 KST 2022, sns=fb, reg_date=Sun Mar 06 21:52:44 KST 2022)
//        user2 = User(id=abcd, pwd=1234, name=abc, email=aaa@aaa.com, birth=2022-03-06, sns=fb, reg_date=2022-03-06)
//        selectUser 에서 타입을 바꿔서 읽어왔기 때문에
        assertTrue(user.getId().equals(user2.getId()));
    }

    @Test
    public void deleteUserTest() throws SQLException{
        deleteAll();
        int rowCnt = deleteUser("asdf");
        assertTrue(rowCnt == 0);

        User user = new User("abcd", "1234", "abc", "aaa@aaa.com", new Date(), "fb", new Date());
        rowCnt = insertUser(user);
        assertTrue(rowCnt == 1);

        deleteUser(user.getId());
        assertTrue(rowCnt == 1);

        assertTrue(selectUser(user.getId()) == null);
    }

    @Test
    public void updateUserTest() throws SQLException {
        deleteAll();
        User user = new User("abcd", "1234", "abc", "aaa@aaa.com", new Date(), "fb", new Date());
        assertTrue(insertUser(user) == 1);

        User user2 = new User("abcd", "5678", "박현준", "bbb@bbb.com", new Date(), "insta", new Date());
        assertTrue(updateUser(user2) == 1);
    }

    public int updateUser(User user) throws SQLException {
        Connection conn = ds.getConnection();

//        여러줄로 sql 작성시 띄어쓰기 확인 할 것(띄어쓰기 있어야함)
        String sql = "update user_info " +
                "set pwd =  ?, name =?, email = ?, birth = ?, sns = ?, reg_date = now() "+
                "where id = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getPwd());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getEmail());
        pstmt.setDate(4, new java.sql.Date(user.getBirth().getTime()));
        pstmt.setString(5, user.getSns());
        pstmt.setString(6, user.getId());
        return pstmt.executeUpdate();
    }

    public int deleteUser(String id) throws SQLException{
        Connection conn = ds.getConnection();

        String sql = "delete from user_info where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);
        int rowCnt = pstmt.executeUpdate(); // insert, delete, update 에 사용
        return rowCnt;
    }

    public User selectUser(String id) throws SQLException {
        Connection conn = ds.getConnection();

        String sql = "select * from user_info where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection 공격, 성능 향상(재실행으로 캐싱 효과)
//        ? 순서대로 값이 채워짐
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        System.out.println("rs = " + rs);
        User user = new User();

        while (rs.next()) {
            user.setId(rs.getString(1));
            user.setPwd(rs.getString(2));
            user.setName(rs.getString(3));
            user.setEmail(rs.getString(4));
            user.setBirth(new java.sql.Date(rs.getDate(5).getTime()));
            user.setSns(rs.getString(6));
            user.setReg_date(new java.sql.Date(rs.getDate(7).getTime()));

            return user;
        }
        return null;
    }
    private void deleteAll() throws SQLException {
        Connection conn = ds.getConnection();
        String sql = "delete from user_info";
        PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection 공격, 성능 향상(재실행으로 캐싱 효과)
        pstmt.executeUpdate(); // insert, delete, update 에 사용
    }

    //    사용자 정보를 user_info 테이블에 저장하는 메서드
    public int insertUser(User user) throws SQLException {
        Connection conn = ds.getConnection();

        String sql = "insert into user_info " +
                "values (?, ?, ?, ?, ?, ?, now())";

        PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection 공격, 성능 향상(재실행으로 캐싱 효과)
//        ? 순서대로 값이 채워짐
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getPwd());
        pstmt.setString(3, user.getName());
        pstmt.setString(4, user.getEmail());
        pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));
        pstmt.setString(6, user.getSns());

        int rowCnt = pstmt.executeUpdate(); // insert, delete, update 에 사용
        return rowCnt;
    }

//    이런 것을 테스트 자동화라 함
    @Test
    public void springJdbcConnectionTest() throws Exception{
//        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
//        DataSource ds = ac.getBean(DataSource.class);

        Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.
        // 괄호 안의 조건식이 True 면 조건식 성공, 아니면 실패
        assertTrue(conn!=null); // 성공
//        assertTrue(conn==null); // 실패
    }
}