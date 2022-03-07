package com.fastcampus.ch3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && Objects.equals(pwd, user.pwd) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(birth, user.birth) && Objects.equals(sns, user.sns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pwd, name, email, birth, sns);
    }

    private String pwd;
    private String name;
    private String email;
    private Date birth;
    private String sns;
    private Date reg_date;
}
