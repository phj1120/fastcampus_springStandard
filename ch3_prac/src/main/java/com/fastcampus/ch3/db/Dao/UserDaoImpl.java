package com.fastcampus.ch3.db.Dao;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Calendar;

@Log4j
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    DataSource ds;

    final int FAIL = 0;

//    C
    @Override
    public int insertUser(User user){
//        위에서 auto wired 로 받아 왔는데
//        auto-close 가능한 객체만 넣는 try catch resource 를 할 수가 있나?
//        는 connection 이랑 p.. 그거 하면 되겠네
        String query = "insert into user_info (id, pwd, name, email, birth, sns, reg_date) values (?, ?, ?, ?, ?, ?, now());";
        try (
                Connection conn = ds.getConnection();
                PreparedStatement psmt = conn.prepareStatement(query);
        )
        {
            log.info("[user] : "+user);
            psmt.setString(1, user.getId());
            psmt.setString(2, user.getPwd());
            psmt.setString(3, user.getName());
            psmt.setString(4, user.getEmail());
            psmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));
            psmt.setString(6, user.getSns());

            return psmt.executeUpdate();
        }
        catch (SQLException e){
            log.info(e);
            return FAIL;
        }
    }

//
    @Override
    public User select(String id){
        String sql = "select id, pwd, name, email, birth, sns, reg_date "+
                "from user_info " +
                "where id = ?";

        try (
                Connection conn = ds.getConnection();
                PreparedStatement psmt = conn.prepareStatement(sql)
        ) {
            psmt.setString(1, id);
            ResultSet rs = psmt.executeQuery();

            User user = new User();

            while (rs.next()) {
                user.setId(rs.getString(1));
                user.setPwd(rs.getString(2));
                user.setName(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setBirth(new java.sql.Date(rs.getDate(5).getTime()));
                user.setSns(rs.getString(6));
                user.setReg_date(new java.sql.Date(rs.getDate(7).getTime()));
            }
            return user;

        } catch (SQLException e) {
            log.info(e);
            return null;
        }
    }

//    U
    @Override
    public int updateUser(User user){

        String sql = "update user_info set pwd = ?, name = ?, email = ?, birth = ?, sns = ?, reg_date = now() where id = ?";

        try (
                Connection conn = ds.getConnection();
                PreparedStatement psmt = conn.prepareStatement(sql);
        ) {
            psmt.setString(1, user.getPwd());
            psmt.setString(2, user.getName());
            psmt.setString(3, user.getEmail());
            psmt.setDate(4, new java.sql.Date(user.getBirth().getTime()));
            psmt.setString(5, user.getSns());
            psmt.setString(6, user.getId());

            return psmt.executeUpdate();
        } catch (SQLException e) {
            log.info(e);
            return FAIL;
        }
    }

//    D
    @Override
    public int deleteUser(String id){
        String sql = "delete from user_info where id = ?";
        try (
                Connection conn = ds.getConnection();
                PreparedStatement psmt = conn.prepareStatement(sql);
                ){
            psmt.setString(1, id);

            return psmt.executeUpdate();

        } catch (SQLException e) {
            log.info(e);
            return FAIL;
        }
    }

    @Override
    public int deleteAll(){
        String sql = "delete from user_info";
        try (
                Connection conn = ds.getConnection();
                PreparedStatement psmt = conn.prepareStatement(sql);
        ) {
            return psmt.executeUpdate();
        } catch (SQLException e) {
            log.info(e);
            return FAIL;
        }
    }
}
