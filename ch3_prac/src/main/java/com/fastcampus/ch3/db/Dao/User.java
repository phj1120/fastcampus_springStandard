package com.fastcampus.ch3.db.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String id;
    String pwd;
    String name;
    String email;
    Date birth;
    String sns;
    Date reg_date;
}
